/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	
/*
 * create a program that calculates the length of a hypotenuse (c) using the pythagorean theorem
 */
	
	public void run() {
		
		/*
		 * create a and b variables, set c to the sum of the root of their squares and println the result.
		 */
		
		println("Enter values to compute the Pythagorean theorem");
		int a = readInt("a: ");
		int b = readInt("b: ");
		double c = Math.sqrt(a*a+b*b);
		println("c = " + c);
	}
}
