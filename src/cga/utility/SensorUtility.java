package cga.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SensorUtility {
	private static int WHITE_PIXEL = Color.WHITE.getRGB();

	public static int calculateArea(final BufferedImage image, String prefix, final boolean saveImage) {
		if (prefix == null)
			prefix = "";
		int toplamRenkliPiksel = 0;
		// Resmi piksel piksel dolasalim
		try {
			if (saveImage)
				ImageIO.write(image, "jpg", new File(prefix + "bas.jpg"));
			for (int j = 0; j < image.getHeight(); j++) {
				for (int i = 0; i < image.getWidth(); i++) {
					if (image.getRGB(i, j) != WHITE_PIXEL) {
						toplamRenkliPiksel++;
						image.setRGB(i, j, WHITE_PIXEL);
					}
				}
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return toplamRenkliPiksel;
	}
}
