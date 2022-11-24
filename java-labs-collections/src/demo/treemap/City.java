package demo.treemap;

public class City {

	private String name;
	private String district;
	
	public City(String[] values) {
		this.name = values[0];
		this.district = values[1];
	}
	
	public String getName() {
		return name;
	}
	
	public String getDistrict() {
		return district;
	}
}
