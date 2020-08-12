import java.io.File;
import java.io.FileNotFoundException;
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
	// the name of the resulting image
	String name;
	// The map dimensions as an array
	int[] dim;
	// Sea level
	int seaLevel;

	Config(String n, int[] d, int s) {
		name = n;
		dim = d;
		seaLevel = s;
	}

	public static void write() throws Exception {

		String defaultName = "heightmap";
		int[] defaultDim = { 100, 100 };
		int defaultSeaLevel = 20;
		Config defaultConfig = new Config(defaultName, defaultDim, defaultSeaLevel);

		File param = new File("config.txt");
		PrintStream p = new PrintStream(param);

		p.println("Filename: " + defaultConfig.name);
		p.println("Dimensions: " + defaultConfig.dim[0] + "," + defaultConfig.dim[1]);
		p.println("Sea level: " + defaultConfig.seaLevel);

		p.close();

	}

	public static Config read() throws Exception {

		File param = new File("config.txt");

		Scanner s = new Scanner(param);

		String name = s.nextLine().substring(10);
		String[] stringDim = s.nextLine().substring(12).split(","); // Reads 
		int[] dim = {Integer.parseInt(stringDim[0]),Integer.parseInt(stringDim[1])};
		int seaLevel = Integer.valueOf(s.nextLine().substring(11));

		s.close();

		return new Config(name, dim, seaLevel);

	}

}
