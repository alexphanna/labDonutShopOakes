package core;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DB {
    private static final String URL = "mongodb://localhost:27017";
    private MongoDatabase database;
    private MongoClient client;

    public DB() {
        try (MongoClient _client = MongoClients.create(URL)) {
            client = _client;
            // Access the database and collection
            database = client.getDatabase("menu");
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
