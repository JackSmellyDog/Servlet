<%@ page import="dao.AuthorDao" %>
<%@ page import="dao.impl.AuthorSqlDao" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Author" %>
<%@ page import="exceptions.DataBaseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Patent</title>
</head>
<body>

    <form method="POST" action="AddPatent">
        <table>
            <tr>
                <td>Id:</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Author:</td>
                <td><%
                    AuthorDao dao = new AuthorSqlDao();
                    try (PrintWriter writer = response.getWriter()){
                        for (Author author : dao.getAll()){
                            writer.println(author + "<br>");
                        }
                    } catch (DataBaseException e) {
                        System.out.println("jsp patent add error");
                    }
                %></td>
            </tr>

            <tr>
                <td>Formula:</td>
                <td><textarea name="formula"></textarea></td>
            </tr>

            <tr>
                <td>Essay:</td>
                <td><textarea name="essay"></textarea></td>
            </tr>

            <tr>
                <td colspan="2" align="right"><input type="submit" value="Add"></td>
            </tr>
        </table>
    </form>

</body>
</html>
