package twitter;

import java.util.Date;

import twitter4j.Status;

public class MyTweet {

	private String id;  
	private String content; 
	private String hashtage;
	private String user;
	private String location;
	private double latitude;
	private double longitude;
	private Date timeTweet;
	private String date;
	private int retweet;
	private int nbRetweet;
	private int nbFavoris;
	
	public MyTweet() {
		super();
		
	}
	/**
	 * @param id
	 * @param content
	 * @param hashtage
	 * @param location
	 * @param timeTweet
	 * @param user
	 */
	
	public MyTweet(Status s){
		int retweet = 0;
		
		if (s.getText().startsWith("RT"))
			retweet = 1;
		
		String hashtag = "";
		try{
			if (s.getText().contains("#")) {
				String[] listString = s.getText().split("#");
				for (int i = 0; i < listString.length; i++) {
					hashtag += "#" + listString[i].split(" ")[0] + " ";
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			hashtag = "";
		}
		
		
		double latitude = 0;
		double longitude= 0;
		if(s.getGeoLocation() != null){
			latitude = s.getGeoLocation().getLatitude();
			longitude = s.getGeoLocation().getLongitude();
		}
		
		this.setId(String.valueOf(s.getId()));
		this.setContent(s.getText().replace("'", " "));
		this.setHashtage(hashtag);
		this.setLocation(s.getUser().getLocation());
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTimeTweet(s.getCreatedAt());
		this.setRetweet(retweet);
		this.setNbRetweet(s.getRetweetCount());
		this.setNbFavoris(s.getFavoriteCount());
		this.setUser(s.getUser().getName());

	}
	
	
	
	
	
	
	
	
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHashtage() {
		return hashtage;
	}
	public void setHashtage(String hashtage) {
		this.hashtage = hashtage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getTimeTweet() {
		return timeTweet;
	}
	public void setTimeTweet(Date timeTweet) {
		this.timeTweet = timeTweet;
	}
	public int getRetweet() {
		return retweet;
	}
	public void setRetweet(int retweet) {
		this.retweet = retweet;
	}
	public int getNbRetweet() {
		return nbRetweet;
	}
	public void setNbRetweet(int nbRetweet) {
		this.nbRetweet = nbRetweet;
	}
	public int getNbFavoris() {
		return nbFavoris;
	}
	public void setNbFavoris(int nbFavoris) {
		this.nbFavoris = nbFavoris;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
		

}
