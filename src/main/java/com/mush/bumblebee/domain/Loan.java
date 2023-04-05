package com.mush.bumblebee.domain;

public class Loan {

    long id;
    String loanUniqueId;
    Double loanAmount;
    int noOfInstallment;
    Double loanBalance;
    String loanForProduct;
    String loanOfCustomer;

    public String getLoanUniqueId() {
        return loanUniqueId;
    }

    public void setLoanUniqueId(String loanUniqueId) {
        this.loanUniqueId = loanUniqueId;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getNoOfInstallment() {
        return noOfInstallment;
    }

    public void setNoOfInstallment(int noOfInstallment) {
        this.noOfInstallment = noOfInstallment;
    }

    public Double getloanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getLoanForProduct() {
        return loanForProduct;
    }

    public void setLoanForProduct(String loanForProduct) {
        this.loanForProduct = loanForProduct;
    }

    public String getLoanOfCustomer() {
        return loanOfCustomer;
    }

    public void setLoanOfCustomer(String loanOfCustomer) {
        this.loanOfCustomer = loanOfCustomer;
    }
}
