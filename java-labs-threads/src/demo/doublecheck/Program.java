package demo.doublecheck;

public class Program {

	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println(Singleton.getInstance().getId());
		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
	}

}
