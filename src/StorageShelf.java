import java.util.*;

public class StorageShelf extends Location implements Actor {
private static String UID="S1";
private int tickCount;
private boolean isReady;
private ArrayList<Order> orders;
//private String itemsUID;




public StorageShelf(Class<? extends Location> nextLocation, String UID){
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
public void setTickCount(){
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

}


