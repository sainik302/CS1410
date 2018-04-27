package warehouse.locations;

import warehouse.Robot;

public final class Empty extends Location {

	private static int nextID = 0;

	public Empty() {
		super("E" + nextID++);
	}

	@Override
	public boolean canLeave(Robot robot) {
		return true;
	}

	@Override
	public void elaspeTick(Robot robot) {
		// Does nothing
	}
}
