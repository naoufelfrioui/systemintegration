package lambda;




import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

import aws.loadtwitterDate;
import twitter.MyTweet;


public class lambda implements RequestHandler<DynamodbEvent, Object> {

    @Override
    public ArrayList<MyTweet> handleRequest(DynamodbEvent input, Context context) {
        context.getLogger().log("Input: " + input);      
    
        loadtwitterDate load=new loadtwitterDate();
 
		 return  load.getdataTwitter("twitter");
    }
}
