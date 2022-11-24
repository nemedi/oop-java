package demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Program {

	public static void main(String[] args) {
		try {
			City[] cities = CsvRepository.getInstance().read("data/cities.csv", City.class);
			Map<String, District> districts = new HashMap<String, District>();
			for (City city : cities) {
				String district = city.getDistrict();
				if (!districts.containsKey(district)) {
					districts.put(district, new District(district));
				}
				districts.get(district).addCity(city);
			}
			Object[] values = districts.values().toArray();
			Arrays.sort(values);
			CsvRepository.getInstance().write(values, "data/districts.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
