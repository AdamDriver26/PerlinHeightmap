import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Adam
 *
 */

public class Config {
	// The name of the resulting image
	String name;
	// The map dimensions as an array
	int[] dim;
	// Where to draw sea level
	int seaLevel;
	// The size of each square of the grid used to create the Perlin noise
	int scale;
	//
	String style;

	Config(String n, int[] D, int l, int sc, String st) {
		name = n;
		dim = D;
		seaLevel = l;
		scale = sc;
		style = st;
		
	}

	public static void write() throws Exception {

		String defaultName = "heightmap";
		int[] defaultDim = { 1920, 1080 };
		int defaultSeaLevel = 110;
		int defaultScale = 115;
		String defaultStyle = "island";
				
		Config defaultConfig = new Config(defaultName, defaultDim, defaultSeaLevel, defaultScale, defaultStyle);

		File param = new File("config.txt");
		PrintStream p = new PrintStream(param);

		p.println("Filename: " + defaultConfig.name);
		p.println("Dimensions: " + defaultConfig.dim[0] + "," + defaultConfig.dim[1]);
		p.println("Sea level: " + defaultConfig.seaLevel);
		p.println("Noise scale: " + defaultConfig.scale);
		p.println("Map style: " + defaultConfig.style);

		p.close();

	}

	public static Config read() throws Exception {

		File param = new File("config.txt");

		Scanner s = new Scanner(param);

		String name = s.nextLine().substring(10);
		String[] stringDim = s.nextLine().substring(12).split(","); // Reads 
		int[] dim = {Integer.parseInt(stringDim[0]),Integer.parseInt(stringDim[1])};
		int seaLevel = Integer.valueOf(s.nextLine().substring(11));
		int scale = Integer.valueOf(s.nextLine().substring(13));
		String style = s.nextLine().substring(11);

		s.close();

		return new Config(name, dim, seaLevel, scale, style);

	}

}
