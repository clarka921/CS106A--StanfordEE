//This class stores all the user profiles in a database
//and provides CRUD functionality

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
	
	//Instance variables
	private HashMap<String, FacePamphletProfile> hashMap = new HashMap<String, FacePamphletProfile>();

	
	//Constructor
	public FacePamphletDatabase() {

	}
	
	
	//Add profile to database- called from main class
	public void addProfile(String profileName) {
		
			hashMap.put(profileName, new FacePamphletProfile(profileName));
	
	}


	//Returns profile based on key entered; called form main class... returns NULL if key/object does not exist
	public FacePamphletProfile getProfile(String name) {
			
			return hashMap.get(name);
		
	}	
		
	
	
	//Called from main class- removes chosen profile form database
	public void deleteProfile(String name) {
		
			hashMap.remove(name);
			
	}
	

	//Called from main class; used to keep program from crashing if profile is/isn't in database 
	public boolean containsProfile(String name) {
		
		if (hashMap.containsKey(name) == true) { 
			return true;
		} else {
			return false;
		}
	}

	
}	//CREATED BY ACLARK 2017
