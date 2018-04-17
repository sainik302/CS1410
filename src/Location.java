import java.util.*;



public abstract class Location {

	/**
	 * The next <code>int</code> of the next {@link Location}s {@link #UID}.
	 *
	 * @see #UID
	 */

	private static String nextUID;

	/**
	 * The <code>Class</code> of the next {@link Location}. This is generic but
	 * must be a subclass of {@link Location}.
	 */

	public final Class<? extends Location> nextLocation;

	/**
	 * Contains all the {@link Actor}s that are present in <code>this</code>
	 * {@link Location}.
	 *
	 * @see java.util.LinkedList
	 * @see #processQueue(HashMap)
	 *
	 */

	protected LinkedList<Actor> actors;


	/**
	 * The total number of {@link Actors} processed at this {@link Location}
	 */

	protected int actorsProcessed;

	/**
	 * The <code>Integer</code> that uniquely identifies <code>this</code>
	 * {@link Location}.
	 */

	private String UID;

	public Location(Class<? extends Location> nextLocation, String UID ) {
		this.nextLocation = nextLocation;
		//addActor = new LinkedList<Actor>();
		actorsProcessed = 0;
		this.UID=UID;

	}

	public void printInfo() {

	}

	public abstract void moveTo();

	public int getActorsProcessed() {
		return actorsProcessed;
	}

}
