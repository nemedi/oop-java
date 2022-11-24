package demo.invert;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {

	public static void main(String[] args) {
		try {
			invert("data/input.bmp", "data/output.bmp");
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	private static void invert(String input, String output) throws IOException {
		try (FileInputStream inputStream = new FileInputStream(input);
				FileOutputStream outputStream = new FileOutputStream(output)) {
			byte[] header = new byte[54];
			inputStream.read(header);
			outputStream.write(header);
			int rgb;
			while((rgb = inputStream.read()) != -1) {
				Color color = new Color(rgb);
				int red = 255 - color.getRed();
				int green = 255 - color.getGreen();
				int blue = 255 - color.getBlue();
				outputStream.write(new Color(red, green, blue).getRGB());
			}
		}

	}

}
