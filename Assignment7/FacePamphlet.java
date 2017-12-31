/* 
 * File: FacePamphlet.java
 * -----------------------
 * 
 * This class implements the GUI for FacePamphlet and generates the 
 * messages that are displayed through the canvas 
 * 
 */

import acm.program.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;


public class FacePamphlet extends Program implements FacePamphletConstants {
	
	// Instance variables
	private JLabel nameLabel = new JLabel("Name");
	private JTextField nameTextField = new JTextField(TEXT_FIELD_SIZE);
	private JButton addButton = new JButton("Add");
	private JButton deleteButton = new JButton("Delete");
	private JButton lookUpButton = new JButton("Lookup");
	
		
	private JTextField changeStatusTextField = new JTextField(TEXT_FIELD_SIZE);
	private JButton changeStatusButton = new JButton("Change Status");
	private JTextField changePictureTextField = new JTextField(TEXT_FIELD_SIZE);
	private JButton changePictureButton = new JButton("Change Picture");
	private JTextField addFriendTextField = new JTextField(TEXT_FIELD_SIZE);
	private JButton addFriendButton = new JButton("Add Friend");

	private JPanel mainPanel;
	private JPanel northPanel;
	private JPanel westPanel;
	
	
	private FacePamphletDatabase dataBase;
	private FacePamphletCanvas canvas;
	
	private FacePamphletProfile currentProfile = null;
	

	//Main method
	public void init() {
		
		dataBase = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
	
		makeDummyProfile();
		setUpGUI();
		
		addActionListeners();
		nameTextField.addActionListener(this);
		
	}
	
	
	//Creates an blank profile and adds it to canvas to get started
	private void makeDummyProfile() {
	
		canvas.newMessage("Welcome to FacePamphlet! Cheez it!");
		dataBase.addProfile("");
		currentProfile = dataBase.getProfile("");
		currentProfile.setImage("NewProfile");
		currentProfile.setStatus("Please select a profile to change status");
		canvas.newMessage("Welcome to FacePamphlet!");
		canvas.displayProfile(currentProfile);
		
	}
	
	
	//Instantiate and place all the panels, labels, textboxes, and buttons
	private void setUpGUI() {
		
		// A border layout lays out a container, arranging and resizing its components
		// to fit in five regions: north, south, east, west, and center
		// This is the syntax for setting one up... doesn't make sense to me, but it has
		// to be done
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		mainPanel.setBounds(0, 0, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		
		westPanel = new JPanel();
		westPanel.setLayout((new BoxLayout(westPanel, BoxLayout.Y_AXIS)));		
		northPanel = new JPanel();
		northPanel.add(nameLabel);
		northPanel.add(nameTextField);
		northPanel.add(addButton);
		northPanel.add(deleteButton);
		northPanel.add(lookUpButton);
	
		westPanel.add(changeStatusTextField);
		westPanel.add(changeStatusButton);
		westPanel.add(changePictureTextField);
		westPanel.add(changePictureButton);
		westPanel.add(addFriendTextField);
		westPanel.add(addFriendButton);
		mainPanel.add(canvas, CENTER);
		mainPanel.add(northPanel, NORTH); 
		mainPanel.add(westPanel, WEST);
		
		add(mainPanel);
		
	}

	
    public void actionPerformed(ActionEvent e) {
    	
    	//Add button pressed; if profile doesn't already exist, create one
    	if (e.getSource() == addButton) {
    		
			String userEntry = nameTextField.getText();
			
			if (dataBase.containsProfile(userEntry) == false) {
				
				dataBase.addProfile(userEntry);
				currentProfile = dataBase.getProfile(userEntry);
				canvas.newMessage("A new profile was created for " + userEntry);
				canvas.displayProfile(currentProfile);
				System.out.println("A profile was succesfully created for " + userEntry);
				System.out.println("The current profile is for " + currentProfile.getName());
				
			} else { 
				
				System.out.println("I already created a profile for " + userEntry + "."); 
			}
			
 			nameTextField.setText("");
 			
			
		//Delete button pressed; delete current profile
		} else if (e.getSource() == deleteButton) {
		 
			if(currentProfile.getName() != "") {
				String jkl = currentProfile.getName();
				dataBase.deleteProfile(jkl);
				currentProfile = dataBase.getProfile("");
				System.out.println(jkl + "'s profile deleted");
				canvas.newMessage(jkl + "'s profile deleted.");
				canvas.displayProfile(currentProfile);
				
			} else { 
				
				canvas.newMessage("Please create a profile.");
				canvas.displayProfile(currentProfile);
				System.out.println("There wasn't a profile to delete");
				
			}
			
			nameTextField.setText("");
			
				
		//Lookup button pressed, if profile exists delete it, otherwise display message
		} else if (e.getSource() == lookUpButton) {
			
			String userEntry = nameTextField.getText();
			
			if (dataBase.containsProfile(userEntry) == true) {
				currentProfile = dataBase.getProfile(userEntry);
				canvas.newMessage("Showing profile for " + userEntry);
				System.out.println("Showing profile for " + userEntry);
				canvas.displayProfile(currentProfile);
				
				
			} else	{ 
				
				System.out.println("We were unable to locate a profile for " + userEntry + ".");
				canvas.newMessage("We were unable to locate a profile for " + userEntry + ".");
				canvas.displayProfile(currentProfile);
				
			}
			
			nameTextField.setText("");
			
			
		//Updates status of current profile if one is present; otherwise display message
		} else if (e.getSource() == changeStatusButton) {
			
			String name = currentProfile.getName();
			
			if (currentProfile.getName() != "") {
				
				String status = changeStatusTextField.getText();
				currentProfile.setStatus(name + "'s is " + status);
				canvas.newMessage("Changed the status for " + name);
				System.out.println(currentProfile.getName() + "'s status was changed to " + status);
				canvas.displayProfile(currentProfile);
				
			} else { 
				
				System.out.println("There doesn't appear to be a profile selected.");
				canvas.newMessage("There doesn't appear to be a profile selected.");
				canvas.displayProfile(currentProfile);
			
			}	
			
			changeStatusTextField.setText("");
			
			
		//Change picture of current profile
		} else if (e.getSource() == changePictureButton) {	
			
			String name = currentProfile.getName();
			
			if (currentProfile.getName() != "") {
			
				try {
					
					String path = changePictureTextField.getText();
					currentProfile.setImage(path);
					canvas.newMessage("Picture change for " + name);
					System.out.println("Picture change for " + name);
					canvas.displayProfile(currentProfile);
					
				} catch (NullPointerException ev) {
					
					canvas.newMessage("Unable to locate that picture.");
					System.out.println("Unable to locate that picture.");
					canvas.displayProfile(currentProfile);
					
				}
				
			} else {
				
				System.out.println("There doesn't appear to be a profile selected.");
				canvas.newMessage("There doesn't appear to be a profile selected.");
				canvas.displayProfile(currentProfile);
				
			}
				
				changePictureTextField.setText("");
			
		
		//Add friend to current profile if one is selected; if not display message. The currentProfile is also added as a friend to the profile entered
		} else if (e.getSource() == addFriendButton) {
			
			String friend = addFriendTextField.getText();
			
			if ((currentProfile.getName() != "") && (dataBase.containsProfile(friend) == true) && currentProfile.containsFriend(friend) == false) {
				
				
				String str = currentProfile.getName();
				FacePamphletProfile otherFriend = dataBase.getProfile(friend);
				String gbh = otherFriend.getName();

				currentProfile.addFriend(friend);
				
				//Add currentProfile as friend to profile selected as friend (reciprocal friendship)
				
				otherFriend.addFriend(str);
				canvas.newMessage(str + " added " + gbh + " as a friend");
				canvas.displayProfile(currentProfile);
				
			} else { 
				
				System.out.println("Unable to add " + friend + " as a friend") ;
				canvas.newMessage("Unable to add " + friend + " as a friend");
				canvas.displayProfile(currentProfile);
			}	
			
			addFriendTextField.setText("");	
		
		}			
	}
    
    
 }	// END OF CLASS. CREATED BY ACLARK 2017
