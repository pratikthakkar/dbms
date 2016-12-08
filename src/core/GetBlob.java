package core;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.sql.Blob;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/GetBlob" })
public class GetBlob extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException

	{
		try

		{
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "niyati@22");

			PreparedStatement ps = con.prepareStatement("select image from bstore where name = ?");

			String name = request.getParameter("name");
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			rs.next();

			Blob b = rs.getBlob("image");
			response.setContentType("image/jpeg");

			response.setContentLength((int) b.length());

			InputStream is = b.getBinaryStream();

			OutputStream os = response.getOutputStream();

			byte buf[] = new byte[(int) b.length()];

			is.read(buf);

			os.write(buf);

			os.close();

		}

		catch (Exception ex)

		{

			System.out.println(ex.getMessage());

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
