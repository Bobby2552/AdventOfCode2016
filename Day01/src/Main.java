import java.util.ArrayList;

/**
 * Created by bscholer on 12/9/16.
 */
public class Main {
	
	public static String directions = "R1, R3, L2, L5, L2, L1, R3, L4, R2, L2, L4, R2, L1, R1, L2, R3, L1, L4, R2, L5, R3, R4, L1, R2, L1, R3, L4, R5, L4, L5, R5, L3, R2, L3, L3, R1, R3, L4, R2, R5, L4, R1, L1, L1, R5, L2, R1, L2, R188, L5, L3, R5, R1, L2, L4, R3, R5, L3, R3, R45, L4, R4, R72, R2, R3, L1, R1, L1, L1, R192, L1, L1, L1, L4, R1, L2, L5, L3, R5, L3, R3, L4, L3, R1, R4, L2, R2, R3, L5, R3, L1, R1, R4, L2, L3, R1, R3, L4, L3, L4, L2, L2, R1, R3, L5, L1, R4, R2, L4, L1, R3, R3, R1, L5, L2, R4, R4, R2, R1, R5, R5, L4, L1, R5, R3, R4, R5, R3, L1, L2, L4, R1, R4, R5, L2, L3, R4, L4, R2, L2, L4, L2, R5, R1, R4, R3, R5, L4, L4, L5, L5, R3, R4, L1, L3, R2, L2, R1, L3, L5, R5, R5, R3, L4, L2, R4, R5, R1, R4, L3";
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;
	
	public static void main (String[] args) {
		
		int[] totals = findDestination(directions);
		System.out.printf("%d, %d\n", totals[0], totals[1]);
		int distance = calcShortestDistance(totals);
		System.out.println(distance);
		
	}
	
	/**
	 * Adds directions
	 *
	 * @param directions
	 * 		R3, L8
	 *
	 * @return (x, y)
	 */
	public static int[] findDestination (String directions) {
		
		ArrayList<int[]> visited = new ArrayList<>( );
		int[] total = new int[2];
		String[] dirs = directions.split(", ");
		//1 - NORTH
		int currentDirection = NORTH;
		
		for (String dir : dirs) {
			if (dir.substring(0, 1).equals("R")) {
				currentDirection++;
				if (currentDirection > WEST) currentDirection = NORTH;
			}
			if (dir.substring(0, 1).equals("L")) {
				currentDirection--;
				if (currentDirection < NORTH) currentDirection = WEST;
			}
			int distance = Integer.parseInt(dir.substring(1));
			
			for (int i = 0; i < distance; i++) {
				total[currentDirection % 2] += (currentDirection == NORTH || currentDirection == EAST) ? 1 :
						- 1;
				for (int[] loc : visited) {
					if (loc[0] == total[0] && loc[1] == total[1]) {
						return total;
					}
				}
				visited.add(total);
			}
			System.out.printf("%d, %d\n", total[0], total[1]);
		}
		
		return total;
	}
	
	public static int calcShortestDistance (int[] totals) {
		
		return Math.abs(totals[0]) + Math.abs(totals[1]);
	}
}
