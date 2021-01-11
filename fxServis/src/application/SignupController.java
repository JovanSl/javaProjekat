package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signupButton;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private TextField signupPrezime;

    @FXML
    private Button signupLogin;

    @FXML
    private TextField signupIme;

    @FXML
    private TextField signupEmail;

    @FXML
    public Label singupE;

    @FXML
    private Label singupOK;

    @FXML
    void initialize() {

    	signupButton.setOnAction(event->{
    		try {
    			regUser();
    			singupOK.setText("Registration Succesfull");
			} catch (Exception e2) {
				singupE.setText("Error");
			}
    		regUser();

    	});
    }
    public void regUser() {
    	dbH dbh=new dbH();
    	String ime=signupIme.getText();
    	String prezime=signupPrezime.getText();
    	String email=signupEmail.getText();
    	String password=signupPassword.getText();
    	if(ime.equals("") || prezime.equals("") || email.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null,"Sva polja moraju biti popunjena");
		}else {
			dbh.signUpUser(ime, email, prezime, password);
		}
    	
    }
    public void toLogIn() {
    		signupButton.getScene().getWindow().hide();
       		FXMLLoader loader2=new FXMLLoader();
       		loader2.setLocation(getClass().getClassLoader().getResource("application/Login.fxml"));
       		try {
				loader2.load();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
       		Parent root=loader2.getRoot();
       		Stage stage=new Stage();
       		stage.setScene(new Scene(root));
       		stage.showAndWait();
       		stage.close();
       		System.exit(0);
    }
}
