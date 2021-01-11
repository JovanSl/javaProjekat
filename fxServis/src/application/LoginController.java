package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginEmail;

    @FXML
    private Button signUpButton;
    
    private dbH dbh;
    @FXML
    void initialize() {
    	 dbh=new dbH();     	
    		loginButton.setOnAction(event->{
    			String loginemail=loginEmail.getText().trim();
    			String loginpass=loginPassword.getText();
    			if(loginemail.equals("") || loginpass.equals("")) {
    				JOptionPane.showMessageDialog(null,"Sva polja moraju biti popunjena");
    			}else {
    				ResultSet userRow=dbh.getUser(loginemail,loginpass);
    			int counter=0;
    			try {
    				while(userRow.next()) {
    					counter++;
    				}
    				if(counter==1) {
    					System.out.println("Login Successful");
    					loginButton.getScene().getWindow().hide();
    		       		FXMLLoader loader=new FXMLLoader();
    		       		loader.setLocation(getClass().getClassLoader().getResource("application/Program.fxml"));
    		       		try {
    						loader.load();
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    		       		Parent root=loader.getRoot();
    		       		Stage stage=new Stage();
    		       		stage.setScene(new Scene(root));
    		       		stage.showAndWait();
    		       		stage.close();
    		       		
    				}else {
    					JOptionPane.showMessageDialog(null,"Greska!");
    				}
    			}catch (Exception e) {
					
				}
    			}
    		});
    }
    public void toRegister() {
       		loginButton.getScene().getWindow().hide();
       		FXMLLoader loader=new FXMLLoader();
       		loader.setLocation(getClass().getClassLoader().getResource("application/Signup.fxml"));
       		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       		Parent root=loader.getRoot();
       		Stage stage=new Stage();
       		stage.setScene(new Scene(root));
       		stage.showAndWait();
       		stage.close();
    }
}
