package aws;

public class testaws {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     loadtwitterDate load=new loadtwitterDate();
    load.createTable("hashtag");
    load.loadhashtag("hashtag");
    load.createTable("twitter");
    load.loaddata("twitter");
  
    // load.getdata("twitter");
	}

}
