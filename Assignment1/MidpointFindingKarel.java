/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 * 
 * preconditions are that Karel is in cell 1,1 and facing east.
 * postconditions are that Karel is in the middle cell (x,1), on top of a beeper facing east or west.
 * 
 * fills first row except for the first and last cell, then bounces back and forth 
 * removing end beepers 1 by 1 until there is only the center beeper left, then it heads to that beeper.
 * 
 * verified to work on maps of even and odd width.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run(){
		fillRow();
		turnAround();
		move();
		while(beepersPresent()){
			clearRow();
		}
		findMidPoint();
	}
	
	private void fillRow(){
		while(frontIsClear()){
			move();
			if(frontIsClear()){
				putBeeper();
			}
		}	
	}
	
	private void clearRow(){
		while(beepersPresent()){
			move();
		}
		turnAround();
		for (int i =0; i<2;i++){
			move();
		}
		if(beepersPresent()){
			turnAround();
			move();
			pickBeeper();
			turnAround();
			move();
		}
	}
	
	private void findMidPoint(){
		while(noBeepersPresent()){
			move();
			if(frontIsBlocked()){
				turnAround();
			}
		}
	}	
}
	


