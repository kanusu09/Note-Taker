package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Transaction;

import com.entity.notes;
import com.fecpro.FactoryProvider;

import jakarta.mail.Session;

import javax.servlet.RequestDispatcher;

public class updateservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
     HttpSession ss = request.getSession();
        // Get parameters from request
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int id =(int) ss.getAttribute("id"); // Ensure you are getting the ID
 
        // Convert ID to long

        org.hibernate.Session s = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();

            // Fetch the existing note from the database
            notes note = s.get(notes.class, id);

            if (note == null) {
                out.println("Error: Note with ID " + id + " does not exist.");
                return;
            }

            // Update the note's properties
            note.setTitle(title);
            note.setContent(content);
            note.setAddeddate(new Date()); // Update the date or any other field

            // Save changes
            s.update(note);
            tx.commit();

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
            out.println("<h1>Note updated successfully!</h1>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            // Delay and forward to JSP
            Thread.sleep(3000);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("shownotes.jsp");

            // Forward request and response
            dispatcher.forward(request, response);
       
    
           
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
}}
