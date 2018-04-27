package warehouse;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warehouse.locations.ChargingPod;
import warehouse.locations.Empty;
import warehouse.locations.Location;
import warehouse.locations.PackingStation;
import warehouse.locations.StorageShelf;

public class Warehouse extends Application {

	private final Map<Robot, Location> robots;

	private final Set<PackingStation> packingStations;

	private final Set<StorageShelf> shelfs;

	private final Queue<String> orderQueue;

	private final Grid grid;

	private final Statistic<Robot> ordersRejected;

	private final Statistic<Robot> ordersProcessed;

	private final Statistic<Robot> numberOfRobots;

	public Warehouse() {
		this.grid = new Grid(6, 6);
		this.robots = new HashMap<>();
		this.ordersRejected = new Statistic<>();
		this.ordersProcessed = new Statistic<>();
		this.numberOfRobots = new Statistic<>();
		this.packingStations = new HashSet<>();
		this.shelfs = new HashSet<>();
		this.orderQueue = new LinkedList<>();
	}

	public static void main(String[] args) {
		// launch(args);

		final Warehouse w = new Warehouse();

		final ChargingPod cp = new ChargingPod();
		final StorageShelf s = new StorageShelf();
		final PackingStation p = new PackingStation();

		final Robot r = new Robot(30, cp);

		w.addOrder("hk47");
		w.addOrder("hk48");
		w.addOrder("hk49");

		// Place the locations on the grid.
		w.addLocation(4, 4, s);
		w.addLocation(0, 4, p);
		w.addLocation(0, 0, cp);

		// Place the robot on the grid.
		w.setRobot(0, 0, r);

		w.simulate(40);


	}

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

	@Override
	public String toString() {

		DecimalFormat money = new DecimalFormat("#.##");
		money.setRoundingMode(RoundingMode.CEILING);
		DecimalFormat integer = new DecimalFormat("####");
		integer.setRoundingMode(RoundingMode.CEILING);

		String output = "";

//		output += "Robots in station:         " + integer.format(numberOfRobots.sum()) + "\n";
//		output += "Orders rejected:           " + integer.format(ordersRejected.sum()) + "\n";
//		output += "Vehicles processed:          " + integer.format(ordersProcessed.sum()) + "\n";

		output += "\n";
		output += grid.toString();


		return output;
	}

	public void setRobot(int x, int y, Robot robot) {

		Location loc = grid.get(x, y);

		if (loc.isOccupied()) {
			throw new IllegalArgumentException("There is already a robot at (" + x + "," + y + ")");
		}

		robots.put(robot, loc);
		loc.setOccupant(robot);

	}

	public void addLocation(int x, int y, Location newLocation) {

		// Check the location is not null.
		if (newLocation == null) {
			throw new NullPointerException("Location cannot be null");
		}

		// Add the location to a collection if necessary.
		if (newLocation instanceof PackingStation) {

			packingStations.add((PackingStation) newLocation);

		} else if (newLocation instanceof StorageShelf) {

			shelfs.add((StorageShelf) newLocation);

		}

		// Add the location to the grid.
		grid.setLocation(x, y, newLocation);
	}

	private void simulate(int numberOfTicks) {

		for (int i = 0; i < numberOfTicks; i++) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(grid.toString());

			// if there is orders to process
			if(!orderQueue.isEmpty()){

				// Attempt to assign the order at the front of the queue.
				if(assignOrder(orderQueue.peek())){
					orderQueue.poll();
				}
			}

			for (Robot robot : robots.keySet()) {

				final Location current = robots.get(robot);

				if (robot.hasTarget()) {

					final Location target = robot.getTarget();

					final List<Location> path = bfs(current, target);

					// Check if there is a path to the target location.
					if (!path.isEmpty()) {

						if (!robot.canMove()) {

							System.out.println("No power!!");

						} else if (!current.canLeave(robot)) {

							current.elaspeTick(robot);

						} else {

							current.setOccupant(null);

							// Get the element that is next in the path final
							Location next = path.get(0);

							next.setOccupant(robot);

							robot.move();

							robots.replace(robot, next);

							if (next.equals(target)) {

								if (target instanceof ChargingPod) {

									robot.setTarget(null);

								} else if (target instanceof StorageShelf) {

									findPackingStation(robot.getOrder(), robot);

								} else if (target instanceof PackingStation) {

									robot.returnHome();
									robot.setCarrying(false);

								}
							}
						}

					} else {
						System.out.println("No path found :( to :" + target);
					}

				} else {
					current.elaspeTick(robot);
				}
			}
		}
	}

	private boolean assignOrder(String order) {

		// Find a robot to retrieve the package
		Robot robotAssigned = null;
		for (Robot robot : robots.keySet()) {

			// Get a shelf that no robot is current going to.
			final StorageShelf shelf = getUnassignedShelf();

			/*
			 * If there is no robot assigned, the current robot has no current
			 * order and their is a empty shelf assigned the current robot to
			 * the order.
			 */
			if (robotAssigned == null && !robot.hasOrder() && robot.isCharged() && shelf != null) {

				robot.setOrder(order);
				robot.setTarget(shelf);
				robotAssigned = robot;
			}

		}

		// Find a packing station to have the order.
		PackingStation packingStationAssigned = null;
		for (PackingStation station : packingStations) {

			if (packingStationAssigned == null && !station.hasOrder()) {
				station.setOrder(order);
				packingStationAssigned = station;
			}

		}

		// If there was no robot assigned but a packing station was then reset
		// the packing station
		if (robotAssigned == null && packingStationAssigned != null) {
			packingStationAssigned.setOrder("");
		}

		// If there was a robot assigned but no packing station the reset the
		// robot.
		if (packingStationAssigned == null && robotAssigned != null) {
			robotAssigned.setOrder("");
			robotAssigned.setTarget(null);
		}

		// Return whether both a packing station and robot were assigned the
		// order.
		return robotAssigned != null && packingStationAssigned != null;
	}

	private List<Location> bfs(Location current, Location target) {

		final Set<Location> visited = new HashSet<>();
		final Map<Location, List<Location>> pathTo = new HashMap<>();
		final Queue<Location> queue = new LinkedList<>();

		queue.add(current);
		visited.add(current);

		grid.forEach(e -> {
			pathTo.put(e, new LinkedList<>());
		});

		while (!queue.isEmpty()) {

			Location node = queue.remove();

			for (Location subNode : grid.getAdjacent(node)) {

				// Whether or not the sub node has not been visited.
				final boolean notVisited = !visited.contains(subNode);

				// Whether or not the sub node does not contain an other robot.
				final boolean notOccupied = !subNode.isOccupied();

				// If the sub node is an empty location OR is the target
				final boolean canEnter = (subNode instanceof Empty) || (subNode.equals(target));

				if (notVisited && notOccupied && canEnter) {

					visited.add(subNode);
					queue.add(subNode);
					List<Location> subNodePath = pathTo.get(subNode);
					List<Location> currentPath = pathTo.get(node);
					currentPath.forEach(e -> subNodePath.add(e));
					subNodePath.add(subNode);

					if (subNode.equals(target)) {
						queue.clear();
					}
				}
			}
		}
		return pathTo.get(target);
	}

	private void findPackingStation(String order, Robot robot) {

		for (PackingStation packingStation : packingStations) {

			if (packingStation.getOrder().equals(order)) {
				robot.setTarget(packingStation);
			}
		}

	}

	private void addOrder(String order){
		orderQueue.add(order);
	}

	private StorageShelf getUnassignedShelf() {

		StorageShelf unassignedShelf = null;
		for (StorageShelf shelf : shelfs) {

			boolean isShelfAssigned = false;
			for (Robot robot : robots.keySet()) {

				final Location target = robot.getTarget();

				if (target != null && target.equals(shelf)) {
					isShelfAssigned = true;
				}
			}

			if (unassignedShelf == null && !isShelfAssigned) {
				unassignedShelf = shelf;
			}
		}

		return unassignedShelf;
	}

}
