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
		
		DrawHeights.drawPNG(param,Map.generateFinal(param));

	}

}
