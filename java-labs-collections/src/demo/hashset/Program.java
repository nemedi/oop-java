package demo.hashset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Program {

	public static void main(String[] args) {
		try {
			List<City> vector = new Vector<City>();
			for (String line : Files.readAllLines(Paths.get("cities.csv"))) {
				vector.add(new City(line.split(",")));
			}
			set(vector, "district");
			set(vector, "name");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void set(List<City> vector, String field) {
		City.field(field);
		Set<City> set = new HashSet<City>();
		for (City city : vector) {
			set.add(city);
		}
		for (City city : set) {
			System.out.println(city);
		}
		System.out.println("Total: " + set.size());
		System.out.println();;
	}

}
