package demo.performance;

public class Program {

	public static void main(String[] args) {
		countEvenNumbersNormally(Integer.MAX_VALUE / 200);
		countEvenNumbersWithException(Integer.MAX_VALUE / 200);
	}

	private static int countEvenNumbersNormally(int n) {
		long start = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				count++;
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);
		return count;
	}
	
	private static int countEvenNumbersWithException(int n) {
		long start = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < n; i++) {
			try {
				if (i % 2 == 0) {
					throw new Exception();
				}
			} catch (Exception e) {
				count++;
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);
		return count;
	}
}
