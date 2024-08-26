package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.notes;
import com.fecpro.FactoryProvider;

import jakarta.servlet.ServletResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addServlet
 */

public class addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// TODO Auto-generated method stub
		try {
			String title = request.getParameter("title");
 			String content = request.getParameter("content");
			notes note = new notes(title, content, new Date());

			Session s = FactoryProvider.getFactory().openSession();

			Transaction tx = s.beginTransaction();
			s.save(note);
			tx.commit();
			s.close();
			 out.println("<html>");
			    out.println("<head>");
			    out.println("<style>");
			    out.println("body { font-family: Arial, sans-serif; }");
			    out.println(".container {");
			    out.println("    text-align: center;");
			    out.println("    margin-top: 50px;");
			    out.println("}");
			    out.println("h1 {");
			    out.println("    color: #4CAF50;");
			    out.println("}");
			    out.println(".link {");
			    out.println("    display: block;");
			    out.println("    margin-top: 20px;");
			    out.println("    font-size: 18px;");
			    out.println("    color: #2196F3;");
			    out.println("    text-decoration: none;");
			    out.println("}");
			    out.println(".link:hover {");
			    out.println("    text-decoration: underline;");
			    out.println("}");
			    out.println("</style>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<div class='container'>");
			    out.println("<h1>Note added successfully!</h1>");
			    out.println("<a href='shownotes.jsp' class='link'>View All Notes</a>");
			    out.println("</div>");
			    out.println("</body>");
			    out.println("</html>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
