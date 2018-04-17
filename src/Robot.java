
public class Robot extends Location implements Actor{
	private int batteryLevel;
	private static String UID="R1";
	private boolean hasAccepted;
	private int tickCount;
	private boolean isReady;

	public Robot(Class<? extends Location >nextLocation){
		super(nextLocation, UID);
		batteryLevel=10;

		hasAccepted=false;
		tickCount=0;
	}


	public int getBatteryLevel(){
		return batteryLevel;
	}


public void setBatteryLevel(){
	batteryLevel++;

	}

@Override
public String getUID(){
	return UID;
}

@Override
public boolean getIsReady(){
	return isReady;
}

@Override
public boolean setIsReady(){
	return true;
}

@Override
public int getTickCount(){
return tickCount;
}

@Override
public void setTickCount(){
tickCount++;
}


public boolean hasAccepted(){
	return hasAccepted;
}
//public void move(){
	//if(batteryLevel){}
//}

@Override
public void moveTo(){}

}
