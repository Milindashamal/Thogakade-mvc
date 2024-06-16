package lk.ijse.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;



public class DashboardFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    void btnCustomerFormOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, pane);
        /*this.pane.getChildren().clear();
        this.pane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakade/view/CustomerForm.fxml")));*/
        /*Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load((this.getClass().getResource("/lk/ijse/thogakade/view/CustomerForm.fxml")))));*/
//        initUI("CustomerForm.fxml");
    }

    @FXML
    void btnItemFormOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ITEM, pane);
    }

    @FXML
    void btnPlaceOrderFormOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER, pane);
        /*this.pane.getChildren().clear();
        this.pane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/ijse/thogakade/view/PlaceOrderForm.fxml")));*/
//        initUI("PlaceOrderForm.fxml");
    }
    /*private void initUI(String location) throws IOException {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(FXMLLoader.load(this.getClass()
                .getResource("/lk/ijse/thogakade/view/" + location)));
    }*/
}
