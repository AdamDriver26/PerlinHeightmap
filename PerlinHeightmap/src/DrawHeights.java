import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawHeights {

	public static void drawPNG(Config param, int[][] map) {

		// Creates an image with the dimensions specified in the config.txt parameters 
		BufferedImage draw = new BufferedImage(param.dim[0], param.dim[1], BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = draw.createGraphics();

		// Iterates through each pixel in the image and shades it using the value in the map array
		for (int x = 0; x < param.dim[0]; x++) {
			for (int y = 0; y < param.dim[1]; y++) {
				
				if (map[x][y] < param.seaLevel) {
					int blue = 204*map[x][y]/param.seaLevel;
					g2d.setColor(new Color(0, blue/2, blue));
				}
				
				else if (steepnessCheck(param, map, x, y) < 3*param.grassThreshold/4 && map[x][y] < 1.05*param.seaLevel) {
					int green = 204*(map[x][y] - param.seaLevel)/(255-param.seaLevel) + 51;
					g2d.setColor(new Color(194, 178, 128));
				}
				
				else if (steepnessCheck(param, map, x, y) < param.grassThreshold) {
					int green = 204*(map[x][y] - param.seaLevel)/(255-param.seaLevel) + 51;
					g2d.setColor(new Color(green/4, green, green/4));
				}
				
				else {
					int brown = 204*(map[x][y] - param.seaLevel)/(255-param.seaLevel) + 51;
					//g2d.setColor(new Color(brown, ( (int) 0.76*brown ), ( (int) 0.53*brown )));
					g2d.setColor(new Color(brown, brown, brown));
				}
				
				g2d.drawLine(x, y, x, y);
			}
		}

		// Writes the image to a png file with the name specified in the config.txt parameters 
		File file = new File(param.name + ".png");
		try {
			ImageIO.write(draw, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static double steepnessCheck(Config param, int[][] map, int x, int y) {
		double angleTotal = 0.0;
		int count = 0;
		boolean result = false;
		
		// North
		if (y > 0) {
			angleTotal += Math.atan( Math.abs(map[x][y - 1] - map[x][y]) );
			count++;
		}
		
		// North East
		if ( (y > 0) && (x < param.dim[0] - 1) ) {
			angleTotal += Math.atan( Math.abs(map[x + 1][y - 1] - map[x][y]) / 1.414 );
			count++;
		}
		
		// East
		if (x < param.dim[0] - 1) {
			angleTotal += Math.atan( Math.abs(map[x + 1][y] - map[x][y]) );
			count++;
		}
		
		// South East
		if ( (x < param.dim[0] - 1) && (y < param.dim[1] - 1) ) {
			angleTotal += Math.atan( Math.abs(map[x + 1][y + 1] - map[x][y]) / 1.414);
			count++;
		}
		
		// South
		if (y < param.dim[1] - 1) {
			angleTotal += Math.atan( Math.abs(map[x][y + 1] - map[x][y]) );
			count++;
		}
		
		// South West
		if ( (x > 0) && (y < param.dim[1] - 1) ) {
			angleTotal += Math.atan( Math.abs(map[x - 1][y + 1] - map[x][y]) / 1.414);
			count++;
		}
		
		// West
		if (x > 0) {
			angleTotal += Math.atan( Math.abs(map[x - 1][y] - map[x][y]) );
			count++;
		}
		
		// North West
		if ( (x > 0) && (y > 0) ) {
			angleTotal += Math.atan( Math.abs(map[x - 1][y - 1] - map[x][y]) / 1.414);
			count++;
		}
		
		// Scales the average angle of all counted neighbours to degrees.
		return (180.0/3.14)*angleTotal/count;
	}

}
