package demo;

public class District {

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
	public String toString() {
		return String.format("%s,%d", name, inhabitants);
	}
}
