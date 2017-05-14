package servlets;

import dao.AuthorDao;
import dao.PatentDao;
import dao.impl.AuthorSqlDao;
import dao.impl.PatentSqlDao;
import exceptions.DataBaseException;
import model.Author;
import model.Patent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet("/AddPatent")
public class AddPatent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            long author_id = Long.parseLong(req.getParameter("author_id"));
            String formula = req.getParameter("formula");
            String essay = req.getParameter("essay");

            if (name  == null || name.isEmpty() || formula  == null || formula.isEmpty() || essay  == null || essay.isEmpty()){
                 throw new Exception();
            }

            PatentDao dao = new PatentSqlDao();
            AuthorDao authorDao = new AuthorSqlDao();
            try {
                dao.insert(new Patent(id, name, authorDao.getById(author_id), formula, essay, Calendar.getInstance()));
                PrintWriter writer = resp.getWriter();
                writer.println("<h2>Patent has been added</h2>");
                writer.println("<br><a href = 'index.jsp'><< back</a>");
                writer.close();
            } catch (DataBaseException e) {
                System.out.println("Terrible error!!!");
            }
        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>Error patent</h2>");
            writer.println("<br><a href = 'index.jsp'><< back</a>");
            writer.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        StringBuilder selectBuilder = new StringBuilder();
        AuthorDao dao = new AuthorSqlDao();

        try {
            selectBuilder.append("<select name =\"author_id\">");
            for (Author author : dao.getAll()){
                selectBuilder.append("<option value=").append(author.getId()).append(">")
                        .append(author.getName())
                        .append("</option>");
            }
            selectBuilder.append("</select>");
        } catch (DataBaseException e) {
            System.out.println("error patent with author");
        }

        builder.append("<h2>Add patent</h2>")
                .append("<form method=\"POST\" action=\"AddPatent\">")
                .append("<table>")
                .append("<tr><td>Id:</td><td><input type=\"text\" name=\"id\"></td></tr>")
                .append("<tr><td>Name:</td><td><input type=\"text\" name=\"name\"></td></tr>")
                .append("<tr><td>Author:</td><td>").append(selectBuilder.toString()).append("</td></tr>")
                .append("<tr><td>Formula:</td><td><textarea name=\"formula\"></textarea></td></tr>")
                .append("<tr><td>Essay:</td><td><textarea name=\"essay\"></textarea></td></tr>")
                .append("<tr><td colspan=\"2\" align=\"right\"><input type=\"submit\" value=\"Add\"></td></tr>")
                .append("</table>")
                .append("</form>");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(builder.toString());
        writer.println("<br><a href = 'index.jsp'><< back</a>");
        writer.close();
    }
}
