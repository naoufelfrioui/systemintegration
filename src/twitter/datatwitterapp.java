package twitter;

import java.util.ArrayList;
import java.util.List;




public class datatwitterapp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub   
		   List<MyTweet> myTweets = new ArrayList<MyTweet>();	
		int j=1;
			myTweets = DataTwitter.getTweet(200,DataTwitter.getConf());
			
			for(MyTweet mt : myTweets){
				System.out.println(j +"---"+ mt.getUser()+"---"+ mt.getLocation()+"---"+ mt.getHashtage());
				j++;
			}
		
	}

}
