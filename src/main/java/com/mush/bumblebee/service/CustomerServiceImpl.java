package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.CustomerManager;
import com.mush.bumblebee.domain.Customer;
import com.mush.bumblebee.injector.Injector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private final Injector injector = new Injector();
    private static CustomerServiceImpl service;
    CustomerManager customerManager= injector.getCustomerManager();

    private CustomerServiceImpl() {
    }

    public static synchronized CustomerServiceImpl getCustomerServiceInstance() {
        if(service==null) {
            service=new CustomerServiceImpl();
        }
        return service;
    }
    @Override
    public boolean signUp(Customer customer) throws SQLException, ClassNotFoundException, IOException {
        return customerManager.registerCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException, IOException {
        return customerManager.getAllCustomers();
    }

    @Override
    public Customer getACustomerWithAllDetails(String customerUniqueId) throws SQLException, ClassNotFoundException, IOException {
        return customerManager.getACustomerWithAllDetails(customerUniqueId);
    }


}
