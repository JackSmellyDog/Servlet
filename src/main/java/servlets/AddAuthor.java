package servlets;


import dao.AuthorDao;
import dao.impl.AuthorSqlDao;
import exceptions.DataBaseException;
import model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddAuthor")
public class AddAuthor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        try {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("fullName");
            String phone = req.getParameter("phone");

            if (name  == null || name.isEmpty() || phone  == null || phone.isEmpty()){
                throw new Exception();
            }

            AuthorDao dao = new AuthorSqlDao();
            try {
                dao.insert(new Author(id, name, phone));
                PrintWriter writer = resp.getWriter();
                writer.println("<h2>Author has been added</h2>");
                writer.println("<br><a href = 'index.jsp'><< back</a>");
                writer.close();
            } catch (DataBaseException e) {
                System.out.println("Terrible error!!!");
            }
        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>Error author</h2>");
            writer.println("<br><a href = 'index.jsp'><< back</a>");
            writer.close();
        }

    }
}
