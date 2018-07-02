package main_classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
//import java.util.HashSet;
import java.util.Scanner;

import daoOperation.InsertDao;
import daoOperation.InsertToDb;
import entityPojo_customer.Customer;
//import validation.Rejection;
//import validation.ValidateMethods;


public class Input 

{
	Customer customer = new Customer();
	public static int record = 0;

	void input() {
		int count = 1;
		// int affectedRows=0;

		Scanner scanner = new Scanner(System.in);
		
		String server = "Oracle";
		System.out.println("Enter the file with Complete file location.\n");
		String location = scanner.next();
		System.out.println("Enter the file name.");
		String fileName = scanner.next();
		System.out.println("Enter the file Extention");
		String fileExtention = scanner.next();

		while (!(fileExtention.equals("txt"))) 
		{
			System.out.println("Enter a Valid File Extention.");
			fileExtention = scanner.next();
		}

		System.out.println("Enter Type of Rejection level you wish.\nPress 'F' for File Level Rejection.\n Press 'R' for Record Level Rejection.");
		String rejection = scanner.next();

		String str;
		
		
		try 
		  {
			//Perfor 'Write' on the file -> ErrorFile.txt
			FileWriter filewriter=new FileWriter("d:/errorFile.txt");
			
			BufferedWriter bufferedwriter=new BufferedWriter(filewriter);
			FileReader file_reader = new FileReader(location + ":/" + fileName + "." + fileExtention);
			BufferedReader bufferedreader = new BufferedReader(file_reader);
			
			//DAO Object
			InsertDao dao = new InsertToDb();

			//Line-by-Line Breaking the record file into strings
			while ((str = bufferedreader.readLine()) != null) {
				String st[] = new String[20];
				record = record + 1;
				st = str.split("~");
				
				try 
				{
					//Customer ID Auto Increment with each record
					customer.setCustomer_id(count);
					count = count + 1;
					
					//Customer Data
					customer.setCustomer_code(st[0]);
					customer.setCustomer_name(st[1]);
					customer.setCustomer_address1(st[2]);
					customer.setCustomer_address2(st[3]);
					customer.setCustomer_pinCode(Integer.parseInt(st[4]));
					customer.setEmail_address(st[5]);
					customer.setContact_number(st[6]);
					customer.setPrimaryContactPerson(st[7]);
					customer.setRecord_status(st[8]);
					customer.setActive_inactiveFlag(st[9]);
					customer.setCreate_date(st[10]);
					customer.setCreated_by(st[11]);
					customer.setModified_date(st[12]);
					customer.setModified_by(st[13]);
					customer.setAuthorized_date(st[14]);
					customer.setAuthorized_by(st[15]);
				}

				catch (ArrayIndexOutOfBoundsException e) 
				{
					System.out.println("\nError..!!\n Could Not Read Data off the file");
					e.printStackTrace();
				}

				finally 
				{
                   System.out.println("Read Record: Successfully done..\nProceeding to Validation... ");
					dao.conditionCheck(server, str, rejection, customer, bufferedwriter);
				}

			}

		} catch (Exception e) 
		{
			System.out.println("File not found");
		}

		scanner.close();
		//bufferedreader.close();
	}
	

		public static void main(String[] args)
		{
			Input i=new Input();
			System.out.println("Initiating the Process..\n");
			i.input();
			

		}
}
