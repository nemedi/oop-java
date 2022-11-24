package demo;

public class City implements Comparable<City> {

	private String name;
	private String district;
	private int inhabitants;
	
	public String getName() {
		return name;
	}
	public String getDistrict() {
		return district;
	}
	public int getInhabitants() {
		return inhabitants;
	}

	@Override
	public int compareTo(City city) {
		return name.compareTo(city.getName());
	}
}
