package core;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/GetB" })
public class Getb extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{

		String id;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try

		{

			out.println("<html>");

			out.println("<body>");

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "niyati@22");

			PreparedStatement ps = con.prepareStatement("select * from bstore");
			ResultSet rs = ps.executeQuery();
			out.println("<h1>Photos</h1>");

			while (rs.next())

			{

				id = rs.getString("name");
				out.println("<h4>" + id + "</h4>");

				out.println("<img width='160' height='160' src=GetBlob?name=" + id + "></img><p/>");

			}

			out.println("</body>");

			out.println("</html>");

			con.close();

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

		finally

		{

			out.close();

		}

	}

}
