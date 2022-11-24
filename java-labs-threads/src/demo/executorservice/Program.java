package demo.executorservice;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		try (Server server = new Server(2020)) {
			System.out.println("Server is running, type 'exit' to close it.");
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					if (scanner.hasNextLine() && "exit".equals(scanner.nextLine())) {
						System.exit(0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
