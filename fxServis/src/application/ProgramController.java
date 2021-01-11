package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.Initializable;


public class ProgramController extends config implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logOut;
    @FXML
    private TableView<parcelaData> parcelatable;
    @FXML
    private TableColumn<parcelaData, String> bParc;

    @FXML
    private TableColumn<parcelaData, String> parIme;

    @FXML
    private TableColumn<parcelaData, String> parPov;

    @FXML
    private TableColumn<parcelaData, String> parTip;

    @FXML
    private Button addData;

    @FXML
    private Button pUredi;

    @FXML
    private Button pObrisi;

    @FXML
    private TextField pIme;

    @FXML
    private TextField bPar;

    @FXML
    private TextField pPov;

    @FXML
    private TextField pTip;
    @FXML
    private TextField textid;
    
    private ObservableList<parcelaData> data;
 
    @Override
    public void initialize(URL location,ResourceBundle resources) {
    	parcelatable.setOnMouseClicked(event ->{
    		if(event.getClickCount()>0) 
    			loadDataToTextField();
     });
    		
    	try {
			loadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }	
    	//Update table row
    public void updateP(ActionEvent event) {
    	
 		String imeP=pIme.getText();
 		String brojP=bPar.getText();
 		String povrsinaP=pPov.getText();
 		String tipP=pTip.getText();
    	String insert="UPDATE servis.parcele set imeP = ?, povrsinaP=?,tipZemljistaP=? where brojP=?";
    		try {
    			PreparedStatement preparedStatement=getDBConnection().prepareStatement(insert);
    			preparedStatement.setString(1,imeP);
    			preparedStatement.setString(2,povrsinaP);
    			preparedStatement.setString(3,tipP);
    			preparedStatement.setString(4,brojP);
    			preparedStatement.executeUpdate();
    			JOptionPane.showMessageDialog(null,"Uspesno Uredjeno");
    			loadData();
    			dbConnection.close();
    		} catch (SQLException e) {
    			System.out.println(e);
    		}catch (ClassNotFoundException e) {
    		}
    		 this.pIme.setText("");
         	 this.bPar.setText("");
         	 this.pPov.setText("");
         	 this.pTip.setText("");
    }
		//table row selection
    public void loadDataToTextField() {
    	try {
			parcelaData pd=parcelatable.getSelectionModel().getSelectedItem();
			pIme.setText(pd.getParIme());
			bPar.setText(pd.getBParc());
			pPov.setText(pd.getParPov());
			pTip.setText(pd.getParTip());	
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    //db connection
	Connection dbConnection;
	public Connection getDBConnection() throws ClassNotFoundException, SQLException{
		String connecString="jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection=DriverManager.getConnection(connecString,dbUser,dbPass);
		return dbConnection;
}	
	//load button
	public void loadData(ActionEvent event)throws SQLException {
			
		try {
			ResultSet rs=getDBConnection().createStatement().executeQuery("SELECT brojP,imeP,povrsinaP,tipZemljistaP FROM servis.parcele");
			this.data=FXCollections.observableArrayList();	
			while(rs.next()) {
				this.data.add(new parcelaData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.bParc.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("bParc"));
		this.parIme.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("parIme"));
		this.parPov.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("parPov"));
		this.parTip.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("parTip"));
		this.parcelatable.setItems(null);
		this.parcelatable.setItems(this.data);
	}
		//add
    public void addData(ActionEvent event) {

     	String imeP=pIme.getText();
     	String brojP=bPar.getText();
     	String povrsinaP=pPov.getText();
     	String tipP=pTip.getText();
    		String insert="INSERT INTO servis.parcele(brojP,imeP,povrsinaP,tipZemljistaP) VALUES(?,?,?,?)";
    		if(imeP.equals("") || brojP.equals("") || povrsinaP.equals("") || tipP.equals("")) {
    			JOptionPane.showMessageDialog(null,"Sva polja moraju biti popunjena");
    		}else {
    			try {
    			PreparedStatement preparedStatement=getDBConnection().prepareStatement(insert);
    			preparedStatement.setString(1,brojP);
    			preparedStatement.setString(2,imeP);
    			preparedStatement.setString(3,povrsinaP);
    			preparedStatement.setString(4,tipP);
    			preparedStatement.execute();
    			dbConnection.close();
    		} catch (SQLException e) {
    			System.out.println(e);
    		}catch (ClassNotFoundException e) {
    		}
    		 this.pIme.setText("");
         	 this.bPar.setText("");
         	 this.pPov.setText("");
         	 this.pTip.setText("");
         	 try {
				loadData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		}		
         	 
    }
    public void ocisti() {
    	 pIme.setText("");
     	 bPar.setText("");
     	 pPov.setText("");
     	 pTip.setText("");
    }
    	//delete
    public void deleteData(ActionEvent event) {
    	
     	String brojP=bPar.getText();
    		String insert="DELETE FROM servis.parcele where brojP = ?";
    		if (brojP.equals("")) {
				JOptionPane.showMessageDialog(null,"Morate izaberite parcelu");
    		}else {
    			try {
    			PreparedStatement preparedStatement=getDBConnection().prepareStatement(insert);
    			preparedStatement.setString(1,brojP);
    			preparedStatement.executeUpdate();
    			JOptionPane.showMessageDialog(null,"Uspesno obrisano");
    			loadData();
    			dbConnection.close();
    		} catch (SQLException e) {
    			System.out.println(e);
    		}catch (ClassNotFoundException e) {
    			System.out.println(e);
    		}
    		}
    		 this.pIme.setText("");
         	 this.bPar.setText("");
         	 this.pPov.setText("");
         	 this.pTip.setText("");
         	 try {
				loadData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		}
    
    		//automatic load
    	public void loadData()throws SQLException {
			
    		try {
    			ResultSet rs=getDBConnection().createStatement().executeQuery("SELECT brojP,imeP,povrsinaP,tipZemljistaP FROM servis.parcele");
    			this.data=FXCollections.observableArrayList();	
    			System.out.println(rs);
    			while(rs.next()) {
    				this.data.add(new parcelaData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
    			}
    		} catch (SQLException | ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		this.bParc.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("bParc"));
    		this.parIme.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("parIme"));
    		this.parPov.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("parPov"));
    		this.parTip.setCellValueFactory(new PropertyValueFactory<parcelaData, String>("parTip"));
    		
    		this.parcelatable.setItems(null);
    		this.parcelatable.setItems(this.data);
    	}
}
