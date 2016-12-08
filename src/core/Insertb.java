package core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.logging.Level;

import java.util.logging.Logger;

/**
 * Servlet implementation class Insertb
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Insertb" })
public class Insertb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Insertb() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String filePath = request.getParameter("filePath");
		FileInputStream is = null;
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		try {

			// Connect to Oracle out.println("<html>"); out.println("<body>");

			Class.forName("com.mysql.jdbc.Driver");

			Connection

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "niyati@22");

			PreparedStatement ps = con.prepareStatement("insert into bstore values(?,?)");

			ps.setString(1, name);

			File fBlob = new File(filePath);

			is = new FileInputStream(fBlob);
			ps.setBinaryStream(2, is, (int) fBlob.length());
			ps.executeUpdate();

			if (is != null)
				is.close();

			out.println("Image sucessfully inserted into database");
			out.println("</body>");

			out.println("</html>");

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

		finally {
			out.close();

		}

	}

	public static void main(String args[]) {
		System.out.println("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
