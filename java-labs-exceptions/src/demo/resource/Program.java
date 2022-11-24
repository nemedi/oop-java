package demo.resource;

public class Program {

	public static void main(String[] args) {
		try (Resource resource = new Resource()) {
			resource.doSomething();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
