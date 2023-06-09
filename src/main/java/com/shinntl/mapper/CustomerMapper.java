package com.shinntl.mapper;

import com.shinntl.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer>{

    @Override
    public Customer mapRow(ResultSet resultSet) {
        Customer customer = new Customer();
        try {
            customer.setId(resultSet.getLong("Customer_ID"));
            customer.setFirstName(resultSet.getString("First_Name"));
            customer.setLastName(resultSet.getString("Last_Name"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setAddress(resultSet.getString("Address"));
            customer.setCity(resultSet.getString("City"));
            customer.setCountry(resultSet.getString("Country"));
            customer.setSex(resultSet.getBoolean("Sex"));
            customer.setPhoneNumber(resultSet.getString("Phone_Number"));

            // set Avatar dữ liệu kiểu LongBlob

            customer.setAccountId(resultSet.getLong("Account_ID"));
            customer.setCreatedDate(resultSet.getTimestamp("Createddate"));
            customer.setModifiedDate(resultSet.getTimestamp("Modifieddate"));

            // Bị Null nếu tạo và chưa chỉnh sửa. ---> Fix
            customer.setCreatedBy(resultSet.getString("Createdby"));
            customer.setModifiedBy(resultSet.getString("Modifiedby"));
            return customer;
        } catch (SQLException e) {
            return null;
        }

    }
}
