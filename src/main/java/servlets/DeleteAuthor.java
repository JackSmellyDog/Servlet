package servlets;


import dao.AuthorDao;
import dao.impl.AuthorSqlDao;
import exceptions.DataBaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteAuthor")
public class DeleteAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        AuthorDao authorDao = new AuthorSqlDao();
        long id = Long.parseLong(req.getParameter("id"));
        try {
            authorDao.deleteById(id);
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>Author has been deleted</h2>");
            writer.println("<br><a href = 'index.jsp'><< back</a>");
            writer.close();
        } catch (DataBaseException e) {
            System.out.println("Authors deleting error!");
        }

    }
}
