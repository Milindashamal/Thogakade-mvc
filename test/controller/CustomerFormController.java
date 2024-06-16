package lk.ijse.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.model.CustomerModel;
import lk.ijse.thogakade.to.Customer;
import lk.ijse.thogakade.util.CrudUtil;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableColumn<Customer, String> colID;

    @FXML
    private TableColumn<Customer, String> colName;

    @FXML
    private TableColumn<Customer, String> colAddress;

    @FXML
    private TableColumn<Customer, Double> colSalary;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnBack;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tblCustomer.setItems(customerList);
        loadAllCustomers();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        Customer customer = new Customer(id, name, address, salary);
        try {
            boolean isAdded = CustomerModel.save(customer);
            if (isAdded) {
                customerList.add(customer);
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                clearForm();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Customer selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            try {
                boolean isDeleted = CustomerModel.delete(selectedCustomer.getId());
                if (isDeleted) {
                    customerList.remove(selectedCustomer);
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Removed!").show();
                    clearForm();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a customer to delete.").show();
        }
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            double salary = Double.parseDouble(txtSalary.getText());

            Customer updatedCustomer = new Customer(id, name, address, salary);
            try {
                boolean isUpdated = CustomerModel.update(updatedCustomer);
                if (isUpdated) {
                    int selectedIndex = tblCustomer.getSelectionModel().getSelectedIndex();
                    customerList.set(selectedIndex, updatedCustomer);
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!").show();
                    clearForm();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a customer to update.").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            Customer customer = CustomerModel.search(id);
            if (customer != null) {
                fillData(customer);
            } else {
                new Alert(Alert.AlertType.WARNING, "No customer found with the given ID.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(Customer customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    private void loadAllCustomers() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getDouble("salary")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }
}
