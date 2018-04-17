
public class Order  {
	private int tickCount;
	private boolean orderAccepted;
	private boolean isReady;

	public Order(){
		orderAccepted=false;
		tickCount=0;
	}
public String getShelfUID(StorageShelf ss){
	return ss.getUID();
}
public int getTickCount(){
	return tickCount;
}
public void setTickCount(){
	tickCount++;
}
public boolean getOrderAccepted(){
	return orderAccepted;
}

public boolean setOrderAccepted(){
	return true;
}

public boolean getIsReady(){
	return isReady;
}

public boolean setIsReady(){
	return true;
}
}
