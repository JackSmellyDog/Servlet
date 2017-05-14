import dao.AuthorDao;
import dao.impl.AuthorSqlDao;
import model.Author;
import org.junit.Assert;
import org.junit.Test;


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

    }

    @Test
    public void MySQLPatentInsertTest() throws Exception {

    }

    @Test
    public void MySQLPatentUpdateTest() throws Exception {

    }

    @Test
    public void MySQLPatentDeleteTest() throws Exception {

    }

    @Test
    public void MongoGetTest() throws Exception {

    }

    @Test
    public void MongoInsertTest() throws Exception {

    }

    @Test
    public void MongoUpdateTest() throws Exception {

    }

    @Test
    public void MongoDeleteTest() throws Exception {

    }
}