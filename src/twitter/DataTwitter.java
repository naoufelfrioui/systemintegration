package twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	
	    /**
	    * in diese Methode sollen wir an twitter compte connecten
	    * wir nutzen ConsumerKey und Token
	    * um diese key zu haben . muss man eine Application twitter erstellen 
	    */

	public static ConfigurationBuilder getConf() {
        
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("7SsChMWuTKL0fuKUbym37xj9F")
        .setOAuthConsumerSecret("pTxeWx3Un5pjKE0GMRAdd3yOAdGQguCDFlRwPXwMsv0nA3EtlO")
        .setOAuthAccessToken("1877539808-HykgJA91IfkplfBHWDfl8Tdzu8xYPsuRN7kAOIl")
        .setOAuthAccessTokenSecret("ZOYkMalxKAllBSlqgMRtS7ym4KDh96cCIQRpXEDwFal9f");

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
	public static List<MyTweet> getTweet(Integer maxTweet,ConfigurationBuilder cfg){
		try{
			
			List<MyTweet> myTweets = new ArrayList<MyTweet>();
			
			TwitterFactory tf = null;
			Twitter twitter = null;
			
			tf = new TwitterFactory(cfg.build());
			twitter = tf.getInstance();
			
    	    	 ResponseList < Status > status = twitter.getHomeTimeline(new Paging(1, maxTweet));
    	    	 for(Status s:status){
            		
            		MyTweet t = new MyTweet(s);
                 	myTweets.add(t);
    	    	 }  
    	    return myTweets;
    	    
		}catch(TwitterException e){
			if(e.getErrorCode() == 88){
				System.out.println("Limit Twitter exceeded");
			}else{
				System.out.println("Identifiant entered are incorrect");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new ArrayList<MyTweet>();
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
		
	
}
