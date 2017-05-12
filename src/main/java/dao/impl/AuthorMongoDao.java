package dao.impl;

import com.mongodb.*;
import dao.AuthorDao;
import dao.DBProperties;
import exceptions.DataBaseException;
import model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorMongoDao implements AuthorDao {
    private MongoClient mongoClient;
    private DB db;
    private DBCollection table;


    public List<Author> getAll() throws DataBaseException {
        List<Author> list = new ArrayList<>();
        getConnection();

        DBCursor cursor = table.find();

        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();
            list.add(new Author(
                    (long) Double.parseDouble(dbObject.get("id").toString()),
                    (String )dbObject.get("name"),
                    (String )dbObject.get("phone")
            ));
        }
        cursor.close();
        mongoClient.close();
        return list;
    }

    public Author getById(long id) throws DataBaseException {
        getConnection();
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = table.find(query);

        DBObject dbObject;

        if (cursor.hasNext()) {
            dbObject = cursor.next();
        } else {
            throw  new DataBaseException();
        }

        return new Author(
                (long) Double.parseDouble(dbObject.get("id").toString()),
                (String )dbObject.get("name"),
                (String )dbObject.get("phone")
        );
    }

    public void deleteById(long id) throws DataBaseException {
        getConnection();

        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        table.remove(query);
        mongoClient.close();
    }

    public void insert(Author item) throws DataBaseException{
        BasicDBObject document = new BasicDBObject();
        getConnection();

        document.put("id", item.getId());
        document.put("name", item.getName());
        document.put("phone", item.getPhone());

        table.insert(document);
        mongoClient.close();
    }

    @Override
    public void update(long id, Author item) throws DataBaseException {
        getConnection();

        BasicDBObject newData = new BasicDBObject();
        newData.put("id", item.getId());
        newData.put("name", item.getName());
        newData.put("phone", item.getPhone());

        BasicDBObject query = new BasicDBObject().append("id", id);

        table.update(query, newData);
        mongoClient.close();
    }

    public void getConnection() throws DataBaseException {
        try {
            mongoClient = new MongoClient(DBProperties.HOST, DBProperties.PORT);
            db = mongoClient.getDB(DBProperties.MONGODB_NAME);
            table = db.getCollection("authors");
        } catch (Exception e){
            throw new DataBaseException();
        }
    }
}
