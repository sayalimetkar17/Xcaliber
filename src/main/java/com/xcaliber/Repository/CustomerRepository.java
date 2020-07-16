package com.xcaliber.Repository;

import com.xcaliber.Entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository  {

    private JdbcTemplate jdbcTemplate;

    class CustomerRowMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            System.out.println(resultSet.getFetchSize());
            if(resultSet.getFetchSize()>-1) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                return customer;
            }
            else
                return null;
        }
    }

    public CustomerRepository(JdbcTemplate jdbcTemplate1){
        this.jdbcTemplate = jdbcTemplate1;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        customers.addAll(jdbcTemplate.query("select * from Customer",new CustomerRowMapper()));
        return  customers;
    }

    public String validateLogin(String username,String password) {

        Customer customer = jdbcTemplate.queryForObject
                ("SELECT * from customer where username=?",
                        new Object[]{username},
                        new CustomerRowMapper());
        System.out.println(customer);

        if (customer != null){
            if (password.equals(customer.getPassword())) {
                return "Login Successful";
            } else
                return "Invalid Login Details";
        }
        else
            return "Register First";

    }


}
