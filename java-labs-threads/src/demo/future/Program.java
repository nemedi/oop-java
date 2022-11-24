package demo.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Program {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<String> supplier = () -> {
			Thread.sleep(1000);
			return "World!";
		};
		Future<String> future = Executors.newFixedThreadPool(5).submit(supplier);
		System.out.print("Hello");
		while (!future.isDone()) {
			System.out.print(".");
			Thread.sleep(100);
		}
		System.out.println(future.get());
		System.exit(0);
	}
}
