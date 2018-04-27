package warehouse.locations;

import warehouse.Robot;

public class StorageShelf extends Location{

	private static int nextID = 0;

	public StorageShelf() {
		super("S" + nextID++);
	}

	@Override
	public boolean canLeave(Robot robot) {
		return robot.isCarrying();
	}

	@Override
	public void elaspeTick(Robot robot) {
		robot.setCarrying(true);
	}

}
