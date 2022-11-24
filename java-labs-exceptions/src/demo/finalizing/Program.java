package demo.finalizing;

import java.util.Random;

public class Program {

	public static void main(String[] args) {
		for (String planet : new String[] {"Neptune",
				"Uranus",
				"Saturn",
				"Jupiter",
				"Mars",
				"Earth",
				"Venus",
				"Mercury",
				"Sun"}) {
			try {
				System.out.print(String.format("Entering %s...", planet));
				visit(planet);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			} finally {
				System.out.println(String.format("leaving %s.", planet));
			}
		}
	}

	private static void visit(String planet) throws Exception {
		System.out.print(String.format("visiting %s...", planet));
		if ("Sun".equals(planet)) {
			System.exit(0);
		}
		if (new Random().nextBoolean()) {
			throw new Exception("something went wrong...");
		}
		System.out.print("all went well...");
	}

}
