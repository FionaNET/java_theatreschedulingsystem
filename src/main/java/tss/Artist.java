/**
 * Template for Artist. You must implement the constructor and methods
 * shown. You may add fields, constructors, and methods, but do not change
 * the signatures of those provided.
 */
package tss;
public class Artist {
	public String artistName = "";
	/**
	 * Create an artist object with the provide name.
	 * @param name The name of the artist.
	 */
	public Artist(String name) {
		System.out.println("@Artist object created with name " + name);
		artistName = name;
	}

	@Override
	public String toString() {
		return artistName;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Artist)
		{
			return ((Artist)obj).artistName == artistName;
		}else
		{
			return false;
		}
	}
}
