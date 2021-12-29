package rmgyantra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertingDataInProjectUsingDataBase 
{
	public static void main(String[] args) throws Throwable 
	{
	Connection connection = null;
	try
	{	
	Driver driver = new Driver();
	DriverManager.registerDriver(driver);
	
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	
	Statement statement = connection.createStatement();
	
	//int result = statement.executeUpdate("insert into project values('TY_PROJ_007','maaaa','17/12/2020','pal','Created',0)");
	

	
	/*if(result==1)
	{
		System.out.println("one set of data is added");
	}
	else
	{
		System.out.println("data is not added");
	}
	*/
	String expectedCreatedBy="maaa";
	
	ResultSet result1 = statement.executeQuery("select * from project");
	while(result1.next())
	{
		if(result1.getString(2).equals(expectedCreatedBy))
		{
			System.out.println("passed");
		}
	}
	}
	finally
	{
	connection.close();
	}

	
	}
}
