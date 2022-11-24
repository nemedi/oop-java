package demo.concurrency;

public class Program {

	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new CounterThread("counter.txt", "Counter " + (i + 1));
			threads[i].start();
		}

	}

}
