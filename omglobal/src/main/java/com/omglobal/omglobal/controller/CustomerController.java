package com.omglobal.omglobal.controller;

import com.omglobal.omglobal.model.request.CustomerRequest;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public Response addCustomer(@RequestBody CustomerRequest customerRequest){
        return this.customerService.addCustomer(customerRequest);
    }

    @GetMapping("/getCustomer")
    public Response getCustomer(@RequestParam Long customerId){
        return this.customerService.getCustomer(customerId);
    }

    @GetMapping("/getAllCustomer")
    public Response getAllCustomer(){
        return this.customerService.getAllCustomer();
    }
}
