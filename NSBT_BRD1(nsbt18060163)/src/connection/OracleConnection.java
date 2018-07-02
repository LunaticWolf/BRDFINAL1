package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
//import java.sql.*;


public class OracleConnection 
{
  //private static Connection connection  =  null;
	Connection conn = null;

	//default
	public OracleConnection(){}
	
	
    //Setup Connection to JDBC - 
	public  Connection myConnection()
	{
        String driverclass = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@10.1.50.198:1521:orcl";
        String username = "sh";
        String password = "sh";

	try 
		{
		 	Class.forName(driverclass);
			conn=DriverManager.getConnection(url,username,password);
            
			System.out.println("Error - \n Driver Class is Successfully Loaded...");	
            
		}
		
	catch (ClassNotFoundException e) 
		{	
            System.out.println("Error - \n Driver Class Not Loaded...\nClassNotFound Exception Encountered while setting up JDBC connection..");
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			System.out.println("Error - \n Driver Class Not Loaded...\n SQLException Encountered while setting up JDBC connection");
			e.printStackTrace();
		}
		return conn;
	}

//Close Connection from JDBC - 
	public void CloseConnection()
	{
		try 
		{
			conn.close();
            System.out.println("\nJBDC - Connection to Oracle Server has been Closed..");
		}
	
		catch (SQLException e) 
		{
			e.printStackTrace();
            System.out.println("Error in JDBC Connection - \n Connection not Terminated Properly.. ");
		}	
	}
}
