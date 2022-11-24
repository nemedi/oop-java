package demo;

import static demo.Flow.from;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		from(() -> {
			List<City> cities = emptyList();
			try {
				File csvFile = Arrays.stream(Paths.get("data/input").toFile().listFiles())
					.filter(file -> file.isFile() && file.getName().toLowerCase().endsWith(".csv"))
					.findFirst()
					.orElse(null);
				if (csvFile != null) {
					cities = Files.readAllLines(csvFile.toPath())
						.stream()
						.map(line -> new City(line))
						.collect(toList());
					csvFile.delete();
				}
			} catch (IOException e) {
			}
			return cities;
		})
		.filter(exchange -> exchange.getBody(City.class).getDistrict() != null)
		.aggregate(exchange -> exchange.getBody(City.class).getDistrict(), (oldExchange, newExchange) -> {
			City city = newExchange.getBody(City.class);
			District district = oldExchange == null
					? new District(city.getDistrict())
					: oldExchange.getBody(District.class);
			district.addCity(city);
			Exchange exchange = oldExchange == null ? newExchange : oldExchange;
			exchange.setBody(district);
			return exchange;
		})
		.sort((firstExchange, secondExchange) -> 
			firstExchange.getBody(District.class).getName()
				.compareTo(secondExchange.getBody(District.class).getName()))
		.aggregate(exchange -> true, (oldExchange, newExchange) -> {
			District district = newExchange.getBody(District.class);
			StringBuilder builder = oldExchange == null
					? new StringBuilder()
					: oldExchange.getBody(StringBuilder.class);
			builder.append(district.toString())
				.append(System.getProperty("line.separator"));
			Exchange exchange = oldExchange == null ? newExchange : oldExchange;
			exchange.setBody(builder);
			return exchange;
		})
		.to(exchange -> {
			try {
				String path = String.format("data/output/%s.csv", exchange.getId());
				Files.writeString(Paths.get(path),
						exchange.getBody(StringBuilder.class).toString());
				System.out.println(String.format("Results were saved to %s.", path));
			} catch (IOException e) {
			}
		});
		
		System.out.println("Copy CSV files to data/input to be processed into data/output. Type 'exit' to quit.");
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				if (scanner.hasNextLine() && "exit".equalsIgnoreCase(scanner.nextLine())) {
					System.exit(0);
				}
			}
		}
	}

}
