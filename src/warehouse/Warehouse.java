package warehouse;

//import java.math.RoundingMode;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import warehouse.locations.Empty;
import warehouse.locations.Location;
import warehouse.locations.PackingStation;
import warehouse.locations.StorageShelf;

public class Warehouse extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			// final Database d = createDatabase();
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Grid.fxml"));
			// loader. setController (new ListController(d));
			final Parent root = loader.load();

			Scene scene = new Scene(root, 400, 300);
			primaryStage.setTitle("Warehouse Simulation");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private final Map<Robot, Location> robots;

	private final Grid grid;

	private final Statistic<Robot> ordersRejected;

	private final Statistic<Robot> ordersProcessed;

	private final Statistic<Robot> numberOfRobots;

	public Warehouse() {
		this.grid = new Grid(5, 5);
		this.robots = new HashMap<>();
		this.ordersRejected = new Statistic<Robot>();
		this.ordersProcessed = new Statistic<Robot>();
		this.numberOfRobots = new Statistic<Robot>();
	}

	public void addLocation(Location newLocation) {
		if (newLocation == null) {
			throw new NullPointerException("Location cannot be null");
		}
	}

	public void getDestination(String order, Robot robot){

		if(!robot.hasTarget()){

			if(!robot.isCarrying()){
				findShelf(order, shelf -> robot.setTarget(shelf));
			}


		}


	}

	private void findShelf(String order, Consumer<StorageShelf> task){

		grid.forEach(location -> {

			if(location instanceof StorageShelf){

				final StorageShelf shelf = (StorageShelf) location;

				if(shelf.hasOrder(order)){
					task.accept(shelf);
				}
			}

		});

	}

	public void processLocations() {
	}

	private boolean move(Robot robot, Class<? extends Location> nextLocation) {

		return false;

	}

//	private boolean findOptimalDestination(Robot robot, Class<? extends Location> nextLocation) {
//
//		// Holds the best destination location found for the robot.
//		Location optimumLocation = null;
//
//		// Iterate though all the locations in the station
//		for (Location location : locations) {
//
//			/*
//			 * If the type of the current location is the same at the next
//			 * location AND there is enough space for the road user in that
//			 * location.
//			 */
//			if (location.getClass() == nextLocation) {
//
//				/*
//				 * If there is currently no optimum location for the road user
//				 * OR the current location is more optimal than the current most
//				 * optimal location.
//				 */
//				if (optimumLocation == null || location.compare(optimumLocation)) {
//
//					// Set the current location to be the most optimal location
//					// for the road user.
//					optimumLocation = location;
//
//				}
//
//			}
//		}
//
//		// If there is an optimal location for the robot then add it to it
//		// and remove it from toMove.
//		if (optimumLocation != null) {
//
//			optimumLocation.enter(robot);
//
//			toRemoveFrom_toMove.add(robot);
//
//			return true;
//
//		}
//
//		// If no destination location was found.
//		return false;
//
//	}

//	/**
//	 * Return all the {@link RoadUser}s in {@link #toMove} to the front of the
//	 * queue in their previous {@link Location}.
//	 *
//	 * @see environment.model.locations.Location
//	 * @see #toMove
//	 * @see environment.model.roadusers.RoadUser
//	 */
//	private void returnToPriorLocation() {
//
//		// Iterate through all the road users that couldn't be moved to their
//		// next location.
//		for (Robot robot : moveTo.keySet()) {
//
//			// If the current road user is not in the queue to be removed from
//			// toMove
//			if (!toRemoveFrom_toMove.contains(robot)) {
//
//				// Get the previous location of the current road user.
//				Location pastLocation = moveTo.get(robot);
//
//				// Return the road user to the its previous location. Then
//				// remove it
//				// from to move.
//				pastLocation.returnToQueue(robot);
//				toRemoveFrom_toMove.add(robot);
//			}
//		}
//	}

//	/**
//	 * Removes all the {@link RoadUser}s stored in {@link #toRemoveFrom_toMove}
//	 * and removes them from {@link #toMove}.
//	 *
//	 * @see #toMove
//	 * @see #toRemoveFrom_toMove
//	 */
//	private void removeMoved() {
//
//		// Iterate through all the elements from toRemoveFrom_toMove and remove
//		// them from toMove.
//		for (Robot robot : toRemoveFrom_toMove) {
//			moveTo.remove(robot);
//		}
//
//	}

	// @Override
	public String toString() {

		DecimalFormat money = new DecimalFormat("#.##");
		money.setRoundingMode(RoundingMode.CEILING);
		DecimalFormat integer = new DecimalFormat("####");
		integer.setRoundingMode(RoundingMode.CEILING);

		String output = "";

		output += "Robots in station:         " + integer.format(numberOfRobots.sum()) + "\n";
		output += "Orders rejected:           " + integer.format(ordersRejected.sum()) + "\n";
		output += "Vehicles processed:          " + integer.format(ordersProcessed.sum()) + "\n";
		// output += "Petrol profit: £" + money.format(fuelProfit.sum()) + "\n";
		// output += "Lost petrol profit: £" +
		// money.format(lostFuelprofit.sum()) + "\n";
		// output += "Shopping profit: £" + money.format(salesProfit.sum()) +
		// "\n";
		// output += "Lost Shopping profit: £" +
		// money.format(lostSalesProfit.sum()) + "\n";
		// output += "Total profit: £" + money.format(salesProfit.sum() +
		// fuelProfit.sum()) + "\n";
		// output += "Total lost profit: £" + money.format(lostFuelprofit.sum()
		// + lostSalesProfit.sum()) + "\n";

		return output;
	}

}
