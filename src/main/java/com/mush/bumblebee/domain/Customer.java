package com.mush.bumblebee.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    long id;
    String customerUniqueId;
    String customerFirstName;
    String customerLastName;
    String customerDOB;
    String customerEmail;

    List<Loan> loan;
    String password;

    public Customer() {

    }

    public Customer(String customerUniqueId, String customerFirstName, String customerLastName, String customerDOB, String customerEmail, String password) {
        this.customerUniqueId = customerUniqueId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerDOB = customerDOB;
        this.customerEmail = customerEmail;
        this.password = password;
    }

    public String getCustomerUniqueId() {
        return customerUniqueId;
    }

    public void setCustomerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(String customerDOB) {
        this.customerDOB = customerDOB;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
