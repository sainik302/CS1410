package warehouse.locations;

import warehouse.Robot;

public class StorageShelf extends Location{

	private String order;

	public StorageShelf() {
		order = "";
	}

	public void setOrder(String order){
		this.order = order;
	}

	@Override
	public boolean canLeave(Robot robot) {
		return order.equals("");
	}

	@Override
	public void elaspeTick(Robot robot) {
		if (!order.equals("")) {
			robot.setOrder(order);
			order = "";
		}
	}

	public boolean hasOrder(String order){
		return this.order.equals(order);
	}

}
