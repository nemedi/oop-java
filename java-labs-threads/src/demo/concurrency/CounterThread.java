package demo.concurrency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CounterThread extends Thread {
	
	private String path;

	public CounterThread(String path, String name) {
		this.path = path;
		setName(name);
	}
	
	@Override
	public void run() {
		int counter = getCounter();
		long previousVersion = getVersion();
		while (true) {
			try {
				counter++;
				Thread.sleep((long) (1000 * Math.random()));
				long currentVersion = getVersion();
				if (currentVersion == previousVersion) {
					setCounter(counter);
					System.out.println(String.format("%s: %d", getName(), counter));
				} else {
					counter = getCounter();
					previousVersion = currentVersion;
				}
			} catch (InterruptedException e) {
			}
		}
	}
	
	private void setCounter(int counter) {
		try {
			Files.write(Paths.get(path), Integer.toString(counter).getBytes());
		} catch (IOException e) {
		}
	}

	private int getCounter() {
		try {
			return Paths.get(path).toFile().exists()
					? Integer.parseInt(new String(Files.readAllBytes(Paths.get(path))))
					: 0;
		} catch (NumberFormatException | IOException e) {
			return 0;
		}
	}
	
	private long getVersion() {
		return Paths.get(path).toFile().exists()
				? Paths.get(path).toFile().lastModified()
				: 0;
	}

}
