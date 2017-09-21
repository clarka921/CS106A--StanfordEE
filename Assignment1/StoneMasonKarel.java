/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 *
 * preconditions are that Karel is at 1,1 facing east.
 * postcondiions are that Karel is at x,1 facing east.
 *
 * Karel climbs the first column, repairing along the way, and turns around at the top and
 * heads back to the original position/orientation it started in. It then moves 4 spaces forward and begins the repair process
 * over again. this cycle repeats until the frontisBlocked, then karel repairs the last column and stops.
 * 
 * verified to work on all provided maps.
*/


import stanford.karel.*;




public class StoneMasonKarel extends SuperKarel {
	public void run(){
		while(frontIsClear()){
			RepCol();
			DescCol();
			MovetoNextCol();
			if(frontIsBlocked()){
				RepCol();
				DescCol();
			}
		}
	}

	private void RepCol(){
		turnLeft();
		if(noBeepersPresent()){
			putBeeper();
		}
		while(frontIsClear()){
			move();
			if(noBeepersPresent()){
				putBeeper();
			}
		}
	}
	
	private void DescCol(){
		turnAround();
		while(frontIsClear()){
			move();
		}
		turnLeft();
	}
	
	private void MovetoNextCol(){
		for(int i =0; i<4;i++){
				move();
		}
	}
}
