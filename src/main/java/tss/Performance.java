/**
 * Template for Performance. You must implement the constructor and methods
 * shown. You may add fields, constructors, and methods, but do not change
 * the signatures of those provided.
 */
package tss;
public class Performance {
	Artist performanceArtist;
	String performanceStart;
	int performanceDuration;
	public Performance(Artist artist, String startTime, int duration) {
		performanceArtist = artist;
		performanceStart = startTime;
		performanceDuration = duration;
		System.out.println("@Performance object created with artist " + artist + " at " + startTime + " with duration " + duration);
	}

	/**
	 * Return the artist for this performance.
	 * @return The {@link Artist} for this performance.
	 */
	public Artist getArtist() {
		return performanceArtist;
	}

	/**
	 * Determine whether this performance overlaps with the performance provided.
	 * Performance A overlaps with performance B if A's start time is before B's, but it
	 * finishes after B starts, or vice versa.
	 * @param existingPerformance The {@link Performance} to compare against.
	 * @return true if the two performances overlap otherwise false.
	 */
	public boolean overlaps(Performance existingPerformance) {
		return false;
	}

	@Override
	public String toString() {
		return null;
	}
}
