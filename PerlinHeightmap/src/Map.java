
public class Map {

	public static int[][] generateFinal(Config param) {
		int[][] map = new int[param.dim[0]][param.dim[1]];

		return generatePerlin(param);
	}

	static double[] randVector() {
		double x = 2.0 * Math.random() - 1.0;
		return new double[] { x, Math.sqrt(1.0 - x * x) };
	}

	public static int[][] generatePerlinNoise(Config param) {
		// Number of grid points along the x-axis
		int n = Math.floorDiv(param.dim[0], param.scale);
		// Number of grid points along the y-axis
		int m = Math.floorDiv(param.dim[1], param.scale);

		double[][][] grid = new double[n][m][2];

		// Generates random unit vectors for each corner of the grid
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = randVector();
				System.out.println(grid[i][j][0] + "," + grid[i][j][1]);
			}
		}
		
		
		int[][][] gradDistMap = new int[param.dim[0]][param.dim[1]][4];

		int[][] phMap = new int[param.dim[0]][param.dim[1]];
		return phMap;

	}

}
