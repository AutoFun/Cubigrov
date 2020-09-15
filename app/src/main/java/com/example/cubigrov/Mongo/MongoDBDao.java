package com.example.cubigrov.Mongo;

import java.util.ArrayList;
import java.util.List;


import com.example.cubigrov.Mongo.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class MongoDBDao {
    private static MongoDBUtil mongoDb;
    // Init. MongoDBUtil.
    static{
        try {
            mongoDb = new MongoDBUtil("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test Create a blanket collection.
     * @param collName :collection name.
     */
    public void createCollectionTest(String collName)
    {
        mongoDb.createCollection(collName);
    }

    /**
     * Test Insert dbObject into collection.
     * @param collName Collection Name.
     */
    public void insertTest(String collName)
    {
        DBObject dbs = new BasicDBObject();
        dbs.put("name", "gymyung");
        dbs.put("age", 20);
        List<String> books = new ArrayList<String>();
        books.add("EXTJS");
        books.add("MONGDB");
        books.add("JAVA");
        dbs.put("books", books);
        mongoDb.insert(dbs, collName);
    }

    /**
     * Test Insert dbObject list into collection.
     * @param collName Collection Name.
     */
    public void insertBatchTest(String collName)
    {
        List<DBObject> dbObjects = new ArrayList<DBObject>();
        DBObject jim2 = new BasicDBObject("name", "jim2");
        DBObject liuting = new BasicDBObject();
        liuting.put("name", "liuting");
        liuting.put("age", "22");

        dbObjects.add(jim2);
        dbObjects.add(liuting);

        mongoDb.insertBatch(dbObjects, collName);
    }

    /**
     * Test Delete data By Id.
     * @param collName Collection Name.
     * @return Operate Result Code.
     */
    public int deleteByIdTest(String collName)
    {
        int counts = mongoDb.deleteById("54507d19cbbd7a385c129ef5", collName);
        return counts;
    }

    /**
     * Test Delete data By Condition.
     * @param collName Collection Name.
     * @return Operate Result Code.
     */
    public int deleteByDbsTest(String collName)
    {
        DBObject jim2 = new BasicDBObject("name", "jim2");
        int count = mongoDb.deleteByDbs(jim2, collName);
        return count;
    }

    /**
     * Test Update Data.
     * @param collName Collection Name.
     * @return Operate Result Code.
     */
    public int updateTest(String collName)
    {
        DBObject liuting = new BasicDBObject();
        DBObject liuting2 = new BasicDBObject();
        liuting2.put("$set", new BasicDBObject("gender", "female"));

        int count = mongoDb.update(liuting, liuting2, false, true, collName);
        return count;
    }

    /**
     * Test Find Data With Page.
     * @param collName Collection Name.
     * @return String List Result.
     */
    public List<String> findWithPageTest(String collName)
    {
        DBCursor cursor = mongoDb.findWithPage(null, null, 0, 3, collName);
        return convertCursorToList(cursor);
    }

    /**
     * Test Find Data With Condition.
     * @param collName Collection Name.
     * @return String List Result.
     */
    public List<String> findWithConditionTest(String collName)
    {
        DBObject where = new BasicDBObject();
        where.put("age", new BasicDBObject("$lte", 26));
        where.put("gender", "female");

        DBCursor cursor = mongoDb.findNoPage(where, null,collName);
        return convertCursorToList(cursor);
    }


    /**
     * Test Find Data No Page.
     * @param collName Collection Name.
     * @return String List Result.
     */
    public List<String> findNoPageTest(String collName)
    {
        DBObject keys = new BasicDBObject();
        keys.put("_id", false);
        keys.put("name", true);
        keys.put("age", true);

        DBCursor cursor = mongoDb.findNoPage(null, keys, collName);

        return convertCursorToList(cursor);
    }

    /**
     * Convert Cursor To List.
     * @param cursor Required DBCursor.
     * @return String List Result.
     */
    private List<String> convertCursorToList(DBCursor cursor)
    {
        List<String> results = new ArrayList<String>();
        while(cursor.hasNext())
        {
            DBObject dbObject = cursor.next();

            for(String key : dbObject.keySet())
            {
                results.add("{"+key+":"+dbObject.get(key)+"}");
            }
        }

        return results;
    }
}