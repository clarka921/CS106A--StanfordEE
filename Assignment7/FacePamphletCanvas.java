/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized (but it does).
 */


import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants, ComponentListener {
	
	
	private JTextArea nameField;
	private JTextArea friendsBody;
	private JTextArea friendsHeader;
	private JTextField messageArea;
	private JTextField statusArea;
	private FacePamphletProfile currentProfile;
	private GImage image;
	private ArrayList<String> mess = new ArrayList<String>(); 
	
	
	//Constructor
	public FacePamphletCanvas() {
		
		addComponentListener(this);
		
		messageArea = new JTextField(25);
		messageArea.setFont(MESSAGE_FONT);
		int x = getWidth() / 2;
		int y = APPLICATION_HEIGHT - BOTTOM_MESSAGE_MARGIN;
		messageArea.setLocation(x, y);
		messageArea.setBorder(null);
		add(messageArea);
		
	}
	
	
	//Refreshes canvas to display current info
	public void displayProfile(FacePamphletProfile profile) {
		
		removeAll();
		currentProfile = profile;
		displayName(profile);
		displayFriends(profile);
		displayImage(profile);
		displayStatus(profile);
		displayMessage();
		
	}
	
	
	//Adds latest message to arraylist (message log)
	public void newMessage(String xyz) {
		
		mess.add(xyz);
	}
	
	
	//Displays latest entry in arraylist as message on canvas
	private void displayMessage() {
		
		try {
			
			int i = mess.size() - 1;
			String abc = mess.get(i);
			int z = abc.length();
			messageArea = new JTextField(z);
			messageArea.setFont(MESSAGE_FONT);
			int x = (getWidth() - messageArea.getWidth()) / 4;
			int y = APPLICATION_HEIGHT - BOTTOM_MESSAGE_MARGIN;
			messageArea.setLocation(x, y);
			messageArea.setBorder(null);
			messageArea.setText(abc);
			add(messageArea);
		
			
		} catch (Exception e) {
			
			System.out.println("Hold up now, pardner");
			
		}
	}
	
	
	//Displays profile name supplied by current profile
	private void displayName (FacePamphletProfile profile) {
		
		nameField = new JTextArea();
		int x = LEFT_MARGIN ;
		int y = TOP_MARGIN ;
		nameField.setLocation(x, y);
		nameField.setEditable(false);
		nameField.setFont(PROFILE_NAME_FONT);
		nameField.setBackground(Color.WHITE);
		nameField.setForeground(Color.blue);
		nameField.setText(profile.getName());
		add(nameField);
		
	}
	
	
	//Displays status prvided by current profile
	private void displayStatus (FacePamphletProfile profile) {
		
		String mno = profile.getStatus();
		int q = mno.length();
		statusArea = new JTextField(q);
		statusArea.setFont(PROFILE_STATUS_FONT);
		int x = LEFT_MARGIN;
		int y = nameField.getY() + nameField.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN; 
		statusArea.setLocation(x, y);
		statusArea.setText(mno);
		statusArea.setBorder(null);
		add(statusArea);
			
	}
	
	
	//Displays list of friends supplied by current profile
	private void displayFriends (FacePamphletProfile profile) {
		
		friendsHeader = new JTextArea();
		int x0 = getWidth() / 2;
		int y0 = nameField.getY() + nameField.getHeight() + IMAGE_MARGIN;
		friendsHeader.setLocation(x0, y0);
		friendsHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendsHeader.setText("Friends:");
		add(friendsHeader);
		
		friendsBody = new JTextArea();
		int x1 = getWidth() / 2;
		int y1 = friendsHeader.getY() + friendsHeader.getHeight() + IMAGE_MARGIN;
		friendsBody.setLocation(x1, y1);
		friendsBody.setFont(PROFILE_FRIEND_FONT);
		
		Iterator <String> it = profile.getFriends();
		while(it.hasNext()) {
			
			String friend = it.next();
			friendsBody.append(friend + "\n");
			
		}	
		
		add(friendsBody);
	
	}
	
	
	//Displas image supplied by current profile.
	private void displayImage(FacePamphletProfile profile) {	
		
		image = profile.getImage();
		double x = LEFT_MARGIN;
		double y = nameField.getY() + nameField.getHeight() + IMAGE_MARGIN; 
		image.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
		add(image);
		
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { if(currentProfile != null ) displayProfile(currentProfile); }
	public void componentShown(ComponentEvent e) { }
	

}	// END OF CLASS. CREATED BY ACLARK 2017
