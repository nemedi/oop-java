package demo.treemap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Program {

	public static void main(String[] args) {
		try {
			Map<String, Integer> map = new TreeMap<String, Integer>();
			for (String line : Files.readAllLines(Paths.get("cities.csv"))) {
				City city = new City(line.split(","));
				if (map.containsKey(city.getDistrict())) {
					map.put(city.getDistrict(), map.get(city.getDistrict()) + 1);
				} else {
					map.put(city.getDistrict(), 1);
				}
			}
			for (Entry<String, Integer> entry : map.entrySet()) {
				System.out.println(String.format("%s: %d",
						entry.getKey(), entry.getValue()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
