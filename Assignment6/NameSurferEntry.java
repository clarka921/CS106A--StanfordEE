
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	// Instance variables
	private String entryName;
	private ArrayList<Integer> rankByDecade = new ArrayList<Integer>();

	// Constructor- void absent from first line because no return-type
	// (constructor!)
	public NameSurferEntry(String string, ArrayList<Integer> arrayList) {

		entryName = string;
		rankByDecade = arrayList;

	}

	// Returns name of NameSurferEntry
	public String getName() {

		return (entryName);
	}

	// Returns the rank associated with an entry for a particular decade. The decade
	// value is an integer indicating how many
	// decades have passed since the first year in the database,which is given by
	// the constant START_DECADE. If a name does
	// not appear in a decade, the rank value is 0.
	public int getRank(int n) {

		// This wont work on its own- also needs name dimension
		int i = rankByDecade.get(n);
		return (i);

	}

	// Prints info associated with NameSurferEntry
	
	public String toString() {

		return (entryName + ": " + String.valueOf(rankByDecade));

	}

}
