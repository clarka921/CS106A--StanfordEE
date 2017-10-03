/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;


/*
 * this program takes an integer and applies one of two different formulas to it, depending on whether the integer is positive or negative.
 * the process repeats itself until the integer is reduced to one. the program also counts the number of steps required to do so.
 */

public class Hailstone extends ConsoleProgram {
	public void run() {
		
		
		/*
		 * create the variables that will be used in the program (one to hold the current value and the other to count the number of steps)
		 */
		int hailstone = readInt("Enter a number: ");
		int steps = 0;
		
		/*
		 * create an if statement checking for positive or negative values of hailstone nested within a while loop to keep the process running until hailstone reaches 1
		 */
		while (hailstone != 1) {
			if(hailstone % 2 == 0) {
				println(hailstone + " is even, so I take half: " + hailstone/2);
				hailstone = hailstone/2;
			} else {
				println(hailstone + " is odd so I make 3n +1: " + (3*hailstone+1));
				hailstone = 3 * hailstone + 1;
			}
			
		/*
		 * add 1 to the value of steps with each iteration to count how many loops are required for halstone to reach 1
		*/
		steps += 1;
		
		}
		
	println("The process took " + steps + " steos to reach 1.");	
	
	}
}


