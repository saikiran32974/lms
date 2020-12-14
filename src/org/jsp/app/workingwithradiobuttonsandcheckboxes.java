package org.jsp.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/workingwithradiobuttonsandcheckboxes")
public class workingwithradiobuttonsandcheckboxes extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
			 String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		 
		 String query="insert into advancejavaonline.radioandcheckbox values(?,?)"; 
		 
		 String[] arr=req.getParameterValues("cd");
		 
		 String s2=" ";
		 
		 for(int index=0;index<=arr.length-1;index++)
		 {
			  s2=s2+arr[index];
			  			 
			 
		 }
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 
			 Connection connection = DriverManager.getConnection(url);
			 
			 
			 PreparedStatement preparedStatement = connection.prepareStatement(query);
			 String s1=req.getParameter("rd");
			 
			 System.out.println(s1);
			 
			 
			 
			 preparedStatement.setString(1, s1);
			 
			 
			 preparedStatement.setString(2, s2);
			 
			 
			 System.out.println("step-2");
					 
			
			  
			 
			  preparedStatement.executeUpdate();
			  
			 
			 
				
			 
			 System.out.println("step 3");
			
			 connection.close();
			 
			 
		 }
			catch(Exception e)
		 {
				e.printStackTrace();
		 }


		 
		 
		 
		

		
	}
	
	
	

}
