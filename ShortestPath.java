/**
 *  @Rowena CS1027 | 251073629
 *  This class represents the algorithm to find the shortest path.
 */

public class ShortestPath {
	
	CityMap cityMap;
	
	/**
	 *  This is the constructor for the class. It receives as input a reference to a CityMap object 
	 *  representing the city map. In this method, instance variable cityMap is initialized to theMap.
	 */
	public ShortestPath (CityMap theMap) {
		cityMap = theMap;
	}
	
	/**
	 * This method will look for a path with the minimum number of map cells from the starting cell to
	 * the destination cell. If a path is found, this method must print a message indicating how many cells
	 * there were in the path (including the starting cell and the destination cell). If no path was found,
	 * this method must print the message “No path found”.
	 */
	public void findShortestPath() {
		int value = 1;
		OrderedCircularArray<MapCell> list = new OrderedCircularArray<MapCell>();
		MapCell start = cityMap.getStart();
		list.insert(start,0);
		start.markInList();
		
		while (!list.isEmpty()) {
			MapCell small = list.getSmallest();
			MapCell temp = nextCell(small);
			small.markOutList();
			if (small.isDestination()) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				if (temp == null || temp.isMarked() && temp.isBlock()) {
					continue;
				}
				if (temp != null) {
					int d = small.getDistanceToStart() + 1;
					if (temp.getDistanceToStart() > d) {
						temp.setDistanceToStart(d);
						temp.setPredecessor(small);
					}
				}
				value = temp.getDistanceToStart();
				if (temp.isMarkedInList() && value < list.getValue(temp)) {
					list.changeValue(temp, value);
				}
				else if (!temp.isMarkedInList()) {
					list.insert(temp, value);
					temp.markInList();
				}
			}
		}
	}
	
	/**
	 * The parameter of this method is the current map cell. This method returns the first unmarked neighboring
	 * map cell that can be used to continue the path from the current one.
	 */
	private MapCell nextCell(MapCell cell) {
		MapCell next = null;
		for (int i = 0; i < 4; i++) {
			MapCell temp = cell.getNeighbour(i);
			if (temp != null && !temp.isMarked()) { // valid cell
				
				if (temp.isDestination()) {
					if (isCellAligned(cell, i)) {
						next = temp;
						break;
					}
				}
				else if (temp.isIntersection()) {
					if (isCellAligned(cell, i)) {
						next = temp;
						break;
					}
				} 
				else if (isRoad(temp)) {
					if (isCellAligned(cell, i) && isCellAligned(temp, i)) {
						next = temp;
						break;
					}
				}
			}

		}

		return next;
	}
	
	/**
	 * Returns true if the cell is a road.
	 */
	private boolean isRoad(MapCell cell) {
		return (cell.isNorthRoad() || cell.isEastRoad() || cell.isSouthRoad() || cell.isWestRoad());
	}
	
	/**
	 * Returns true if the cell is aligned. Compares current cell to neighboring index.
	 */
	private boolean isCellAligned(MapCell cell, int index) {
		return ((cell.isNorthRoad() && index == 0) || (cell.isEastRoad() && index == 1)
				|| (cell.isSouthRoad() && index == 2) || (cell.isWestRoad() && index == 3) || (cell.isIntersection())
				|| (cell.isStart()));
	}
}
