package net.tutorial.utilities;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoService {

	private static MongoService instance = new MongoService();
	private MongoClient mongoClient = null;
	private DB db = null;

	private MongoService() {
	}

	public static MongoService getInstance() {
		return instance;
	}

	public void connect() {
		try {
			String textUri = "mongodb://admin:password@ds161169.mlab.com:61169/addressbookdb";
			MongoClientURI uri = new MongoClientURI(textUri);
			this.mongoClient = new MongoClient(uri);
			this.db = this.mongoClient.getDB("addressbookdb");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		this.mongoClient.close();
	}

	public void create(Map<String, String> document) {
		DBCollection coll = this.db.getCollection("contacts");
		BasicDBObject doc = new BasicDBObject(document);
		coll.insert(doc);
	}

	public ArrayList<Map<String, String>> allDocuments() {
		DBCollection coll = this.db.getCollection("contacts");
		DBCursor cursor = coll.find();

		ArrayList<Map<String, String>> documents = new ArrayList<Map<String, String>>();
		Map<String, String> document = null;

		try {
			while (cursor.hasNext()) {

				DBObject obj = cursor.next();
				document = new HashMap<String, String>();

				document.put("id", obj.get("_id").toString());
				document.put("name", obj.get("name").toString());
				document.put("email", obj.get("email").toString());
				document.put("mobile", obj.get("mobile").toString());

				documents.add(document);

			}
		} finally {
			cursor.close();
		}

		return documents;
	}

	public void delete(String id) {
		BasicDBObject document = new BasicDBObject();
		DBCollection coll = this.db.getCollection("contacts");
		document.put("_id", new ObjectId(id));
		coll.remove(document);
	}

	public Map<String, String> findOne(String id) {
		Map<String, String> document = new HashMap<String, String>();
		DBCollection coll = this.db.getCollection("contacts");
		DBObject obj = coll.findOne(new ObjectId(id));

		document.put("id", obj.get("_id").toString());
		document.put("name", obj.get("name").toString());
		document.put("email", obj.get("email").toString());
		document.put("mobile", obj.get("mobile").toString());

		return document;
	}

	public void update(String id, Map<String, String> document) {
		BasicDBObject newDocument = new BasicDBObject();
		BasicDBObject query = new BasicDBObject().append("_id", new ObjectId(id));
		DBCollection coll = this.db.getCollection("contacts");

		newDocument.append("$set", new BasicDBObject().append("name", document.get("name"))
				.append("email", document.get("email")).append("mobile", document.get("mobile")));
		
		coll.update(query, newDocument);
	}

}
