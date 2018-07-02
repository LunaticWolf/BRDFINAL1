package daoOperation;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.util.HashSet;
import java.util.TreeSet;

import entityPojo_customer.Customer;
//import java.io.*;

//DAO Input
public interface InsertDao 
{
	//1.Conditional Check : For Rejection Entry..
	public void conditionCheck(String server, String str, String rejection, Customer customer, BufferedWriter bw);
	
	//2.Database Input Method
	public int inputDB(String server, Customer customer, Connection conn);

	//3.get Connection
	public Connection connection(String server);
	
	//4.Array List fetch code for Customer Code
	public HashSet<String> fetch_customer_code();
}
