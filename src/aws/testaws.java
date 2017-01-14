package aws;

import twitter.DataTwitter;

public class testaws {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
     System.out.println("PutItems succeeded in table: " + "location");
	}

}
