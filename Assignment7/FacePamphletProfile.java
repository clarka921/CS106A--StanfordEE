//This class contains the constructor
//for individual profiles and provides all the functionality
//to return and change their info (name, status, etc.)


import java.util.*;
import acm.graphics.*;
import acm.util.ErrorException;


public class FacePamphletProfile implements FacePamphletConstants {
	
	//Instance variables
	private String profileName;
	private String profileStatus;
	private GImage currentProfilePic;
	private ArrayList <String> profileFriends;
	
	
	//Constructor
	public FacePamphletProfile(String name) {
		profileName = name;
		profileFriends = new ArrayList <String> ();
		profileStatus = "No Current Status";
		String str = "images/NewProfile";
		setImage(str);
		
	}
	
	
	//This method returns the name associated with the profile.
	public String getName() {
		
		return profileName;
		
	}

	
	//This method returns the image associated with the profile. If there is no image associated with the profile, the method returns null. */ 
	public GImage getImage() {
		
			return currentProfilePic;

	}

	
	//This method sets the image associated with the profile. */ 
	public void setImage(String path) {
		
		GImage image = null;
		
		try {
			
			image = new GImage(path + ".jpg");
			currentProfilePic = image;
			
		} catch (ErrorException ex) {
			
			image = null;
			System.out.println("Unable to load your dumb pic...");
		}
	
	
	} 
	
	
	//Returns profile's current status
	public String getStatus() {
		
		return profileStatus;
		
	}
	
	
	//Sets profiles current status
	public void setStatus(String status) {
		
		profileStatus = status;
		
	}

	
	//If friend is not currently in network AND the friend exists in the database, they will be added to the profile's friend list
	public void addFriend(String friend) {
		
			profileFriends.add(friend);
			
	}		
		

	//Checks if friend is in network and if so, returns true and removes friend; else returns false
	public boolean removeFriend(String friend) {
	
		if (profileFriends.contains(friend) == true) {
			
			profileFriends.remove(friend);
			return true;
	
		} else { return false; }	
	}

	
	//Returns the iterator for the Friend array list
	public Iterator<String> getFriends() {
		if(profileFriends != null) {
			Iterator <String> it = profileFriends.iterator();
			return it;
		} else { 
			return Collections.emptyIterator();
		}	
	}
	
	public boolean containsFriend(String friend) {
		
		if(profileFriends.contains(friend) == true) {
			return true;
		} else { return false;	
		}

	}	
		
	
	//Returns a concatenation (String) of all the textual info associated with profile
	public String toString (Iterator <String> it) {
		
		String name = getName();
		String status = getStatus();
		ArrayList <String> arr = new ArrayList <String>();
		
		while (it.hasNext() == true) {
			String str = it.next() + ",";
			arr.add(str);
		}
		
		return (name + " (" + status + "): " + arr.toString());
		
	}

	
}	// END OF CLASS. CREATED BY ACLARK 2017
