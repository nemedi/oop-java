package demo.hashset;

public class City {
	
	private String name;
	private String district;
	private static String field = "name";
	
	public City(String[] values) {
		name = values[0];
		district = values[1];
	}
	
	public static void field(String field) {
		City.field = field;
	}
	
	@Override
	public String toString() {
		return String.format("Name = %s, District = %s", name, district);
	}
	
	@Override
	public int hashCode() {
		return "name".equalsIgnoreCase(field) ? name.hashCode() : district.hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		return object instanceof City
				&& ("name".equals(field) && name.equals(((City) object).name)
				|| "district".equals(field) && district.equals(((City) object).district));
	}
}
