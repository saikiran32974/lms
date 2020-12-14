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
@WebServlet("/UpdateBook")

public class UpdateBook extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		String booktitle=req.getParameter("title");
		String edition=req.getParameter("edition");
		
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="update librarymanagementsystem.lms set edition=? where booktitle=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, edition);
			preparedStatement.setString(2, booktitle);
			
			String query2="select booktitle from librarymanagementsystem.lms where booktitle=?";
			PreparedStatement preparedStatement2=connection.prepareStatement(query2);
			preparedStatement2.setString(1, booktitle);
			PrintWriter printWriter=resp.getWriter();
			ResultSet resultSet=preparedStatement2.executeQuery();
			
			if(resultSet.next())
			{
				preparedStatement.executeUpdate();
				printWriter.println("Book Update Successfully");
			}
			else
			{
				printWriter.println("Please check the book title");
			}
			connection.close();
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		
	}

}

