package twitter;

import java.util.Date;

import twitter4j.Status;

public class DATA {

	private String ID;  
	private String nb; 
	
	
	public DATA() {
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


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getNb() {
		return nb;
	}


	public void setNb(String nb) {
		this.nb = nb;
	}
	
	
		

}
