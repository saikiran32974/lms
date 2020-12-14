package org.jsp.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/AddBook")
public class AddBook extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		{
			String booktitle=req.getParameter("BT");
			String author=req.getParameter("AT");
			String edition=req.getParameter("ET");
			String price=req.getParameter("PT");
			String type=req.getParameter("TT");
			
			//jdbc//
			String url="jdbc:mysql://localhost:3306?user=root&password=12345";
			String query="insert into librarymanagementsystem.lms values(?,?,?,?,?)";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection(url);
				
				System.out.println("step2");
				
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1, booktitle);
				preparedStatement.setString(2, author);
				preparedStatement.setString(3, edition);
				preparedStatement.setString(4, price);
				preparedStatement.setString(5, type);
				
				preparedStatement.executeUpdate();
				System.out.println("step4");
				
				PrintWriter printWriter=resp.getWriter();
				printWriter.println("Book Added Successfully");
				connection.close();
				} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

