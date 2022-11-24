package demo.doublecheck;

import java.util.UUID;

public class Singleton {

	private static Singleton instance;
	private String id;
	
	private Singleton() {
		try {
			id = UUID.randomUUID().toString();
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				createInstance();
				// createInstanceIfNull();
			}
		}
		return instance;
	}
	
	private static void createInstance() {
		instance = new Singleton();
	}
	
	private static void createInstanceIfNull() {
		if (instance == null) {
			instance = new Singleton();
		}
	}

	public String getId() {
		return id;
	}
	
}
