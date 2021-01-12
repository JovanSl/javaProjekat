package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbH extends config{
	Connection dbConnection;
	
	public Connection getDBConnection() throws ClassNotFoundException, SQLException{
		String connecString="jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection=DriverManager.getConnection(connecString,dbUser,dbPass);
		return dbConnection;
}
	//write
	public boolean signUpUser(String username,
			String useremail, String userlastname, String userpassword) {
		String insert="INSERT INTO servis.users(username,useremail,userlastname,userpassword) VALUES(?,?,?,?)";
		try {
			PreparedStatement preparedStatement=getDBConnection().prepareStatement(insert);
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,useremail);
			preparedStatement.setString(3,userlastname);
			preparedStatement.setString(4,userpassword);
			preparedStatement.executeUpdate();
			preparedStatement.clearParameters();
		} catch (SQLException e) {
			return false;
		}catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	public ResultSet getUser(String useremail, String userpassword) {
		ResultSet resultSet=null;
		if(!useremail.equals("") ||  !userpassword.equals("") ) {
			String query= "SELECT * FROM servis.users WHERE useremail=? AND userpassword=?";
			try {
			PreparedStatement preparedStatement=getDBConnection().prepareStatement(query);
			preparedStatement.setString(1,useremail);
			preparedStatement.setString(2,userpassword);
			resultSet=preparedStatement.executeQuery();
			System.out.println("100");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO: handle exception
			}
			
		}else{
			System.out.println("Error");
		}
		return resultSet;
	}
	public ResultSet getParcele() {
		ResultSet resultSet2=null;
			String query2= "SELECT * FROM servis.parcele";
			try {
			PreparedStatement preparedStatement2=getDBConnection().prepareStatement(query2);
			resultSet2=preparedStatement2.executeQuery();
			System.out.println("100");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO: handle exception
			}
		return resultSet2;
	}
	
	
	
}