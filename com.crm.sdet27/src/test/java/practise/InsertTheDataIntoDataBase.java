package practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertTheDataIntoDataBase 
{
	public static void main(String[] args) throws Throwable 
	{
		        //registering database
				Driver driver = new Driver();
				DriverManager.registerDriver(driver);
				
				//establish the connection
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
				
				//issue the statement
				Statement statement = connection.createStatement();
				
				//execute statement
				int result = statement.executeUpdate("insert into studentinfo(id , fname,lname,address)values(5,'suresh','raina','delhi')");
	
				//verification
				if(result==1)
				{
					System.out.println("one set of data is added");
				}
				else
				{
					System.out.println("data is not added");
				}
				
				connection.close();
	}

}
