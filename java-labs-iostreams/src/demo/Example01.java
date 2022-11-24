package demo;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class Example01 {

	public static void main(String[] args) {
		for (String encoding : new String[] {"ISO-8859-1", "UTF-8"}) {
			try (FileReader reader = new FileReader("resources/Example01.txt",
					Charset.forName(encoding))) {
				int c;
				while ((c = reader.read()) != -1) {
					System.out.print((char) c);
				}
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
