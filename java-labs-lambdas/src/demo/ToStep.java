package demo;

import static java.util.Collections.emptyList;
import java.util.List;
import java.util.function.Consumer;

public class ToStep implements Step {

	private Consumer<Exchange> consumer;

	public ToStep(Consumer<Exchange> consumer) {
		this.consumer = consumer;
	}

	@Override
	public List<Exchange> process(List<Exchange> exchanges) {
		exchanges.forEach(consumer);
		return emptyList();
	}

}
