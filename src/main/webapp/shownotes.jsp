<%@page import="com.entity.notes"%>
<%@page import="com.fecpro.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.query.Query"%>
<%@page import="java.util.List"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>

<!doctype html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

  <title>Show Notes</title>

  <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
  <div class="container">
    <%@include file="navbar.jsp" %>
    <h1>Show All Notes:</h1>
  </div>  
  <div class="container">
    <div class="row">
      <div class="col-12">
        <%
          // Open a session and begin transaction
          Session s = FactoryProvider.getFactory().openSession();
          Transaction tx = s.beginTransaction();
          
          // Create and execute query
          Query<notes> query = s.createQuery("from notes", notes.class);
          List<notes> notesList = query.list();
          
          // Commit transaction and close session
          tx.commit();
          s.close();
          
          // Display notes
          if (notesList != null && !notesList.isEmpty()) {
            out.println("<table class='table table-bordered'>");
            out.println("<thead><tr><th>ID</th><th>Title</th><th>Content</th><th>Actions</th></tr></thead>");
            out.println("<tbody>");
            for (notes note : notesList) {
              out.println("<tr>");
              out.println("<td>" + note.getId() + "</td>");
              out.println("<td>" + note.getTitle() + "</td>");
              out.println("<td>" + note.getContent() + "</td>");
              out.println("<td>");
              out.println("<a href='update.jsp?id=" + note.getId() + "' class='btn btn-warning btn-sm'>Update</a> ");
              out.println("<a href='deleteservlet?noteId=" + note.getId() + "' class='btn btn-danger btn-sm'>Delete</a>");
              out.println("</td>");
              out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
          } else {
            out.println("<p>No notes available.</p>");
          }
        %>
      </div>
    </div>
  </div>

  <!-- Optional JavaScript; choose one of the two! -->
  <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>
