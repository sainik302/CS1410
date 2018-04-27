package warehouse.locations;

import warehouse.Robot;

public class StorageShelf extends Location{

	@Override
	public boolean canLeave(Robot robot) {
		return robot.isCarrying();
	}

	@Override
	public void elaspeTick(Robot robot) {
		robot.setCarrying(true);
	}

}
