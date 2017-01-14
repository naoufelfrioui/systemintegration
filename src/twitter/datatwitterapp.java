package twitter;

import java.util.ArrayList;
import java.util.List;




public class datatwitterapp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub   
		   List<MyTweet> myTweets = new ArrayList<MyTweet>();	
		   List<String> location = new ArrayList<String>();	
		int j=1;
			 DataTwitter.getTweet(200,DataTwitter.getConf());
			 myTweets =DataTwitter.getMyTweets();
			 location=DataTwitter.getLocation();
			//for(MyTweet mt : myTweets){
				//System.out.println(j +"---"+ mt.getUser()+"---"+ mt.getLocation()+"---"+ mt.getHashtage());
			 for (int i= 0; i < location.size(); i++) {
				 System.out.println(i+"-------"+location.get(i));
				
			}
		
	}

}
