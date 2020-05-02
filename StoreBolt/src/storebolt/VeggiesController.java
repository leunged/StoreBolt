/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storebolt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author Doss
 */
public class VeggiesController implements Initializable {

    @FXML
    private TableView<item> veggieTable;
    @FXML
    private Button backButton;
    @FXML
    private Label lbl;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      TableColumn name = new TableColumn("Name");
        TableColumn price = new TableColumn("Price");
        TableColumn id = new TableColumn("ID");
        TableColumn stock = new TableColumn("Stock");
        
        veggieTable.getColumns().addAll(name,price,id,stock);
 
    final ObservableList<item> data = FXCollections.observableArrayList();
    ArrayList<item> items = new ArrayList<>();
    
    item cabbage = new item("Cabbage",3.3,"1001",66);
    item tomato = new item("Tomato",2.0,"1002",66);
    item corn = new item("Corn",1.0,"1003",66);
    item brocolli = new item("Brocolli",5.0,"1004",66);
    
    items.add(cabbage);
    items.add(tomato);
    items.add(corn);
    items.add(brocolli);
    item.addToList(cabbage);
    item.addToList(tomato);
    item.addToList(corn);
    item.addToList(brocolli);
    
    
    for (item b : items) {
            
            item a = new item(b.getName(), b.getPrice(), b.getId(), b.getStock());
            data.add(a);
            
            
        }  
      
    name.setCellValueFactory(new PropertyValueFactory<item,String>("name"));
   name.setMinWidth(132);
    price.setCellValueFactory(new PropertyValueFactory<item,String>("price"));
    price.setMinWidth(132);
    id.setCellValueFactory(new PropertyValueFactory<item,String>("id"));
    id.setMinWidth(132);
    stock.setCellValueFactory(new PropertyValueFactory<item,String>("stock"));
    stock.setMinWidth(132);
    
    veggieTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    veggieTable.setItems(data);
        
    }    

  @FXML
    private void homepage(MouseEvent event) throws IOException {
         Parent homepagee = load(getClass().getResource("mainpage.fxml"));
        Scene scene1 = new Scene(homepagee);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

   @FXML
    private void addToCart(ActionEvent event) {
        
        ObservableList<item> selected = veggieTable.getSelectionModel().getSelectedItems();
        
        for (item item : selected) {
            item.addToOrderList(item);
        }
        
        if(selected.size() > 1) {
            lbl.setText("Items have been added");
        }
        else {
            lbl.setText("Item has been added");
        }
    }

    @FXML
    private void backToHome(ActionEvent event) throws IOException {
        
        Parent sub = load(getClass().getResource("mainpage.fxml"));
        Scene scene8 = new Scene(sub);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene8);
        window.show();
        
    }
    
    
    
}
