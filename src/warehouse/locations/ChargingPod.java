package warehouse.locations;

import warehouse.Robot;

public class ChargingPod extends Location{

	private static int nextID = 0;

	public ChargingPod() {
		super("C" + nextID++);
	}

	@Override
	public boolean canLeave(Robot robot) {
		return robot.isCharged();
	}

	@Override
	public void elaspeTick(Robot robot) {
		robot.charge();
	}


}
