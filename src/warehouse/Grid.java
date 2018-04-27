package warehouse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import warehouse.locations.Empty;
import warehouse.locations.Location;

public final class Grid{

	private final List<List<Location>> grid;

	private int width;

	private int height;

	public Grid(int width, int height) {

		this.grid = new ArrayList<>();
		this.width = width;
		this.height = height;

		populate(width, height);

	}

	public Location get(int x, int y){

		if(x < 0 || y < 0){
			throw new IllegalArgumentException("That is not a valid coordinate.");
		}

		if (x >= width) {
			return null;
		}

		if(y >= height){
			return null;
		}

		return grid.get(x).get(y);
	}

	public void changeWidth(int newWidth) {

		if (newWidth > width) {

			for (int x = 0; x < newWidth - width; x++) {

				final List<Location> newCol = new ArrayList<>();

				for (int y = 0; y < height; y++) {
					newCol.add(new Empty());
				}

				grid.add(newCol);
			}

		} else if (newWidth < width) {

			for (int x = width - 1; x >= newWidth; x--) {
				grid.remove(x);
			}

		}

		width = newWidth;

	}

	public void changeHeight(int newHeight) {

		if (newHeight > height) {

			for (List<Location> currentCol : grid) {

				for (int y = 0; y < newHeight - height; y++) {
					currentCol.add(new Empty());
				}

			}

		} else if (newHeight < height) {

			for (List<Location> currentCol : grid) {

				for (int y = height - 1; y >= newHeight; y--) {
					currentCol.remove(y);
				}

			}

		}

		height = newHeight;

	}

	public void setLocation(int x, int y, Location newLoc) {
		grid.get(x).add(y, newLoc);
		grid.get(x).remove(y + 1);
	}

	private final void populate(int width, int height) {

		for (int x = 0; x < width; x++) {

			final List<Location> newCol = new ArrayList<>();

			for (int y = 0; y < height; y++) {
				newCol.add(new Empty());
			}

			grid.add(newCol);
		}

	}

	public List<Location> getAdjacent(Location loc){
		final List<Location> adjacent = new LinkedList<Location>();

		boolean found = false;

		for (int x = 0; x < width && !found; x++) {
			for (int y = 0; y < height && !found; y++) {
				if(get(x, y).equals(loc)){

					// Add left
					if(x > 0){
						final Location left = get(x - 1, y);

						if(left != null){
							adjacent.add(left);
						}
					}

					// Add right
					final Location right = get(x + 1, y);
					if (right != null) {
						adjacent.add(right);
					}

					// Add up
					if(y > 0){
						final Location up = get(x, y - 1);

						if (up != null) {
							adjacent.add(up);
						}
					}

					// Add down
					final Location down = get(x, y + 1);
					if (down != null) {
						adjacent.add(down);
					}


					found = true;
				}
			}
		}


		return adjacent;
	}

	public final void forEach(Consumer<Location> task) {
		for (List<Location> col : grid) {
			for (Location location : col) {
				task.accept(location);
			}
		}
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}

}
