package com.xcaliber.Controller;


import com.xcaliber.Entity.Items;
import com.xcaliber.Entity.Order;
import com.xcaliber.Repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/categories")
    public List<String> getCategories(){
        return itemsRepository.getCategories();
    }

    @GetMapping("/categories/items/{category}")
    public List<Items> getItemsFromCategory(@PathVariable String category){
        return itemsRepository.getItemsFromCategory(category);
    }

    @PostMapping(value = "/order",consumes = {MediaType.ALL_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public void getUsernamePassword(@RequestBody List<Order> order){
        //return customerRepository.validateLogin(allParams.get("username"),allParams.get("password"));
        //System.out.println(order.get(0));
        itemsRepository.placeOrder(order);

    }
}
