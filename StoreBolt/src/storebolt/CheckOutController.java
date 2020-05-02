/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storebolt;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import static javafx.fxml.FXMLLoader.load;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MP-_-44
 */
public class CheckOutController implements Initializable {

    @FXML
    private Button orderButton;
    @FXML
    private TableView<item> orderTable;
    @FXML
    private Label totalPrice;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;
    
    private double total = 0.0;
    
    public ArrayList<item> list = item.getOrderList();
    
    public static ObservableList<item> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //create new tableview columns
        TableColumn name = new TableColumn("Name");
        TableColumn price = new TableColumn("Price");
        
        //add columns to the table
        orderTable.getColumns().addAll(name,price);
        
        //for loop to add item from the orderList into the ObservableList for the table
        //also sums up total for each item
        for (item b : list) {
            item a = new item(b.getName(), b.getPrice(), "", 0);
            data.add(a);
            total += b.getPrice();
            
        }
        //set value holders for items to be put in
        name.setCellValueFactory(new PropertyValueFactory<item, String>("name"));
        name.setMinWidth(264);
        price.setCellValueFactory(new PropertyValueFactory<item, Double>("price"));
        price.setMinWidth(264);
//        //let users select multiple items
//        orderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //add items to the table
        orderTable.setItems(data);
        //set label displaying total cost
        
        totalPrice.setText("$" + String.valueOf(total));
        
    }    

    @FXML
    private void placeOrder(ActionEvent event) throws IOException {
        
        
       
        //alert user that order has been placed
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Your order has been placed UwU");
        alert.getDialogPane().setPrefSize(480, 100);
        alert.showAndWait();
        //clear orderList and table
        list.clear();
        data.clear();
        //load home page
        Parent homepagee = load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene1 = new Scene(homepagee);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
        
    }

    @FXML
    private void deleteOrder(ActionEvent event) {
        //get selected items from table
        ObservableList<item> selected = orderTable.getSelectionModel().getSelectedItems();
            //get index of selected items
         int index = orderTable.getSelectionModel().selectedIndexProperty().get();
         list.remove(index);
        //remove selected items from table and subtract from cost
        for (item del : selected) {
            orderTable.getItems().remove(del);
            total -= del.getPrice();
            totalPrice.setText("$" + String.valueOf(total));
        }
        Math.round((total*100)/100);
            
    }

    @FXML
    private void backToHome(ActionEvent event) throws IOException{
        data.clear();
        //load home page
        Parent homepagee = load(getClass().getResource("mainpage.fxml"));
        Scene scene1 = new Scene(homepagee);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}
