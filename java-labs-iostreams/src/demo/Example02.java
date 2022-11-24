package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Example02 {

	public static void main(String[] args) {
		
		final String path = "resources/Example02.txt";
		
		try (FileOutputStream stream = new FileOutputStream(path)) {
			System.out.print("writting... ");
			int[] numbers = new int[10];
			Random random = new Random();
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = random.nextInt(100);
				System.out.print(numbers[i] + " ");
			}
			stream.write(numbers.length);
			for (int i = 0; i < numbers.length; i++) {
				stream.write(numbers[i]);
			}
			System.out.println("...done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (FileInputStream stream = new FileInputStream(path)) {
			System.out.print("reading... ");
			int[] numbers = new int[stream.read()];
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = stream.read();
				System.out.print(numbers[i] + " ");
			}
			System.out.println("...done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
