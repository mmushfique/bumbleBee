package com.mush.bumblebee.service;

import com.mush.bumblebee.domain.Customer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface CustomerService {

    public String signUp(Customer customer) throws SQLException, ClassNotFoundException, IOException;


    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException, IOException;

    public Customer getACustomerWithAllDetails(String customerUniqueId) throws SQLException, ClassNotFoundException, IOException;
}
