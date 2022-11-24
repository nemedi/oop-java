package demo;

import static java.lang.Thread.sleep;
import static java.util.Collections.emptyList;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Flow {
	
	private static final ExecutorService executorService =
			newFixedThreadPool(20 * Runtime.getRuntime().availableProcessors());
	
	private List<Step> steps = new ArrayList<Step>();
	
	private Flow() {
	}
	
	public static Flow from(Supplier<List<?>> supplier) {
		Flow builder = new Flow();
		builder.steps.add(new FromStep(supplier));
		return builder;
	}
	
	public Flow filter(Predicate<Exchange> predicate) {
		steps.add(new FilterStep(predicate));
		return this;
	}
	
	public Flow aggregate(Function<Exchange, Object> creterion, Aggregator<Exchange> aggregator) {
		steps.add(new AggregateStep(creterion, aggregator));
		return this;
	}
	
	public Flow sort(Comparator<Exchange> comparator) {
		steps.add(new SortStep(comparator));
		return this;
	}
	
	public void to(Consumer<Exchange> consumer) {
		steps.add(new ToStep(consumer));
		executorService.submit(() -> {
			while (true) {
				try {
					List<Exchange> exchanges = emptyList();
					for (Step step : steps) {
						exchanges = step.process(exchanges);
					}
					sleep(1000);
				} catch (Throwable t) {
				}
			}
		});
	}
	
}
