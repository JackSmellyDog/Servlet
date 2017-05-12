package dao.impl;


import dao.AuthorDao;
import dao.DBProperties;
import exceptions.DataBaseException;
import model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorSqlDao implements AuthorDao {
    public List<Author> getAll() throws DataBaseException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()
        ){
            List<Author> authors = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM authors");
            while (resultSet.next()){
                authors.add(new Author(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone")
                ));
            }
            return authors;
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    public Author getById(long id) throws DataBaseException {

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()
        ){

            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM authors WHERE id = %d", id)
            );

            if (!resultSet.next()){
                throw new DataBaseException();
            }

            return new Author(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone")
            );
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    public void deleteById(long id) throws DataBaseException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement("DELETE FROM authors WHERE id = ?")
        ){
            preparedStmt.setLong(1, id);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new DataBaseException();
        }

    }

    public void insert(Author item) throws DataBaseException {
        try(Connection connection = getConnection(); PreparedStatement preparedStmt = connection.prepareStatement(
                    "INSERT INTO authors (id, name, phone) VALUES (?, ?, ?)"
        )){

            preparedStmt.setLong(1, item.getId());
            preparedStmt.setString(2, item.getName());
            preparedStmt.setString(3, item.getPhone());

            preparedStmt.execute();

        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    @Override
    public void update(long id, Author item) throws DataBaseException {
        try(Connection connection = getConnection(); PreparedStatement preparedStmt = connection.prepareStatement(
                "UPDATE authors SET name = ?, phone = ? where id = ?;"
        )){

            preparedStmt.setString(1, item.getName());
            preparedStmt.setString(2, item.getPhone());
            preparedStmt.setLong(3, id);

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
