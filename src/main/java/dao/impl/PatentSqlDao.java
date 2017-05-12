package dao.impl;

import dao.DBProperties;
import dao.PatentDao;
import exceptions.DataBaseException;
import model.Author;
import model.Patent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PatentSqlDao implements PatentDao {
    public List<Patent> getAll() throws DataBaseException{

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()
        ){
            List<Patent> patents = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM patents, authors WHERE patents.author_id = authors.id;"
            );

            while (resultSet.next()){
                patents.add(new Patent(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Author(
                                resultSet.getLong("author_id"),
                                resultSet.getString(7), // that's shitty
                                resultSet.getString("phone")
                        ),
                        resultSet.getString("formula"),
                        resultSet.getString("essay"),
                        Calendar.getInstance()
                ));
            }
            return patents;
        } catch (SQLException e) {
            throw new DataBaseException();
        }

    }

    public Patent getById(long id) throws DataBaseException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT * FROM patents, authors WHERE patents.author_id = authors.id AND patents.id = %s;",
                    id
            ));

            if (!resultSet.next()){
                throw new DataBaseException();
            }

            return new Patent(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    new Author(
                            resultSet.getLong("author_id"),
                            resultSet.getString(7), // that's shitty
                            resultSet.getString("phone")
                    ),
                    resultSet.getString("formula"),
                    resultSet.getString("essay"),
                    Calendar.getInstance()
            );
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    public void deleteById(long id) throws DataBaseException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement("DELETE FROM patents WHERE id = ?")
        ){
            preparedStmt.setLong(1, id);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    public void insert(Patent item) throws DataBaseException {
        try(Connection connection = getConnection(); PreparedStatement preparedStmt = connection.prepareStatement(
                "INSERT INTO patents (id, name, formula, essay, author_id) VALUES (?, ?, ?, ?, ?)"
        )){

            preparedStmt.setLong(1, item.getId());
            preparedStmt.setString(2, item.getName());
            preparedStmt.setString(3, item.getFormula());
            preparedStmt.setString(4, item.getEssay());
            preparedStmt.setLong(5, item.getAuthor().getId());

            preparedStmt.execute();

        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    @Override
    public void update(long id, Patent item) throws DataBaseException {
        try(Connection connection = getConnection(); PreparedStatement preparedStmt = connection.prepareStatement(
                "UPDATE patents SET name = ?, formula = ?, essay = ?, author_id = ?  where id = ?;"
        )){

            preparedStmt.setString(1, item.getName());
            preparedStmt.setString(2, item.getFormula());
            preparedStmt.setString(3, item.getEssay());
            preparedStmt.setLong(4, item.getAuthor().getId());
            preparedStmt.setLong(5, id);

            preparedStmt.execute();

        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    private Connection getConnection() throws DataBaseException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    DBProperties.JDBC_URL,
                    DBProperties.LOGIN,
                    DBProperties.PASSWORD
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataBaseException();
        }
    }
}
