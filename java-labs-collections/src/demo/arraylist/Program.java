package demo.arraylist;

public class Program {

	public static void main(String[] args) {
		System.out.println("Fibonacci Stream:");
		for (int i = 1; i <= 10; i++) {
			System.out.println(String.format("f(%d) = %d", i, FibonacciStream.get(i)));
		}
		System.out.println("...");
		System.out.println(String.format("f(%d) = %d", 20, FibonacciStream.get(20)));
	}

}
