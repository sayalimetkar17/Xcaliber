package com.xcaliber.Repository;


import com.xcaliber.Entity.Items;
import com.xcaliber.Entity.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemsRepository {

    private JdbcTemplate jdbcTemplate;

    class ItemsRowMapper implements RowMapper<Items>{

        @Override
        public Items mapRow(ResultSet resultSet, int i) throws SQLException {
            if(resultSet.getFetchSize()>-1) {
                Items items = new Items();
                items.setId(resultSet.getInt("id"));
                items.setCategory(resultSet.getString("category"));
                items.setItem(resultSet.getString("item"));
                items.setPrice(resultSet.getDouble("price"));
                return items;
            }
            else
                return null;
        }
    }

    public ItemsRepository(JdbcTemplate jdbcTemplate1){
        this.jdbcTemplate = jdbcTemplate1;
    }

    public List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        categories.addAll(jdbcTemplate.queryForList("select distinct(category) from items",String.class));
        return categories;
    }
    public List<Items> getItemsFromCategory(String category){

        List<Items> items = jdbcTemplate.query("select * from Items where category=?",
                new Object[]{category},new ItemsRowMapper());

        return items;
    }

    public void placeOrder(List<Order> orders){
        for(int i=0;i<orders.size();i++){
            Order o = orders.get(i);
            System.out.println(o.getCustomerid()+" "+
                    o.getItem()+" "+
                    o.getPrice()+" "+
                    o.getQuantity());
            double priceWithQuantity = o.getPrice()*o.getQuantity();
            int res = jdbcTemplate.update("insert into orders (customerid,item,quantity,price)" +
                    "values (?,?,?,?)",new Object[] {o.getCustomerid(),o.getItem(),priceWithQuantity,o.getQuantity()});
            System.out.println(res);
        }

    }

}
