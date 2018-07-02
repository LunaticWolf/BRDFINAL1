package validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.TreeSet;


//import connection.ConnectionI;
//import connection.OracleConnection;
import daoOperation.InsertDao;
import daoOperation.InsertToDb;
import entityPojo_customer.Customer;
import main_classes.Input;

public class Rejection 
{

	
	//Validate Object ==> validate calls Valid Methods
	ValidationI validate = new ValidateMethods();


	//Checking For Validity At Record Level
	public void recordLevel(String server, String str, Customer customer,BufferedWriter bufferedwriter) throws IOException 
	{

		InsertDao dao = new InsertToDb();
	    HashSet<String> set=dao.fetch_customer_code();
 
	   
	    
	    //Validated Boolean values
		boolean code = validate.validCustomerCode(customer, set);
		set.add(customer.getCustomer_code());
 
		boolean name = validate.validCustomerName(customer);

		boolean pinCode = validate.validPinCode(customer);

		boolean record = validate.validRecordStatus(customer);

		boolean flag = validate.validFlag(customer);

		boolean email = validate.validEmail(customer);

		Connection conn = dao.connection(server);
		
		
		
		
		//Validity Check
		if (code && name && record && pinCode && flag && email) 
		{
			int recordsAffected = dao.inputDB(server, customer, conn);

			if (recordsAffected > 0)
				{
				  System.out.println("More than One Record Afftected");
				}

			else
			{
				System.out.println("Rejection\nAt Record Level\nValidity Check:Some error ocuured");
			}

			
			try 
			{
				System.out.println("\nCondition Checked Status:");
				conn.commit();
				conn.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("\nCondition Checked Status: Failed");
				e.printStackTrace();
			}
		} 
		
		
		else 
		{	
			System.out.println("\n Rejection:\nAt Record Level: Invalid Records ..");
			try 
			  {
				System.out.println("\nData Error ");
				if(code==false)
				{
				bufferedwriter.append(str);
				//bufferedwriter.newLine();
				bufferedwriter.append("\nError in Customer Code");
				//bufferedwriter.newLine();
				bufferedwriter.flush();
				}
				
				
				else if(name==false)
				{
					bufferedwriter.append(str);
					//bufferedwriter.newLine();
					bufferedwriter.append("\nError in name");
					//bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				
				else if(pinCode==false)
				{
					bufferedwriter.append(str);
					//bufferedwriter.newLine();
					bufferedwriter.append("\nError in pincode");
					//bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				
				else if(record==false)
				{
					bufferedwriter.append(str);
					//bufferedwriter.newLine();
					bufferedwriter.append("\nError in record");
					//bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				
				else if(flag==false)
				{
					bufferedwriter.append(str);
					//bufferedwriter.newLine();
					bufferedwriter.append("\nError in flag value");
					//bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				
				else if(email==false)
				{
					bufferedwriter.append(str);
					//bufferedwriter.newLine();
					bufferedwriter.append("\nError in email format");
					//bufferedwriter.newLine();
					bufferedwriter.flush();
				}
			} 
			
			catch (IOException e) 
			
			{
				System.out.println("\nError Occcured While Writing Records.");
				e.printStackTrace();
			}

		}
	}

	
	
	public void fileLevel(String server, String str, Customer customer,BufferedWriter bufferedwriter) {
		InsertDao dao = new InsertToDb();
		HashSet<String> set=dao.fetch_customer_code();
		
		int c=0;
		
		boolean code = validate.validCustomerCode(customer, set);
		set.add(customer.getCustomer_code());
		
		boolean name = validate.validCustomerName(customer);

		boolean pinCode = validate.validPinCode(customer);

		boolean record = validate.validRecordStatus(customer);

		boolean flag = validate.validFlag(customer);

		boolean email = validate.validEmail(customer);

		Connection conn = dao.connection(server);

		if (code && name && record && pinCode && flag && email) 
		{
			int rowsAffected = dao.inputDB(server, customer, conn);
			c=c+1;
			

			if (rowsAffected > 0)
				System.out.println("done");
			else
				System.out.println("Some error ocuured");
		} 
		else
		{
			System.out.println("At File Level Rejection:\nError Occured..");
			try 
			  {
				if(code==false)
				{
					bufferedwriter.append(str);
					bufferedwriter.newLine();
					bufferedwriter.append("Error in code");
					bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				else if(name==false)
				{
					bufferedwriter.append(str);
					bufferedwriter.newLine();
					bufferedwriter.append("\nError in name..");
					bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				else if(pinCode==false)
				{
					bufferedwriter.append(str);
					bufferedwriter.newLine();
					bufferedwriter.append("\nError in pincode..");
					bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				else if(record==false)
				{
					bufferedwriter.append(str);
					bufferedwriter.newLine();
					bufferedwriter.append("\nError in record..");
					bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				else if(flag==false)
				{
					bufferedwriter.append(str);
					bufferedwriter.newLine();
					bufferedwriter.append("\nError in Flag value..");
					bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				else if(email==false)
				{
					bufferedwriter.append(str);
					bufferedwriter.newLine();
					bufferedwriter.append("\nError in Email format..");
					bufferedwriter.newLine();
					bufferedwriter.flush();
				}
				
				//Rollback Record Data
				conn.rollback();
				

			}
			catch (Exception e) 
			{
				System.out.println("At File Level Rejection:Some Error Occured..");
				e.printStackTrace();
			}
			if((Input.record)==c)
			{
				try 
				{
					
					//Commit Sucessfully done
					System.out.print("At File Level Rejection:Record Successfully Stored");
					conn.commit();
					conn.close();
				} 
				catch (SQLException e) 
				{
					System.out.println("At File Level Rejection: Some Error occurred..");
					e.printStackTrace();
				}
				
			}

		}
	}
}
