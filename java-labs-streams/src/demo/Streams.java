package demo;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Streams {

	public static Stream<Integer> from(int n) {
		return new Stream<Integer>(n,
				() -> from(n + 1));
	}
	
	public static IStream<Integer> even(IStream<Integer> s) {
		return new Stream<Integer>(s.head(),
				() -> even(s.tail().filter(n -> n % 2 == 0)));
	}
	
	public static IStream<Integer> even() {
		return even(from(2));
	}
	
	public static IStream<Integer> squared(IStream<Integer> s) {
		return new Stream<Integer>(s.head(),
				() -> squared(s.tail().filter(n -> pow((long) (sqrt(n) + 0.5), 2) == n)));
	}
	
	public static IStream<Integer> squared() {
		return squared(from(4));
	}

	public static IStream<Integer> sieve(IStream<Integer> s) {
		return new Stream<Integer>(s.head(),
				() -> sieve(s.tail().filter(n -> n % s.head() != 0)));
	}
	
	public static IStream<Integer> sieve() {
		return sieve(from(2));
	}

	public static IStream<Integer> fibonacci() {
		return new Stream<Integer>(1,
				() -> new Stream<Integer>(2, () -> nextFibonacciPair(1, 2)));
	}

	private static IStream<Integer> nextFibonacciPair(int a, int b) {
		return new Stream<Integer>(a + b, () -> nextFibonacciPair(b, a + b));
	}
}
