package warehouse.locations;

import warehouse.Robot;

public abstract class Location {

	/**
	 * The <code>Integer</code> that uniquely identifies <code>this</code>
	 * {@link Location}.
	 */
	private final String id;

	protected Robot occupant;

	public Location(String id) {
		this.id = id;
	}

	public abstract boolean canLeave(Robot robot);

	public abstract void elaspeTick(Robot robot);

	public String toString() {
		return id;
	}

	public final boolean isOccupied() {
		return occupant != null;
	}

	public final void setOccupant(Robot occupant) {
		this.occupant = occupant;
	}

	@Override
	public final boolean equals(Object obj) {

		if (obj instanceof Location) {

			if (((Location) obj).id == this.id) {
				return true;
			}
		}

		return false;
	}

	public Robot getOccupant() {
		return occupant;
	}

}
