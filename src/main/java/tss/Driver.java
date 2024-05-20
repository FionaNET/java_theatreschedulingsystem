package tss;

public class Driver {
	
	/**
	 * Provide some strings to use for inputs to save having to type them
	 * in all the time.
	 */
	private static String[][] TEST_VALUES = {
				{ "The Jesus And Mary Chain", "2019-03-04T21:30", "150" }, 
				{ "Milk and Honey Festival", "2019-03-08T19:00", "180" }, 
				{ "Halestorm", "2019-03-13T20:00", "59" },  // short show for testing
				{ "Me First And The Gimme Gimmes", "2019-03-15T21:00", "150" },
				{ "Jay Rock",  "2019-03-23T21:30", "180" } 
	};
	/**
	 * The main for the system.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		hardwired();
	}
	
	/**
	 * This method provides a means to do basic testing of the system. It creates various
	 * objects under different circumstances and reports what happens. It is "hard-wired"
	 * in that the same thing happens every time it is executed (assuming no changes between
	 * executions of course). This makes is easy to re-run tests.
	 * 
	 * The implementation is instrumented somewhat to show what's going on. The 
	 * instrumentation messages are formatted in such a way to distinguish them from "normal" 
	 * system messages. In what's provide ">>" describes the particular 'scenario' that is 
	 * being shown, and "@" indicates when a constructor is called. This means that when 
	 * this method is executed (by you, or by a marker for example), the output can be 
	 * examined and any issues can be identified (e.g. somewhat that was supposed to happen 
	 * for a scenario didn't happen) and evidence for behaviour of your design can be 
	 * identified (e.g. the number of Artist objects created just requires counting the 
	 * relevant messages).
	 * 
	 * You are welcome to continue to use the conventions used in this template or to add new
	 * kinds of messages. Please do not change the existing conventions (">>" for scenarios
	 * and "@" for constructor calls). You are encouraged to add more scenarios, but keep in 
	 * mind that the markers will be unwilling to read pages and pages of output. You need to
	 * provide enough evidence to support any claims you make, without overwhelming the reader.
	 * 
	 * You are strongly encouraged to change the formatting of the output to make it easier
	 * to see what's going on. You might also consider using choosing values that will usefully
	 * illustrate the scenarios.
	 */
	private static void hardwired() {
		// Create new theatre instance
		Theatre myTheatre = new Theatre("MyTheatre");

		System.out.println(">>Start. Show current status (no artists, no performances)");
		myTheatre.printStatusOfTheatre();
		System.out.println(); // Make sure scenarios are clearly separated.
		System.out.println(">>Register first artist");
		// Create an artist object, register it, and show the resulting theatre status
		Artist artist1 = new Artist("Pink!");
		myTheatre.registerArtist(artist1);
		myTheatre.printStatusOfTheatre();
		System.out.println(); // Make sure scenarios are clearly separated.
		System.out.println(">>Register second artist");
		// Create another artist object, register it, and show the resulting theatre status
		Artist artist2 = new Artist("Sia");
		myTheatre.registerArtist(artist2);
		myTheatre.printStatusOfTheatre();
		System.out.println(); // Make sure scenarios are clearly separated.

		System.out.println(">>Schedule performance for second artist");
		// Create a performance for the second artist, schedule it, and show the resulting status
		Performance performance = new Performance(artist2, "2019-03-04T21:30", 149);
		myTheatre.registerPerformance(performance);
		myTheatre.printStatusOfTheatre();
		System.out.println(); // Make sure scenarios are clearly separated.

		System.out.println(">>Schedule second performance for second artist BEFORE first");
		System.out.println(">>performance, so should be listed after the first.");

		Performance performance2 = new Performance(artist2, "2019-03-04T09:30", 150);
		myTheatre.registerPerformance(performance2);
		myTheatre.printStatusOfTheatre();
		System.out.println(); // Make sure scenarios are clearly separated.

		System.out.println(">>Create performance that starts and finishes within the");
		System.out.println(">>same hour of day. Note the artist has not been registered");
		System.out.println(">>yet, but nor is the performance being scheduled.");
		Artist artist3 = new Artist("Blood");
		Performance performance3 = new Performance(artist3, "2019-03-05T09:30", 150);
		myTheatre.registerPerformance(performance3);
		myTheatre.printStatusOfTheatre();

		myTheatre.registerArtist(artist3);
		Performance performance4 = new Performance(artist3, "2019-03-04T09:30", 150);
		myTheatre.registerPerformance(performance4);
		myTheatre.printStatusOfTheatre();
		System.out.println(); // Make sure scenarios are clearly separated.

		System.out.println(">>Schedule previously-created performance for unregistered artist (no change)");


		System.out.println(); // Make sure scenarios are clearly separated.

//
//		System.out.println(">>Register third artist and schedule performance (this time successfully)");
//		// etc
//		System.out.println(); // Make sure scenarios are clearly separated.
//
//		System.out.println(">>Try to add overlapping performance (nothing should change)");
//		// etc
//		System.out.println(); // Make sure scenarios are clearly separated.
//
//		System.out.println(">>Try to create performance that finishes after midnight (nothing should change)");
//		// etc
//		System.out.println(); // Make sure scenarios are clearly separated.
	}
}

