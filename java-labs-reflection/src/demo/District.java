package demo;

public class District implements Comparable<District> {

	private String name;
	private int inhabitants;
	
	public District(String name) {
		this.name = name;
	}
	
	public void addCity(City city) {
		if (name.equals(city.getDistrict())) {
			inhabitants += city.getInhabitants();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getInhabitants() {
		return inhabitants;
	}

	@Override
	public int compareTo(District district) {
		return name.compareTo(district.getName());
	}
}
