package streams;

import static java.util.stream.Collectors.counting;
import static streams.Streams.even;
import static streams.Streams.fibonacci;
import static streams.Streams.sieve;
import static streams.Streams.squared;

public class Program {

	public static void main(String[] args) {
		printStream("Even", even());
		printStream("Squared", squared());
		printStream("Fibonacci", fibonacci());
		printStream("Sieve", sieve());
	}
	
	private static <T> void printStream(String name, IStream<Integer> stream) {
		stream = stream.takeWhile(n -> n <= 100);
		System.out.println(name + ":");
		stream.forEach(System.out::println);
		System.out.println(String.format("Count = %d",
				stream.collect(counting()).intValue()));
		System.out.println();
	}

}
