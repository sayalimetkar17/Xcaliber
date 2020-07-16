package com.xcaliber.Controller;



import com.xcaliber.Entity.Customer;
import com.xcaliber.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {


    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();

    }
    @PostMapping("/login")
    public String getUsernamePassword(@RequestParam Map<String,String> allParams){
        return customerRepository.validateLogin(allParams.get("username"),allParams.get("password"));

    }


}
