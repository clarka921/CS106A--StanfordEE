/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

/*
 * this program reads a list of integers and when the SENTINEL value is entered, outputs the max and min intgers entered.
 */

import acm.program.*;

/*
 * this program determines the min and max values from a list of integers. the values are returned after a SENTINEL variable 
 * is entered. If the first value entered is the SENTINEL, a saucy message is printed; if the SENTINEL is entered as the second value,
 * the max and min will equal the first value entered. For all other combinations, the max and min will be returned. there is no limit 
 * on how many different values can be entered.
 */

public class FindRange extends ConsoleProgram {
	public void run() {
		
		//*print text explaining operation of program
		println("This program finds the largest"); 
		println("and smallest integers in a list.");
		println("Enter values, 1 per line, using");
		println("0 to signal the end of the list.");
		
		//*prompt to enter first value, If SENTINEL, display message
		int value = readInt ("?");
		if(value == SENTINEL) {
			println("Why did you do that? I worked hard programming this and you didn't even try to use it!");
		}
		
		//*if not SENTINEL, initialize max and min to value
		int max = value; 
		int min = value;
		
		//* enter another value. If SENTINEL, end program and print message that max = min
		value = readInt("?");
		if(value == SENTINEL) {
			println("The max value is " + max + ".");
			println("The min value is " + min + ".");
			
		/* if not SENTINEL, continue reading values and checking each against current min and max. If higher/lower, set variable to new value.
		 * once SENTINEL is entered, print max and min
		 */
		
		} else if (value != SENTINEL) {
			while(value != SENTINEL) {
			value = readInt("?");
			if(value > max) {
				max = value;
			}else if (value < min) {
				min = value;
			}
			}
			println("The max value is " + max + ".");
			println("The min value is " + min + ".");	
		}
	}	
	private static final int SENTINEL= 0;
}

