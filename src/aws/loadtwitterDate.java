package aws;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

import twitter.DATA;
import twitter.MyTweet;

public class loadtwitterDate {

	static AmazonDynamoDBClient dynamoDB;
	static AmazonDynamoDBClient Client;

	/*
	 * in diese Methode sollen wir an aws compte connecten und und eine Attribut
	 * dynamoDB stellen
	 */
	public void init() {
		try {
			BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("hh",
					"gg");
			
			 Client = new AmazonDynamoDBClient(basicAWSCredentials)
					.withEndpoint("http://dynamodb.us-west-2.amazonaws.com");
			dynamoDB = new AmazonDynamoDBClient(basicAWSCredentials);
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			dynamoDB.setRegion(usWest2);
		} catch (Exception e) {
			throw new AmazonClientException(e.toString());
		}

	}

	/*
	 * diese Methode erstellt eine neue Table in DynamoDB amazon
	 * 
	 */
	public void createTable(String tableName) {
		init();

		try {
			System.out.println("Attempting to create table; please wait...");
			// Create a table with a primary hash key named 'name', which holds
			// a string
			CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
					.withKeySchema(new KeySchemaElement().withAttributeName("ID").withKeyType(KeyType.HASH))
					.withAttributeDefinitions(
							new AttributeDefinition().withAttributeName("ID").withAttributeType(ScalarAttributeType.S))
					.withProvisionedThroughput(
							new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));

			// Create table if it does not exist yet
			TableUtils.createTableIfNotExists(dynamoDB, createTableRequest);
			// wait for the table to move into ACTIVE state
			TableUtils.waitUntilActive(dynamoDB, tableName);

			// Describe our new table
			DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
			TableDescription tableDescription = dynamoDB.describeTable(describeTableRequest).getTable();

			System.out.println("Success.  Table :" + tableName + ": " + tableDescription.getTableStatus());

		} catch (Exception e) {
			System.err.println("Unable to create table: ");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * in diese Methode wird die hshtag und die hashtagnummer in DynamoDB Table
	 * hochl�dt
	 * 
	 */
	public void loadNBdata(ArrayList<String> list, String tableName) {
		init();
		int nb;
		try {
			Map<String, AttributeValue> item;
			PutItemRequest putItemRequest;

			for (int j = 0; j < list.size(); j++) {

				String position = list.get(j);
				nb = 0;
				for (int i = 0; i < list.size(); i++) {
					if (position.equals(list.get(i)))
						nb++;
				}
				// wir erstellen eine neue Item in DynamoDB Table
				item = newNbItem(position, nb);
				putItemRequest = new PutItemRequest(tableName, item);
				dynamoDB.putItem(putItemRequest);
				System.out.println("PutItem succeeded: " + position + " -----" + nb);
			}
		} catch (Exception e) {
			System.err.println("Unable to add Tweets: ");
			System.err.println(e.getMessage());

		}

	}

	public void loaddata(List<MyTweet> myTweets, String tableName) {
		init();
		int i = 1;
		Map<String, AttributeValue> item;
		PutItemRequest putItemRequest;
		for (MyTweet mt : myTweets) {
			try {
				/*
				 * manchmal hashtage und location werden null und wir koennen
				 * nicht eine attribut null in dynamoDB hochladen , deswegen wir
				 * sollen eine test machen , wenn Sie null sind , sie nehmmen -1
				 * 
				 */
				if (mt.getLocation().length() > 1) {
					// wir erstellen eine neue Item in DynamoDB Table
					item = newItem(mt.getId(), mt.getUser(), mt.getTimeTweet(), mt.getLocation(), mt.getContent());
					putItemRequest = new PutItemRequest(tableName, item);
					dynamoDB.putItem(putItemRequest);

					System.out.println("PutItem succeeded: " + i + "----" + mt.getUser() + " -----" + mt.getLocation()
							+ " -----" + mt.getLongitude());

					i++;
				}
			} catch (Exception e) {
				System.err.println("Unable to add Tweets: " + mt.getUser() + " " + mt.getLocation());
				System.err.println(e.getMessage());
				break;
			}
		}
	}
	/*
	 * diese Methode liest die Items der dynamodb Table
	 */
	public ArrayList<MyTweet> getdataTwitter(String tableName) {
       init();
       int i=0;
		ArrayList<MyTweet> myTweets = new ArrayList<MyTweet>();

		DynamoDB dynamo = new DynamoDB(Client);

		Table table = dynamo.getTable(tableName);

		try {
			// recuperer les donnee de dynamodb
			ItemCollection<ScanOutcome> items = table.scan();

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();

				MyTweet myTweet = new MyTweet();
				myTweet.setId((String) item.get("ID"));
				myTweet.setDate((String) item.get("date"));
				myTweet.setUser((String) item.get("user"));
				myTweet.setLocation((String) item.get("location"));
				myTweet.setHashtage((String) item.get("hashtag"));
				myTweets.add(myTweet);
    i++;
				System.out.println("PutItem succeeded: " + i + "----" + myTweet.getUser() + " -----" + myTweet.getLocation()
				+ " -----" + myTweet.getLongitude());
			}

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}
		return myTweets;

	}
	public ArrayList<DATA>  getdata(String tableName) {
	       init();
			ArrayList<DATA> list = new ArrayList<DATA>();
			
		//	ConcurrentHashMap<String, Station> list_st = new ConcurrentHashMap<String, Station>();
			DynamoDB dynamo = new DynamoDB(Client);
			Table table = dynamo.getTable(tableName);
			try {
				// recuperer les donnee de dynamodb
				ItemCollection<ScanOutcome> items = table.scan();
				Iterator<Item> iter = items.iterator();
				while (iter.hasNext()) {
					Item item = iter.next();
					DATA data = new DATA();
					data.setID((String) item.get("ID"));
					data.setNb((String) item.get("nb"));
					
					list.add(data);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return list;
		}
	
	

	/*
	 * wir bekomen die Daten von Twitter als type "Status" um diese daten nach
	 * dynamoDB hochzuladen . sollen wir diese daten analysiert und in Map<>List
	 * stellen
	 */

	private static Map<String, AttributeValue> newNbItem(String ID, int nb) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("ID", new AttributeValue(ID));
		item.put("nb", new AttributeValue(Integer.toString(nb)));

		return item;
	}

	private static Map<String, AttributeValue> newItem(String id, String user, Date date, String location,
			String content) {
		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
		item.put("ID", new AttributeValue(id));
		item.put("user", new AttributeValue(user));
		item.put("location", new AttributeValue(location));
		item.put("date", new AttributeValue(date.toString()));
		item.put("content", new AttributeValue().withSS(content));

		return item;
	}
}
