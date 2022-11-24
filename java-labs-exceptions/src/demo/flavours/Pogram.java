package demo.flavours;

import java.util.Scanner;

public class Pogram {
	
	public static int f(int i) {
		return f(++i);
	}

	public static void main(String[] args) {
		System.out.println("1. Division by zero;");
		System.out.println("2. Out of bounds;");
		System.out.println("3. Number format;");
		System.out.println("4. Class not found.");
		System.out.println("5. Infinity recursivity;");
		System.out.println("0. Exit.");
		try (Scanner scanner = new Scanner(System.in)) {
			loop: while (true) {
				try {
					System.out.print("Select: ");
					switch(scanner.nextInt()) {
					case 0:
						break loop;
					case 1:
						System.out.println(1 / 0);
						break;
					case 2:
						System.out.println((new int[0])[1]);
						break;
					case 3:
						Integer.parseInt("1.0");
						break;
					case 4:
						Class.forName("package.Class");
					case 5:
						f(1);
						break;
					default:
						throw new Throwable("Invalid selection.");
					}
				} catch (RuntimeException e) {
					System.out.println("Runtime exception: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
				} catch (Error e) {
					System.out.println("Error: " + e.getMessage());
				} catch (Throwable e) {
					System.out.println("Throwable: " + e.getMessage());
				}
			}
		}
	}
}
