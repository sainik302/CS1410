package warehouse.locations;

import warehouse.Robot;

public final class Empty extends Location {

	@Override
	public boolean canLeave(Robot robot) {
		return true;
	}

	@Override
	public void elaspeTick(Robot robot) {
		// Does nothing
	}
}
