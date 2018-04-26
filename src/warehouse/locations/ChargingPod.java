package warehouse.locations;

import warehouse.Robot;

public class ChargingPod extends Location{

	@Override
	public boolean canLeave(Robot robot) {
		return robot.isCharged();
	}

	@Override
	public void elaspeTick(Robot robot) {
		robot.charge();
	}


}
