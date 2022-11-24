package demo;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

public interface IStream<T> {

	T head();

	IStream<T> tail();

	boolean isEmpty();

	default IStream<T> takeWhile(Predicate<? super T> predicate) {
		return takeWhile(this, predicate);
	}

	default void forEach(Consumer<? super T> consumer) {
		forEach(this, consumer);
	}
	
	default <R> IStream<R> map(Function<T, R> mapper) {
		return map(this, mapper);
	}

	default IStream<T> filter(Predicate<? super T> predicate) {
		return filter(this, predicate);
	}
	
	default <A, R> R collect(Collector<T, A, R> collector) {
		return collect(this, collector);
	}
	
	private static <T> IStream<T> takeWhile(IStream<? extends T> stream, Predicate<? super T> predicate) {
		if (stream.isEmpty() || !predicate.test(stream.head())) {
			return new EmptyStream<T>();
		}
		return new Stream<T>(stream.head(), () -> takeWhile(stream.tail(), predicate));
	}

	private static <T> void forEach(IStream<? extends T> stream, Consumer<? super T> consumer) {
		while (!stream.isEmpty()) {
			consumer.accept(stream.head());
			stream = stream.tail();
		}
	}
	
	private static <T, R> IStream<R> map(IStream<T> stream, Function<T, R> mapper) {
		return new Stream<R>(mapper.apply(stream.head()), () -> map(stream.tail(), mapper));
		
	}

	private static <T> IStream<T> filter(IStream<? extends T> stream, Predicate<? super T> predicate) {
		if (stream.isEmpty()) {
			return new EmptyStream<T>();
		}
		if (predicate.test(stream.head())) {
			return new Stream<T>(stream.head(), () -> filter(stream.tail(), predicate));
		}
		return filter(stream.tail(), predicate);
	}

	private static <T, A, R> R collect(IStream<? extends T> stream, Collector<T, A, R> collector) {
		final A accumulator = collector.supplier().get();
		forEach (stream, item -> {
			collector.accumulator().accept(accumulator, item);
		});
		return collector.finisher().apply(accumulator);
	}
}