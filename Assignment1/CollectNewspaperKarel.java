/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 * 
 * Preconditions are that karel is in cell 3,4 and facing east.
 * Postconditions are that karel is in cell 3,4 and facing east.
 * 
 * Karel moves to the newspaper, pickBeeper's, and returns to the starting point, facing the
 * original direction.
 * 
 * verified to work on supplied map.
 */


import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run(){
		MovetoNP();
		PickupNP();
		ReturntoSP();
	}
	
	private void MovetoNP(){
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}	
	
	private void PickupNP(){
		pickBeeper();
	}
	private void ReturntoSP(){
		turnAround();
		move();
		move();
		move();
		turnRight();
		move();
		turnRight();
	}
}