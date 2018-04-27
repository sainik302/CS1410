package warehouse;

import warehouse.locations.ChargingPod;
import warehouse.locations.Location;

public class Robot{

	private static int nextID = 0;

	private int batteryCapacity;

	private final String id;

	private int batteryLevel;

	private String currentOrder;

	private boolean carrying;

	private Location target;

	private ChargingPod home;

	public Robot(int batteryCapacity, ChargingPod home) {

		this.batteryCapacity = batteryCapacity;
		this.batteryLevel = batteryCapacity;
		this.currentOrder = "";
		this.carrying = false;
		this.target = null;
		this.home = home;
		this.id = "R" + nextID++;

	}

	public void returnHome(){
		this.target = home;
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

	public boolean hasOrder(){
		return !currentOrder.equals("");
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

	@Override
	public String toString() {
		return id;
	}
}

