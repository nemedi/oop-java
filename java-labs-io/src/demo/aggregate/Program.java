package demo.aggregate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Program {

	public static void main(String[] args) {
		try {
			aggregate("data/cities.csv", "data/districts.csv");
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	private static void aggregate(String input, String output) throws IOException {
		Map<String, Integer> inhabitantsbyDistrict = new HashMap<String, Integer>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(input)))) {
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] values = line.split(",");
				if (values.length != 4) {
					continue;
				}
				String district = values[2];
				int inhabitants = Integer.parseInt(values[3]);
				if (inhabitantsbyDistrict.containsKey(district)) {
					inhabitantsbyDistrict.put(district, inhabitantsbyDistrict.get(district) + inhabitants);
				} else {
					inhabitantsbyDistrict.put(district, inhabitants);
				}
			}
		}
		String[] districts = new String[inhabitantsbyDistrict.keySet().size()];
		inhabitantsbyDistrict.keySet().toArray(districts);
		Arrays.sort(districts);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
			for (String district : districts) {
				int inhabitants = inhabitantsbyDistrict.get(district);
				writer.write(district);
				writer.write(",");
				writer.write(Integer.toString(inhabitants));
				writer.newLine();
			}
		}
	}
}
