package servlets;

import dao.PatentDao;
import dao.impl.PatentSqlDao;
import exceptions.DataBaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeletePatent")
public class DeletePatent extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PatentDao patentDao = new PatentSqlDao();
        long id = Long.parseLong(req.getParameter("id"));
        try {
            patentDao.deleteById(id);
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>Patent has been deleted</h2>");
            writer.println("<br><a href = 'index.jsp'><< back</a>");
            writer.close();
        } catch (DataBaseException e) {
            System.out.println("Patent deleting error!");
        }
    }
}
