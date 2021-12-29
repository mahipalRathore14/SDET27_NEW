package practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class GetTheDataFromDataBase 
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
		ResultSet result = statement.executeQuery("select * from studentinfo");
		while(result.next())
		{
			System.out.println(result.getString(1));
		}
		
		//closing
		connection.close();
		
	
		
	}

}
