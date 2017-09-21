/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 * 
 * precondition is that Karel is in cell 1,1 and facing east
 * post conditions are that Karel is in either the NW or NE corner
 * 
 * Karel checks to see if the map is of the dimensions 1,x, in which case it will turnLeft and fill each alternating square, ending at the top.
 * If the map is of dimensions x>1,y Karel will fill each cell in it's current row before moving up to the next row. This cycle repeats until Karel cannot
 * move to the next row becuase frontIsBlocked, then it stops.
 * 
 * verified to work on all maps provided
 * 
 * ps-I found this one so hard... I spent 10+ hours on it. The trick for me was realising each row can be treated identically 
 * as long as the starting position is correct.
 */


import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run(){
		if(frontIsBlocked()){
			turnLeft();
			fillRow();
		}
		while(frontIsClear()){
			fillRow();
			nextRow();
		}
	}
	
	private void fillRow(){
		while(frontIsClear()){
			putBeeper();
			while(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
					putBeeper();
	
				}
			}
		}
	}
	
	private void nextRow(){
		if(facingEast()&&noBeepersPresent()){
			turnLeft();
			if(frontIsClear()){
				move();
				turnLeft();
			}
		}else if(facingEast()&&beepersPresent()){
			turnLeft();
			if(frontIsClear()){
				move();
				turnLeft();
				move();
			}
		}else if(facingWest()&&noBeepersPresent()){
			turnRight();
			if(frontIsClear()){
				move();
				turnRight();
			}
		}else if(facingWest()&&beepersPresent()){
			turnRight();
			if(frontIsClear()){
				move();
				turnRight();
				move();
			}
		}
	}
}

	
	

	