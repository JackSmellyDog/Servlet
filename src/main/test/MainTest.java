import dao.AuthorDao;
import dao.PatentDao;
import dao.impl.AuthorMongoDao;
import dao.impl.AuthorSqlDao;
import dao.impl.PatentSqlDao;
import model.Author;
import model.Patent;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;


public class MainTest {

    @Test
    public void MySQLAuthorGetTest() throws Exception {
        AuthorDao authorDao = new AuthorSqlDao();
        Author author = new Author(1, "Ivanov Ivan Ivanovich", "11111111111");

        Assert.assertEquals(author, authorDao.getById(1));
    }

    @Test
    public void MySQLAuthorInsertTest() throws Exception {
        AuthorDao authorDao = new AuthorSqlDao();
        Author author = new Author(99, "Test User", "999");
        authorDao.insert(author);

        Assert.assertEquals(author, authorDao.getById(99));

        authorDao.deleteById(99);
    }

    @Test
    public void MySQLAuthorUpdateTest() throws Exception {
        AuthorDao authorDao = new AuthorSqlDao();
        Author author = new Author(99, "Test User", "999");
        authorDao.insert(author);

        Author newAuthor = new Author(99, "New Test User", "111");
        authorDao.update(99, newAuthor);

        Assert.assertEquals(newAuthor, authorDao.getById(99));
        Assert.assertNotEquals(author, authorDao.getById(99));

        authorDao.deleteById(99);
    }

    @Test
    public void MySQLAuthorDeleteTest() throws Exception {
        AuthorDao authorDao = new AuthorSqlDao();
        Author author = new Author(99, "Test User", "999");
        authorDao.insert(author);

        Assert.assertTrue(authorDao.getAll().contains(author));
        authorDao.deleteById(99);
        Assert.assertFalse(authorDao.getAll().contains(author));
    }

    @Test
    public void MySQLPatentGetTest() throws Exception {
        Author author = new Author(1, "Ivanov Ivan Ivanovich", "11111111111");
        Patent patent = new Patent(1, "Internet", author, "fffffffff", "eeeeeeeee", Calendar.getInstance());

        PatentDao patentDao = new PatentSqlDao();

        Assert.assertEquals(patent, patentDao.getById(1));
    }

    @Test
    public void MySQLPatentInsertTest() throws Exception {
        PatentDao patentDao = new PatentSqlDao();
        Author author = new Author(1, "Ivanov Ivan Ivanovich", "11111111111");
        Patent patent = new Patent(99, "Test Patent", author, "testF", "testE", Calendar.getInstance());
        patentDao.insert(patent);

        Assert.assertEquals(patent, patentDao.getById(99));

        patentDao.deleteById(99);
    }

    @Test
    public void MySQLPatentUpdateTest() throws Exception {
        PatentDao patentDao = new PatentSqlDao();
        Author author = new Author(1, "Ivanov Ivan Ivanovich", "11111111111");
        Patent patent = new Patent(99, "Test Patent", author, "testF", "testE", Calendar.getInstance());
        patentDao.insert(patent);

        Patent newPatent = new Patent(99, "New Test Patent", author, "newTestF", "newTestE", Calendar.getInstance());
        patentDao.update(99, newPatent);

        Assert.assertEquals(newPatent, patentDao.getById(99));
        Assert.assertNotEquals(author, patentDao.getById(99));

        patentDao.deleteById(99);
    }

    @Test
    public void MySQLPatentDeleteTest() throws Exception {
        PatentDao patentDao = new PatentSqlDao();
        Author author = new Author(1, "Ivanov Ivan Ivanovich", "11111111111");
        Patent patent = new Patent(99, "Test Patent", author, "testF", "testE", Calendar.getInstance());
        patentDao.insert(patent);

        Assert.assertTrue(patentDao.getAll().contains(patent));
        patentDao.deleteById(99);
        Assert.assertFalse(patentDao.getAll().contains(patent));

    }

    @Test
    public void MongoGetTest() throws Exception {
        AuthorDao authorDao = new AuthorMongoDao();
        Author author = new Author(1, "Ivanov Ivan Ivanovich", "11111111111");
        Assert.assertEquals(author, authorDao.getById(1));

    }

    @Test
    public void MongoInsertTest() throws Exception {
        AuthorDao authorDao = new AuthorMongoDao();
        Author author = new Author(99, "Test User", "999");
        authorDao.insert(author);

        Assert.assertEquals(author, authorDao.getById(99));

        authorDao.deleteById(99);
    }

    @Test
    public void MongoUpdateTest() throws Exception {
        AuthorDao authorDao = new AuthorMongoDao();
        Author author = new Author(99, "Test User", "999");
        authorDao.insert(author);

        Author newAuthor = new Author(99, "New Test User", "111");
        authorDao.update(99, newAuthor);

        Assert.assertEquals(newAuthor, authorDao.getById(99));
        Assert.assertNotEquals(author, authorDao.getById(99));

        authorDao.deleteById(99);
    }

    @Test
    public void MongoDeleteTest() throws Exception {
        AuthorDao authorDao = new AuthorMongoDao();
        Author author = new Author(99, "Test User", "999");
        authorDao.insert(author);

        Assert.assertTrue(authorDao.getAll().contains(author));
        authorDao.deleteById(99);
        Assert.assertFalse(authorDao.getAll().contains(author));
    }
}