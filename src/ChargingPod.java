
public class ChargingPod extends Location implements Actor {
	private static String UID="C1";
	private int tickCount;
	private boolean isReady;

	public ChargingPod(Class<? extends Location> nextLocation){
		super(nextLocation, UID);
		tickCount=0;
	}

	@Override
	public void moveTo(){}


	@Override
	public String getUID(){
		return UID;
	}

	@Override
	public int getTickCount(){
		return tickCount;
	}

	@Override
	public void setTickCount() {
		tickCount++;

	}

	@Override
	public boolean getIsReady(){
		return isReady;
	}

	@Override
	public boolean setIsReady(){
		return true;
	}

	public void charge(Robot robot){
		if(robot.getBatteryLevel()<=10){

			while(tickCount<10){
				robot.setBatteryLevel();
				tickCount++;
			}

		}
	}


}
