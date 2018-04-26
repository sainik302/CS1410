package warehouse.locations;

import java.util.LinkedList;
import java.util.List;

import warehouse.Robot;

public abstract class Location {

	/**
	 * The next <code>int</code> of the next {@link Location}s {@link #UID}.
	 *
	 * @see #UID
	 */
	private static int nextUID = 0;

	/**
	 * Holds all the locations that a robot could move from the current two.
	 */
	private final List<Location> adjacentLocations;

	/**
	 * The <code>Integer</code> that uniquely identifies <code>this</code>
	 * {@link Location}.
	 */
	private final int UID;

	/**
	 * Whether or not this {@link Location} is occupied by a robot.
	 */
	protected boolean occupied;

	public Location() {
		this.UID = nextUID++;
		this.adjacentLocations = new LinkedList<>();
	}

	public abstract boolean canLeave(Robot robot);

	public abstract void elaspeTick(Robot robot);

	public void printInfo() {

	}

	public final boolean isOccupied() {
		return occupied;
	}

	public final void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	@Override
	public final boolean equals(Object obj) {

		if (obj instanceof Location) {

			if (((Location) obj).UID == this.UID) {
				return true;
			}

		}

		return false;
	}

}
