package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.CustomerManager;
import com.mush.bumblebee.domain.Customer;
import com.mush.bumblebee.injector.Injector;
import com.mush.bumblebee.util.AgeCalculator;
import com.mush.bumblebee.util.PasswordEncryption;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
    public String signUp(Customer customer) throws SQLException, ClassNotFoundException, IOException {

        int age=AgeCalculator.calculateAge(customer.getCustomerDOB());
        if(age<18){
            return "You are not eligible to create account";
        }else if(!((customer.getPassword().length() >= 8) && (customer.getPassword().length() <= 12))){
            return "Password must be 8-12 characters";
        }else{
            String password=PasswordEncryption.encryptPassword(customer.getPassword());
            customer.setPassword(password);
            return  customerManager.registerCustomer(customer);
        }
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
