package twitter;

import java.util.ArrayList;
import java.util.List;


import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;



/**
 * Getting tweet 
 * 
 * 
 *
 */
public class DataTwitter {
	
	private static ArrayList<String> location= new ArrayList<String>();
	private static ArrayList<String> hashtag= new ArrayList<String>();
	private static List<MyTweet> myTweets = new ArrayList<MyTweet>();
	
	    /**
	    * in diese Methode sollen wir an twitter compte connecten
	    * wir nutzen ConsumerKey und Token
	    * um diese key zu haben . muss man eine Application twitter erstellen 
	    */

	public static ConfigurationBuilder getConf() {
        
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("ImD7gRwKdEKhDUFQtvY4bIqTW")
        .setOAuthConsumerSecret("hbbHbJtIoHDgF5weZfEHTXEPDjEfF9Ceh7UG78G1EJDXMfQkTL")
        .setOAuthAccessToken("1713240570-Q8N2kNnLYGWfWduo1B9JeLRy0p5TnwXjK3Zmizp")
        .setOAuthAccessTokenSecret("5DFtKkac9pvTOs3V3Q4wqcrggb4CJVFGwJUKn9DiRWCwa");

		return cb;
	}
	
	/**
	 * Return a list of tweet for hasthag passed in parameter 
	 * Size of this list is passed too in parameter 
	 * @param hashtag
	 * @param maxTweet
	 * @param cfg
	 * @return
	 */
	// diese methode gibt uns List Tweets
	public static void getTweet(Integer maxTweet,ConfigurationBuilder cfg){
		try{
			
			
			String position;
			TwitterFactory tf = null;
			Twitter twitter = null;
			
			tf = new TwitterFactory(cfg.build());
			twitter = tf.getInstance();
			
    	    	 ResponseList < Status > status = twitter.getHomeTimeline(new Paging(1, maxTweet));
    	    	 for(Status s:status){
    	    		 if (s.getText().contains("#")) {
     	 				String[] listString = s.getText().split("#");
     	 				for (int i = 0; i < listString.length; i++) {
     	 					String hash = "#" + listString[i].split(" ")[0] + " ";
     	 				   hashtag.add(hash);
     	 				}
       	    		 }
    	    		 
            		MyTweet tweet = new MyTweet(s);
            		myTweets.add(tweet);
            		position=s.getUser().getLocation();
            		if(position.length()<1)
            			position="keine Location";
            		location.add(position); 
                 	
                 
    	    	 }  
    	    
    	    
		}catch(TwitterException e){
			if(e.getErrorCode() == 88){
				System.out.println("Limit Twitter exceeded");
			}else{
				System.out.println("Identifiant entered are incorrect");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	// diese methode gibt uns List Hashtag
	public static ArrayList<String> gethashtag(Integer maxTweet,ConfigurationBuilder cfg){
		try{
			
			ArrayList<String> hashtag = new ArrayList<String>();	
			
			TwitterFactory tf = null;
			Twitter twitter = null;
			
			tf = new TwitterFactory(cfg.build());
			twitter = tf.getInstance();
			
    	    	 ResponseList < Status > status = twitter.getHomeTimeline(new Paging(1, maxTweet));
    	    	 for(Status s:status){
    	    		 if (s.getText().contains("#")) {
    	 				String[] listString = s.getText().split("#");
    	 				for (int i = 0; i < listString.length; i++) {
    	 					String hash = "#" + listString[i].split(" ")[0] + " ";
    	 				   hashtag.add(hash);
    	 				}
      	    		 }
    	    	 }  
    	    return hashtag;
    	    	  
		}catch(TwitterException e){
			if(e.getErrorCode() == 88){
				System.out.println("Limit Twitter exceeded");
			}else{
				System.out.println("Identifiant entered are incorrect");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new ArrayList<String>();
	}

	
	
	
	
	
	
	public static ArrayList<String> getLocation() {
		return location;
	}

	public static void setLocation(ArrayList<String> location) {
		DataTwitter.location = location;
	}

	public static ArrayList<String> getHashtag() {
		return hashtag;
	}

	public static void setHashtag(ArrayList<String> hashtag) {
		DataTwitter.hashtag = hashtag;
	}

	public static List<MyTweet> getMyTweets() {
		return myTweets;
	}

	public static void setMyTweets(List<MyTweet> myTweets) {
		DataTwitter.myTweets = myTweets;
	}
	
		
	
}
