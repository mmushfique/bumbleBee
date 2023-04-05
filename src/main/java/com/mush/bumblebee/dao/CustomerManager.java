package com.mush.bumblebee.dao;

import com.mush.bumblebee.domain.Customer;
import com.mush.bumblebee.domain.Loan;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer")
public class CustomerManager {

    public boolean registerCustomer(Customer customer) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

  /*  try {
        String query = "SELECT username FROM user WHERE username='" + username + "'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            PrintWriter out = response.getWriter();
            out.print("username");
        } else {
            query = "SELECT email FROM user WHERE email='" + email + "'";
            st = connection.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                PrintWriter out = response.getWriter();
                out.print("email");
            } else {
                query = "SELECT phone_no FROM user WHERE phone_no='" + phoneNo + "'";

                st = connection.createStatement();
                rs = st.executeQuery(query);
                if (rs.next()) {
                    PrintWriter out = response.getWriter();
                    out.print("phone");
                } else {

                }
                //Insert register data to database
                query = "INSERT INTO user VALUES(?,?,?,?,?,?)";

                PreparedStatement pst = this.connection.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, fName);
                pst.setString(3, lName);
                pst.setString(4, email);
                pst.setString(5, phoneNo);
                pst.setString(6, password);
                pst.executeUpdate();
            }
        }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println(e);
        }*/


        String query="INSERT INTO customer (customerUniqueID,customerFirstName,customerLastName,customerDOB, customerEmail,password) VALUES(?,?,?,?,?,?)";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, customer.getCustomerUniqueId());
        pst.setString(2, customer.getCustomerFirstName());
        pst.setString(3, customer.getCustomerLastName());
        pst.setString(4, customer.getCustomerDOB());
        pst.setString(5, customer.getCustomerEmail());
        pst.setString(6, customer.getPassword());



        int result=pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }


    public List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        List<Customer> customerList=new ArrayList<Customer>();

        String query="SELECT customerUniqueId,customerFirstName,customerLastName,customerDOB,customerEmail FROM customer";
        Statement st=connection.createStatement();

        ResultSet rs=st.executeQuery(query);
        while(rs.next()) {
            Customer customer=new Customer();
            customer.setCustomerUniqueId(rs.getString("customerUniqueId"));
            customer.setCustomerFirstName(rs.getString("customerFirstName"));
            customer.setCustomerLastName(rs.getString("customerLastName"));
            customer.setCustomerDOB(rs.getString("customerDOB"));
            customer.setCustomerEmail(rs.getString("customerEmail"));
            customerList.add(customer);
        }

        st.close();
        connection.close();

        return customerList;
    }

    public Customer getACustomerWithAllDetails(String customerUniqueId) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        Customer customer=new Customer();
        List<Loan> loanList=new ArrayList<Loan>();

        String query="SELECT * FROM customer JOIN loan ON customer.customerUniqueId=loan.loanOfCustomer JOIN product ON " +
                "loan.loanForProduct=product.productUniqueId WHERE customerUniqueId=?";

        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, customerUniqueId);

        ResultSet rs= pst.executeQuery();
        while(rs.next()) {

            Loan loan =new Loan();
            loan.setLoanUniqueId(rs.getString("loanUniqueId"));
            loan.setLoanAmount(rs.getDouble("loanAmount"));
            loan.setNoOfInstallment(rs.getInt("noOfInstallment"));
            loan.setLoanBalance(rs.getDouble("loanBalance"));
            loan.setLoanForProduct(rs.getString("productName"));

            customer.setCustomerUniqueId(rs.getString("customerUniqueId"));
            customer.setCustomerFirstName(rs.getString("customerFirstName"));

            loanList.add(loan);

        }customer.setLoan(loanList);



        pst.close();
        connection.close();

        return customer;
    }
}
