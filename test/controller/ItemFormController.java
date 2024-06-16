package lk.ijse.thogakade.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import lk.ijse.thogakade.model.CustomerModel;
import lk.ijse.thogakade.model.ItemModel;
import lk.ijse.thogakade.to.Customer;
import lk.ijse.thogakade.to.Item;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtcode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private TableColumn<Item, String> colCode;

    @FXML
    private TableColumn<Item, String> colDescription;

    @FXML
    private TableColumn<Item, Double> colUnitPrice;

    @FXML
    private TableColumn<Item, Integer> colQtyOnHand;

    @FXML
    private TableColumn<Item, Void> colAction;

    @FXML
    private JFXTextField txtSearch;

    private ObservableList<Item> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadTableData();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String code = txtcode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        Item item = new Item(code, description, unitPrice, qtyOnHand);
        try {
            boolean isAdded = ItemModel.save(item);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
                loadTableData();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtcode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        Item item = new Item(code, description, unitPrice, qtyOnHand);
        try {
            boolean isUpdated = ItemModel.update(item);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated!").show();
                loadTableData();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtcode.getText();
        try {
            boolean isDeleted = ItemModel.delete(code);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted!").show();
                loadTableData();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        String code = txtcode.getText();
        try {
            Item item = ItemModel.search(code);
            if (item != null) {
                fillData(item);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtDescriptionOnAction(ActionEvent event) {
        // You can implement necessary action here if needed
    }

    @FXML
    void txtUnitPriceOnAction(ActionEvent event) {
        // You can implement necessary action here if needed
    }

    @FXML
    void txtQtyOnHandOnAction(ActionEvent event) {
        // You can implement necessary action here if needed
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String code = txtSearch.getText();
        try {
            Item item = ItemModel.search(code);
            if (item != null) {
                fillData(item);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Item item) {
        txtcode.setText(item.getCode());
        txtDescription.setText(item.getDescription());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }

    private void clearFields() {
        txtcode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    private void loadTableData() {
        try {
            List<Item> itemList = ItemModel.getAllItems();
            tblItem.getItems().clear();
            tblItem.getItems().addAll(itemList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearForm() {
        txtcode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }
}
