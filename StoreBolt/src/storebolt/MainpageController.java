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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Doss
 */
public class MainpageController implements Initializable {

    @FXML
    private VBox subScreen1;
    @FXML
    private Button checkOutButton;
    @FXML
    private Button editUser;

    FXMLDocumentController secondpage;
    String emailid;
    @FXML
    private Label name;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(emailid);
    }

    //method to print name of login user on mainpage

    public void getLoginName(String text) {
        name.setText(text);
    }

    //logout logobutton, with alert to make sure user doesnt signout on accident

    @FXML
    private void homepage(MouseEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent homepagee = load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene1 = new Scene(homepagee);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene1);
            window.show();
//          item.getOrderList().clear();
//          CheckOutController.data.clear();
        }
    }

    //meats table loader

    @FXML
    void meats(MouseEvent event) throws IOException {
        Parent meatsScene = load(getClass().getResource("meats.fxml"));
        Scene scene3 = new Scene(meatsScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

    //vegies table loader

    @FXML
    void vegies(MouseEvent event) throws IOException {
        Parent vegieesScene = load(getClass().getResource("veggies.fxml"));
        Scene scene4 = new Scene(vegieesScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene4);
        window.show();
    }

    //alchohol table loader

    @FXML
    void alchohol(MouseEvent event) throws IOException {
        Parent alchScene = load(getClass().getResource("alchohol.fxml"));
        Scene scene5 = new Scene(alchScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }
//health table loader

    @FXML
    void health(MouseEvent event) throws IOException {
        Parent healthScene = load(getClass().getResource("health.fxml"));
        Scene scene6 = new Scene(healthScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();
    }

    @FXML
    private void checkOut(ActionEvent event) throws IOException {
        Parent checkOut;
        checkOut = load(getClass().getResource("checkOut.fxml"));
        Scene scene7 = new Scene(checkOut);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene7);
        window.show();

    }

    @FXML
    private void editUserHandler(ActionEvent event) throws IOException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Username and Password");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField from = new TextField();
        PasswordField to = new PasswordField();
        Label label1 = new Label("All fields required!");

        gridPane.add(new Label("Username:"), 0, 0);
        gridPane.add(from, 1, 0);
        gridPane.add(new Label("Password:"), 2, 0);
        gridPane.add(to, 3, 0);
        gridPane.add(label1, 0, 4);

        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the username field by default.
        Platform.runLater(() -> from.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(from.getText(), to.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            //System.out.println("Username:" + pair.getKey() + ", Password:" + pair.getValue());
            try {
                if (from.getText().equals("") || to.getText().equals("")) {
                    label1.setText("All fields are Required!");
                    return;
                }
                writeToFile(pair.getKey(), pair.getValue());
            } catch (IOException ex) {
                Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void writeToFile(String userID, String password) throws IOException {
        try {
            String fileName = "CustomerInfo.csv";
            FileWriter fw = new FileWriter(fileName, true);
            StringBuilder sb = new StringBuilder();
            sb.append(userID).append(",");
            sb.append(",");
            sb.append(",");
            sb.append(password + "\n");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

}
