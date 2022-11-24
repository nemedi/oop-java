package demo.fallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class Program {

	public static void main(String[] args) {
		try {
			FileReader fileReader = new FileReader(new Random().nextBoolean()
					? "data/input.txt" : "data/missing.txt");
			fileReader.close();
			fileReader.read();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
			
		} catch (Exception e) {
			System.out.println("Other IO exception: " + e.getMessage());
		} finally {
			new File("data/output.txt").renameTo(new File("data/input.txt"));
		}
	}

}
