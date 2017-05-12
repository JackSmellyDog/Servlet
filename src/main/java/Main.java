import dao.AuthorDao;
import dao.PatentDao;
import dao.impl.AuthorMongoDao;
import dao.impl.AuthorSqlDao;
import dao.impl.PatentSqlDao;
import exceptions.DataBaseException;
import model.Author;
import model.Patent;

import java.sql.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, DataBaseException {
        AuthorDao dao = new AuthorSqlDao();
        dao.getAll().forEach(System.out::println);
    /*    System.out.println();
        System.out.println(dao.getById(2));
        Author deleted = dao.getById(4);
        dao.deleteById(4);
        dao.insert(deleted);
        dao.update(5, new Author(5, "newName", "103"));

        System.out.println();
        System.out.println();
        System.out.println();

        PatentDao patentDao = new PatentSqlDao();
        patentDao.getAll().forEach(System.out::println);
        System.out.println();
        System.out.println(patentDao.getById(2));
        Patent deletedPatent = patentDao.getById(4);
        patentDao.deleteById(4);
        patentDao.insert(deletedPatent);
        patentDao.update(5, new Patent(5, "newName", deleted, "lsfjl", "slfkj", Calendar.getInstance()));
*/

        //AuthorMongoDao mongoDao = new AuthorMongoDao();

        /*mongoDao.insert(new Author(5, "testUpdate", "55555"));
        System.out.println(mongoDao.getById(5));

        mongoDao.update(5, new Author(5, "victory", "444444"));
        System.out.println(mongoDao.getById(5));*/

        //mongoDao.getAll().forEach(System.out::println);

    }
}
