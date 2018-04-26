package warehouse;

import warehouse.locations.Location;

public class Robot{

	private int batteryCapacity;

	private int batteryLevel;

	private String currentOrder;

	private boolean carrying;

	private Location target;

	public Robot(int batteryCapacity) {

		this.batteryCapacity = batteryCapacity;
		this.batteryLevel = batteryCapacity;
		this.currentOrder = "";
		this.carrying = false;
		this.target = null;

	}

	public void setTarget(Location target){
		this.target = target;
	}

	public Location getTarget() {
		return target;
	}

	public boolean hasTarget(){
		return target != null;
	}

	public void setOrder(String order){
		this.currentOrder = order;
	}

	public void setBatteryCapacity(int capacity){
		this.batteryCapacity = capacity;
	}

	public String getOrder(){
		return currentOrder;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void charge() {
		if(batteryLevel < batteryCapacity){
			batteryLevel++;
		}
	}

	public void setCarrying(boolean carrying){
		this.carrying = carrying;
	}

	public int getRange(){
		return batteryLevel / (carrying ? 2 : 1);
	}

	public boolean isCharged(){
		return batteryLevel >= (batteryCapacity / 2);
	}

	public void move(){
		batteryLevel -= (carrying ? 2 : 1);
	}

	public boolean canMove(){
		return batteryLevel >= (carrying ? 2 : 1);
	}

	public boolean isCarrying() {
		return carrying;
	}
}

