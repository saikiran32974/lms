package org.jsp.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/DeleteBook")
public class DeleteBook extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		String booktitle=req.getParameter("title");
		
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="delete from librarymanagementsystem.lms where booktitle=?";
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, booktitle);
			
			String query2="select * from librarymanagementsystem.lms where booktitle=?";
			PreparedStatement preparedStatement2=connection.prepareStatement(query2);
			preparedStatement2.setString(1, booktitle);
			PrintWriter printWriter=resp.getWriter();
			ResultSet resultSet=preparedStatement2.executeQuery();
			if(resultSet.next())
			{
				preparedStatement.executeUpdate();
				printWriter.println("Book deleted!");
			}
			else
			{
				printWriter.println("check the title given and try again");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

