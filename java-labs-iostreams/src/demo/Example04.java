package demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Example04 {

	public static void main(String[] args) {
		try {
			BufferedImage imageA = ImageIO.read(new File("resources/imageA.png"));
			BufferedImage imageB = ImageIO.read(new File("resources/imageB.png"));
			if (imageA.getWidth() != imageB.getWidth() || imageA.getHeight() != imageB.getHeight()) {
				throw new IllegalArgumentException("Dimensions are not the same!");
			}
			BufferedImage imageAB = new BufferedImage(imageA.getWidth(),
					imageA.getHeight(),
					BufferedImage.TYPE_INT_ARGB_PRE);
			for (int y = 0; y < imageA.getHeight(); y++) {
				for (int x = 0; x < imageA.getWidth(); x++) {
					imageAB.setRGB(x, y,
							imageA.getRGB(x, y) & imageB.getRGB(x, y));
				}
			}
			imageAB.flush();
			ImageIO.write(imageAB, "png", new File("resources/imageAB.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
