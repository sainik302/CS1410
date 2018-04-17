//import java.math.RoundingMode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import jpd.aston.lab6.ListController;
//import jpd.aston.lab6.model.Database;




public class Warehouse extends Application {

	 @Override
	  public void start(Stage primaryStage) {
	    try {



	       // final Database d = createDatabase();
	        final FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass ().getResource("Grid.fxml"));
	    	//loader. setController (new ListController(d));
	    	final Parent root = loader.load ();

	    	Scene scene = new Scene(root, 400, 300);
	    	primaryStage.setTitle("Warehouse Simulation");
	    	primaryStage.setScene(scene);
	    	primaryStage.show();


	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }


	 public static void main(String[] args) {
		    launch(args);
		  }
	/**
	 * The list of all {@link Location}s which make up {@link Warehouse}.
	 */

	private List<Location> locations;

	private Class<? extends Location> startLocation;

	private Map<Actor, Location> moveTo;

	private Statistic<Actor> ordersRejected;

	private Statistic<Actor> ordersProcessed;

	private Statistic<Robot>numberOfActors;

	/**public Warehouse(Class<? extends Location> startLocation ){
		locations=new LinkedList<Location>();
		this.startLocation=startLocation;
		moveTo=new HashMap<Actor, Location>();
		ordersRejected=new Statistic<Actor>();
		ordersProcessed=new Statistic<Actor>();
		numberOfActors=new Statistic<Robot>();
	}*/

	public void addLocation(Location newLocation){
		if(newLocation !=null && !locations.contains(newLocation)){
			locations.add(newLocation);
		}
	}
	public List<Location> getLocations(){
		return locations;
	}



	//@Override
	/**public String toString() {

		DecimalFormat money = new DecimalFormat("#.##");
		money.setRoundingMode(RoundingMode.CEILING);
		DecimalFormat integer = new DecimalFormat("####");
		integer.setRoundingMode(RoundingMode.CEILING);

		String output = "";

		output += "Robots in station:         " + integer.format(numberOfActors.sum()) + "\n";
		output += "Orders rejected:           " + integer.format(roadUsersRejected.sum()) + "\n";
		output += "Vehicles processed:          " + integer.format(roadUsersProcessed.sum()) + "\n";
		output += "Petrol profit:              £" + money.format(fuelProfit.sum()) + "\n";
		output += "Lost petrol profit:         £" + money.format(lostFuelprofit.sum()) + "\n";
		output += "Shopping profit:            £" + money.format(salesProfit.sum()) + "\n";
		output += "Lost Shopping profit:       £" + money.format(lostSalesProfit.sum()) + "\n";
		output += "Total profit:               £" + money.format(salesProfit.sum() + fuelProfit.sum()) + "\n";
		output += "Total lost profit:          £" + money.format(lostFuelprofit.sum() + lostSalesProfit.sum()) + "\n";

		return output;
	} */




	 }


