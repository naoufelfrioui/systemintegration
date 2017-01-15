package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

import aws.loadtwitterDate;
import twitter.DataTwitter;

public class lamdaWrite implements RequestHandler<DynamodbEvent, Object> {

    @Override
    public Object handleRequest(DynamodbEvent input, Context context) {
        context.getLogger().log("Input: " + input);
       try{
        loadtwitterDate load=new loadtwitterDate();
        DataTwitter.getTweet(200,DataTwitter.getConf());
        load.createTable("hashtag");
         load.createTable("twitter");
         load.createTable("location");
        load.loaddata(DataTwitter.getMyTweets(), "twitter");
        System.out.println("PutItems succeeded in table: " + "twitter");
         load.loadNBdata(DataTwitter.getHashtag(), "hashtag");
         System.out.println("PutItems succeeded in table: " + "hashtag");
         load.loadNBdata(DataTwitter.getLocation(), "location");
      return (String)"PutItems succeeded in table: twitter and hashtag and location ";
   
    } catch (Exception e) {

		System.err.println(e.getMessage());
        return null;
    }
 }
    
}
