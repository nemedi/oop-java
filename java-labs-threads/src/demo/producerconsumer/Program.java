package demo.producerconsumer;

public class Program {

	public static void main(String[] args) {
		try {
			Storage<String> storage = new Storage<String>(3);
			ProducerThread<String> producerThread = new ProducerThread<String>(storage, () -> "COVID-19");
			ConsumerThread<String> consumerThread = new ConsumerThread<String>(storage, System.out::println);
			producerThread.start();
			consumerThread.start();
			producerThread.join();
			consumerThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
