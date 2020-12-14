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

@WebServlet("/SearchBook")
public class SearchBook extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
	String booktitle=req.getParameter("BT");
	
	String url="jdbc:mysql://localhost:3306?user=root&password=12345";
	String query="select * from librarymanagementsystem.lms where bookTitle=?";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1,booktitle);
		ResultSet resultSet=preparedStatement.executeQuery();
		PrintWriter printWriter=resp.getWriter();
		if(resultSet.next())
		{
			String book=resultSet.getString("bookTitle");
			String author=resultSet.getString("author");
			String edition=resultSet.getString("edition");
			String price=resultSet.getString("price");
			String type=resultSet.getString("author");
			printWriter.println("Booktitle : "+book+"BookAuthor : "+author+"BookEdition : "+edition+"BookPrice : "+price+"BookType : "+type);
			printWriter.println("Book details are printed successfully");
			}
		else {
			printWriter.println("Enter valid book title");
		}
		
		connection.close();
	} catch( Exception e) {
		
		e.printStackTrace();
	}
	
	
	
	
	
	}

}

