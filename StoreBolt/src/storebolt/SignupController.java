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
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Doss
 */
public class SignupController implements Initializable {

    @FXML
    private VBox signupScene;
    @FXML
    private TextField signupName;
    @FXML
    private TextField signupEmail;
    @FXML
    private PasswordField signupPass;
    @FXML
    private DatePicker signupDOB;
     private File dataFile = new File("CustomerInfo.csv");
       Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
     
    public static void listToFile(ArrayList<Customer> list, File file){
        PrintWriter output = null;
        try {
            output = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
            for (Customer cst : list){
                output.println(cst.toCsv());
            }
        }catch (IOException e){
            System.out.println("Error");
        }
        finally {
            if (output != null) output.close();
        }
    }
    @FXML
    private Label msg;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void homepage(MouseEvent event) throws IOException {
        Parent homepagee = load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene1 = new Scene(homepagee);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void createCustomer(ActionEvent event) throws IOException{
        //error handling for signup
        if(signupName.getText().equals("") || signupEmail.getText().equals("") || signupPass.getText().equals("") || signupDOB.getValue() == null){
            msg.setText("All fields are Required!");
            return;
        }
        //checks if user is over the age of 19
        if((year - signupDOB.getValue().getYear())<= 19 ){
        msg.setText("You have to be 18!");
        return;
        } 
        if(signupPass.getText().length() < 5){
        msg.setText("Password too short!");
        return;
        }
        else{
        ArrayList<Customer> customerList = new ArrayList<>();
        String date = signupDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        customerList.add(new Customer(signupName.getText(),signupEmail.getText(),date,signupPass.getText()));
        listToFile(customerList,dataFile);


        Parent homepagee = load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene1 = new Scene(homepagee);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
        
          
    }}
    
}
