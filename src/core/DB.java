package core;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.sql.SQLException;

public class DB {
    private static final String DB_URL = "mongodb://localhost:27017";
    private static MongoDatabase database;
    private final MongoClient client;
    private static DB instance;

    private DB() throws SQLException {
        client = MongoClients.create(DB_URL);
        try {
            database = client.getDatabase("oakdonutsdb");
        } catch (Exception e) {
            client.close();
            e.printStackTrace();
        }
    }

    public static synchronized DB getInstance() {
        if (instance == null) {
            try {
                instance = new DB();
            } catch (SQLException ex) {
                throw new IllegalStateException("Unable to initialize MongoDB database", ex);
            }
        }
        return instance;
    }
}
