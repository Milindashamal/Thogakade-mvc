package lk.ijse.thogakade.model;

import lk.ijse.thogakade.to.Customer;
import lk.ijse.thogakade.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer (id, name, address, salary) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
    }

    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?";
        return CrudUtil.execute(sql, customer.getName(), customer.getAddress(), customer.getSalary(), customer.getId());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Customer WHERE id = ?";
        return CrudUtil.execute(sql, id);
    }

    public static Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Customer(
                    result.getString("id"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getDouble("salary")
            );
        }
        return null;
    }

    public static List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        List<Customer> customerList = new ArrayList<>();
        while (result.next()) {
            customerList.add(new Customer(
                    result.getString("id"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getDouble("salary")
            ));
        }
        return customerList;
    }
}
