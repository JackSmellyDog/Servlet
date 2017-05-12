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
import java.util.List;

@WebServlet("/ShowAuthors")
public class ShowAuthors extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDao dao = new AuthorSqlDao();
        resp.setContentType("text/html");
        try {
            List<Author> authors = dao.getAll();
            StringBuilder builder = new StringBuilder();
            builder.append("<table>")
                    .append("<tr>")
                    .append("<td><strong>Id</strong></td>")
                    .append("<td><strong>Name</strong></td>")
                    .append("<td><strong>Phone</strong></td>")
                    .append("</tr>");

            for (Author author : authors){
                builder.append("<tr>")
                        .append("<td>").append(author.getId()).append("</td>")
                        .append("<td>").append(author.getName()).append("</td>")
                        .append("<td>").append(author.getPhone()).append("</td>")
                        .append("</tr>");
            }

            builder.append("</table>");

            PrintWriter writer = resp.getWriter();
            writer.println(builder.toString());
            writer.println("<br><a href = 'index.jsp'><< back</a>");
            writer.close();

        } catch (DataBaseException e) {
            System.out.println("ShowAuthors error");
        }
    }
}
