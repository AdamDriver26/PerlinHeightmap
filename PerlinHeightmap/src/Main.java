import java.io.File;

/**
 * @author Adam Driver
 *
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File configFile = new File("config.txt");
		
		if (!configFile.exists()) {
			try {
				Config.write();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Config param = null;
		try {
			param = Config.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Currently generates a map of random noise to be drawn
		int[][] map = new int[param.dim[0]][param.dim[1]];
		for (int x = 0; x < param.dim[0]; x++) {
			for (int y = 0; y < param.dim[1]; y++) {
				map[x][y] = (int) (Math.random() * 255);
			}
		}

		
		DrawHeights.drawPNG(param,Map.generateFinal(param));

	}

}
