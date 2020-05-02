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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

/**
 *
 * @author Doss
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private VBox homeScene;
    @FXML
    private TextField emailid;
    @FXML
    private PasswordField passid;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private Label lb1;

    private ArrayList<Customer> customers;
    private MainpageController secondpage;
    ArrayList <Customer> listClone =new ArrayList<>(); 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lb1.setVisible(false);
        customers = new ArrayList<>();
       
    }

    void readFileName(String fileName) {
        File file = new File("CustomerInfo.csv");
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(file);
            while (fileInput.hasNext()) {
                String record = fileInput.nextLine();
                String[] fields = record.split(",");
                String name = fields[0];
                String email = fields[1];
                String dateOfBirth = fields[2];
                String password = fields[3];
                Customer newCustomer = new Customer(name, email, dateOfBirth, password);
                customers.add(newCustomer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't read from file");
        } finally {
            if (fileInput != null) {
                fileInput.close();
            }
        }
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        try {
            //error handling for empty login
            readFileName("CustomerInfo.csv");
            String email = emailid.getText();
            String password = passid.getText();
            if (email.equals("") || password.equals("")) {
                lb1.setVisible(true);
                lb1.setText("Login Failed");
            }
            for (Customer cst : customers) {
                if (!email.equals(cst.getName()) && (!password.equals(cst.getPassword()))) {
                    lb1.setVisible(true);
                    lb1.setText("Login Failed");
                } else if (email.equals(cst.getName()) && (password.equals(cst.getPassword()))) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("mainpage.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show(); 
                    secondpage = loader.getController();
                    secondpage.getLoginName("Welcome "+emailid.getText()+"!");
                }
            }
        } catch (IOException e) {
            lb1.setVisible(true);
            lb1.setText("Login Failed");
        }
    }

    @FXML
    void signuppage(ActionEvent event) throws IOException {
        Parent signupScene = load(getClass().getResource("signup.fxml"));
        Scene scene2 = new Scene(signupScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void search(ActionEvent event) {
        //checks if search field is empty
       if(emailid.getText().equals("")){
        lb1.setVisible(true);
            lb1.setText("Empty Search field!");
       }else{
           //reads arraylist of users from file
        readFileName("CustomerInfo.csv");
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Members");
        alert.setHeaderText("Our memberlist ");
        StringBuilder listBuilder = new StringBuilder();
            //new alert box with all the members found
            for (Customer cst : customers) {
               if(cst.getName().equals(emailid.getText())){
               listClone.add(cst);
               }   
        }
              for (Customer i : listClone) {
                listBuilder.append(i.getName()+" "+ i.getEmail()+ "\n");
               }
              
        String text = listBuilder.toString();
        alert.setContentText(text);
        alert.showAndWait(); 
        listClone.clear();
        customers.clear();
        listBuilder.setLength(0);
    }}

}
