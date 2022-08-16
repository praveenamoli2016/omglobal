package com.omglobal.omglobal.service;

import com.omglobal.omglobal.model.request.CustomerRequest;
import com.omglobal.omglobal.model.response.Response;

public interface CustomerService {

    Response addCustomer(CustomerRequest customerRequest);

    Response getCustomer(Long customerId);

    Response getAllCustomer();
}
