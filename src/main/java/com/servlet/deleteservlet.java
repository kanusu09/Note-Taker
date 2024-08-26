package com.servlet;

import com.fecpro.FactoryProvider;
import com.entity.notes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class deleteservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int noteId = Integer.parseInt(request.getParameter("noteId"));

        Session s= FactoryProvider.getFactory().openSession();
        Transaction tx = s.beginTransaction();

        notes note = s.get(notes.class, noteId);
        if (note != null) {
            s.delete(note);
        }

        tx.commit();
        s.close();

        response.sendRedirect("shownotes.jsp"); // Redirect to the page showing all notes
    }
}
