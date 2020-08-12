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
				g2d.setColor(new Color(map[x][y], map[x][y], map[x][y]));
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

}
