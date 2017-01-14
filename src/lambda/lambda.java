package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import aws.loadtwitterDate;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

public class lambda implements RequestHandler<DynamodbEvent, Object> {

    @Override
    public Object handleRequest(DynamodbEvent input, Context context) {
        context.getLogger().log("Input: " + input);
       
       
        // TODO: implement your handler
        return null;
    }
    

}
