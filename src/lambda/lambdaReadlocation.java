package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

import aws.loadtwitterDate;

public class lambdaReadlocation implements RequestHandler<DynamodbEvent, Object> {

    @Override
    public Object handleRequest(DynamodbEvent input, Context context) {
        context.getLogger().log("Input: " + input);

        loadtwitterDate load=new loadtwitterDate();
        
		 return  load.getdata("location");
       
    }

}
