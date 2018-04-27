package warehouse.locations;
import warehouse.Robot;

public class PackingStation extends Location {

	private String order;

	public PackingStation() {
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
			robot.setOrder("");
			order = "";
		}
	}

	public boolean hasOrder(){
		return !order.equals("");
	}

	public String getOrder(){
		return order;
	}



}
