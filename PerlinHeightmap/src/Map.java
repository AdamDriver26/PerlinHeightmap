
public class Map {

	public static int[][] generateFinal(Config param) {
		
		int[][] map = new int[ param.dim[0] ][ param.dim[1] ];
		int[][] layer1 = new int[ param.dim[0] ][ param.dim[1] ];
		int[][] layer2 = new int[ param.dim[0] ][ param.dim[1] ];
		int[][] layer3 = new int[ param.dim[0] ][ param.dim[1] ];
		int[][] layer4 = new int[ param.dim[0] ][ param.dim[1] ];
		int[][] layer5 = new int[ param.dim[0] ][ param.dim[1] ];
		
		// if/else for whether you want an island generated. If true use 3D/rotated Gaussian distribution. Otherwise use another layer of perlin noise with 2*scale.
		
		layer1 = generatePerlinNoise(param.dim, 2*param.scale);
		layer2 = generatePerlinNoise(param.dim, param.scale);
		layer3 = generatePerlinNoise(param.dim, param.scale/2);
		layer4 = generatePerlinNoise(param.dim, param.scale/4);
		layer5 = generatePerlinNoise(param.dim, param.scale/8);
		
		for (int x = 0; x < param.dim[0]; x++) {
			for (int y = 0; y < param.dim[1]; y++) {
				
				map[x][y] = (16*layer1[x][y] + 8*layer2[x][y] + 4*layer3[x][y] + 2*layer4[x][y] + layer5[x][y])/31;
			}
		}
		

		return map;
	}

	static double[] randUnitVector() {
		double[] randVector = new double[] { 2.0 * Math.random() - 1.0, 2.0 * Math.random() - 1.0 };
		double len = Math.sqrt(randVector[0]*randVector[0] + randVector[1]*randVector[1]);
		return new double[] { randVector[0]/len, randVector[1]/len };
	}
	
	 // Exception handling may require changing
	static double dotProduct(double[] u, double[] v) {
//		if (u.length != 2 || v.length != 2) {
//			throw new Exception("Vectors are incorrect sizes");
//		}
		return u[0]*v[0] + u[1]*v[1];
	}
	
	static double easeCurve(double t) {
		return 6.0*Math.pow(t, 5.0) - 15.0*Math.pow(t, 4.0) + 10.0*Math.pow(t, 3.0);
	}
	
	static double linearInterpolation(double a, double b, double t) {
		return t*(b - a) + a;
	}

	public static int[][] generatePerlinNoise(int[] dim, int scale) {
		// Number of grid points along the x-axis
		int n = Math.floorDiv(dim[0], scale);
		// Number of grid points along the y-axis
		int m = Math.floorDiv(dim[1], scale);

		double[][][] randVectorGrid = new double[n+1][m+1][2];

		// Generates random unit vectors for each corner of the grid
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				randVectorGrid[i][j] = randUnitVector();
				//System.out.println(randVectorGrid[i][j][0] + "," + randVectorGrid[i][j][1]);
			}
		}
		
		int[][] map = new int[ dim[0] ][ dim[1] ];
		
		// Iterates elements of the map without exceeding the edges of the grid.
		for (int x = 0; x < n*scale - 1; x++) {
			for (int y = 0; y < m*scale - 1; y++) {
				
				// For an element of the map within the grid finds the "leadingIndex", i.e. the index of the top left local grid corner.
				int[] leadingIndex = new int[] {Math.floorDiv(x, scale), Math.floorDiv(y, scale)};
				//System.out.println(leadingIndex[0] + "," + leadingIndex[1] + ",," + x + "," + y);
				
				// Scales x and y to lie within [0,1]
				double xScaled = ((double) (x - leadingIndex[0]*scale))/((double) scale);
				double yScaled = ((double) (y - leadingIndex[1]*scale))/((double) scale);
				
				// Finds displacement vectors from each corner of the enclosing grid to the point (x,y)
				double[] topLeftDisplacement = new double[] {xScaled, yScaled};
				double[] topRightDisplacement = new double[] {xScaled - 1.0, yScaled};
				double[] bottomLeftDisplacement = new double[] {xScaled, yScaled - 1.0};
				double[] bottomRightDisplacement = new double[] {xScaled - 1.0, yScaled - 1.0};
				
				// The dot product between the unit vector and displacement vector for each corner is taken in order to calculate the impact on the shading of (x,y)
				double topLeftImpact = dotProduct(randVectorGrid[ leadingIndex[0] ][ leadingIndex[1] ], topLeftDisplacement);					// s
				double topRightImpact = dotProduct(randVectorGrid[ leadingIndex[0] + 1 ][ leadingIndex[1] ], topRightDisplacement);				// t
				double bottomLeftImpact = dotProduct(randVectorGrid[ leadingIndex[0] ][ leadingIndex[1] + 1], bottomLeftDisplacement);			// u 
				double bottomRightImpact = dotProduct(randVectorGrid[ leadingIndex[0] + 1][ leadingIndex[1] + 1], bottomRightDisplacement);		// v
				
				// Uses the ease curve function to ease values towards integral points
				double xEased = easeCurve(xScaled);			// S_x
				double yEased = easeCurve(yScaled);			// S_y
				
				double topAverage = linearInterpolation(topLeftImpact,topRightImpact,xEased);
				double bottomAverage = linearInterpolation(bottomLeftImpact,bottomRightImpact,xEased);
				
				map[x][y] += ( (int) 255.0 * ( linearInterpolation(topAverage,bottomAverage,yEased) + 1.0 ) ) / 2;

			}
		}

		return map;

	}

}
