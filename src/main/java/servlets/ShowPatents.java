package servlets;

import dao.PatentDao;
import dao.impl.PatentSqlDao;
import exceptions.DataBaseException;
import model.Patent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ShowPatents")
public class ShowPatents extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PatentDao dao = new PatentSqlDao();
        resp.setContentType("text/html");
        try {
            List<Patent> patents = dao.getAll();
            StringBuilder builder = new StringBuilder();
            builder.append("<table>")
                    .append("<tr>")
                    .append("<td><strong>Id</strong></td>")
                    .append("<td><strong>Name</strong></td>")
                    .append("<td><strong>Author</strong></td>")
                    .append("<td><strong>Formula</strong></td>")
                    .append("<td><strong>Essay</strong></td>")
                    .append("<td><strong>Delete</strong></td>")
                    .append("</tr>");

            for (Patent patent : patents){
                builder.append("<tr>")
                        .append("<td>").append(patent.getId()).append("</td>")
                        .append("<td>").append(patent.getName()).append("</td>")
                        .append("<td>").append(patent.getAuthor().getName()).append("</td>")
                        .append("<td>").append(patent.getFormula()).append("</td>")
                        .append("<td>").append(patent.getEssay()).append("</td>")
                        .append("<td>")
                        .append("<a href = 'DeletePatent?id=").append(patent.getId()).append("'>X</a>")
                        .append("</td>")
                        .append("</tr>");
            }

            builder.append("</table>");

            PrintWriter writer = resp.getWriter();
            writer.println(builder.toString());
            writer.println("<br><a href = 'index.jsp'><< back</a>");
            writer.close();

        } catch (DataBaseException e) {
            System.out.println("Patents error");
        }
    }
}
