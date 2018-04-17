import java.util.*;

public class PackingStation extends Location implements Actor {
	private static String UID="P1";

	private int tickCount;
	private boolean isReady;
	private ArrayList<Order> orders;

	public PackingStation(Class<? extends Location> nextLocation){
		super(nextLocation, UID);
		tickCount=0;
		orders=new ArrayList<Order>();
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

	public void pack(){


	}
}
