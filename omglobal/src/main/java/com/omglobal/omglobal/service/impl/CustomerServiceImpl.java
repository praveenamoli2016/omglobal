package com.omglobal.omglobal.service.impl;

import com.omglobal.omglobal.model.entities.Customer;
import com.omglobal.omglobal.model.request.CustomerRequest;
import com.omglobal.omglobal.model.request.dto.CustomerRequestDTO;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.repository.CustomerRepo;
import com.omglobal.omglobal.service.CustomerService;
import com.omglobal.omglobal.utility.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Response addCustomer(CustomerRequest customerRequest) {
        Response response = null;
        try {
            List<CustomerRequestDTO> customerRequestList = customerRequest.getCustomerRequestDTOS();
            List<Customer> customerList = new ArrayList<>();
            /*for(CustomerRequestDTO customerRequestDTO : customerRequestList){

            }*/
            customerRequestList.forEach(customerRequestDTO -> {
                Optional<Customer> customerOptional = Optional.empty();
                Customer customer = null;
                //checking id is present or not
                if (customerRequestDTO.getId() != null)
                    //finding customer using id from DB
                    customerOptional = customerRepo.findById(customerRequestDTO.getId());

                //checking customer is present or not
                if (customerOptional.isPresent()) { //updating existing customer
                    customer = customerOptional.get();
                    customer.setUpdatedAt(LocalDateTime.now().toString());
                } else { //creating new customer
                    customer = new Customer();
                    customer.setCreatedAt(LocalDateTime.now().toString());
                }
                //setting all common fields
                customer.setName(customerRequestDTO.getName());
                customer.setAddress(customerRequestDTO.getAddress());
                customer.setCity(customerRequestDTO.getCity());
                customer.setIsDeleted(customerRequestDTO.getIsDeleted() != null ? customerRequestDTO.getIsDeleted() : false);
                customer.setStatus(customerRequestDTO.getStatus() != null ? customerRequestDTO.getStatus() : Status.ACTIVE);
                customer.setCountry(customerRequestDTO.getCountry());
                customer.setCreditLimit(customerRequestDTO.getCreditLimit());
                customer.setEmail(customerRequestDTO.getEmail());
                customer.setMobileNumber(customerRequestDTO.getMobileNumber());
                customer.setPhoneNumber(customerRequestDTO.getPhoneNumber());
                customer.setGstNumber(customerRequestDTO.getTaxNumber());
                customer.setPriviousdue(customerRequestDTO.getPriviousdue());
                customer.setLocationLink(customerRequestDTO.getLocationLink());
                customer.setOpeningBalance(customerRequestDTO.getOpeningBalance());
                customer.setPostCode(customerRequestDTO.getPostCode());
                customer.setShippingAddress(customerRequestDTO.getShippingAddress());
                customer.setShippingCountry(customerRequestDTO.getShippingCountry());
                customer.setShippingCity(customerRequestDTO.getShippingCity());
                customer.setShippingLocationLink(customerRequestDTO.getShippingLocationLink());
                customer.setShippingPostCode(customerRequestDTO.getShippingPostCode());
                customer.setShippingState(customerRequestDTO.getShippingState());
                customer.setState(customerRequestDTO.getState());
                customer.setTaxNumber(customerRequestDTO.getTaxNumber());
                customer.setPriceLevel(customerRequestDTO.getPriceLevel());

                //saving customer in list
                customerList.add(customer);
            });
            //saving list of customer in database
            List<Customer> savedList = customerRepo.saveAll(customerList);
            response = new Response("Success", "200", "Customer saved successfully", savedList);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getCustomer(Long customerId) {
        Response response = null;
        try {
            Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                response = new Response("Success", "200", "Get Customer successfully", customer);

            } else {
                response = new Response("Failure", "404", "Customer not found ", null);

            }

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;

    }

    @Override
    public Response getAllCustomer() {
        Response response = null;
        try {
            List<Customer> customerList=customerRepo.findAll();

                response = new Response("Success", "200", "CustomerList fetched successfully", customerList);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }
}