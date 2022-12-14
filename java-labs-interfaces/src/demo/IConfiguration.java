package demo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

public interface IConfiguration extends IPrintable, IPersistable, IPropertyManager {
	
	String getPath();

	default void print() {
		for (Entry<String, Object> entry : getProperties().entrySet()) {
			System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
		}
	}

	default void load() throws Exception {
		if (!(this instanceof ISerializable)) {
			throw new Exception(String.format("Type %s is not serializable.", getClass().getName()));
		}
		for (String line : Files.readAllLines(Paths.get(getPath()))) {
			String[] values = line.split("\\s*=\\s*");
			if (values.length == 2) {
				setProperty(values[0], values[1]);
			}
		}
	}

	default void store() throws Exception {
		if (!(this instanceof ISerializable)) {
			throw new Exception(String.format("Type %s is not serializable.", getClass().getName()));
		}
		StringBuilder builder = new StringBuilder();
		for (Entry<String, Object> entry : getProperties().entrySet()) {
			if (builder.length() > 0) {
				builder.append(System.getProperty("line.separator"));
			}
			builder
				.append(entry.getKey())
				.append("=")
				.append(entry.getValue());
		}
		Files.write(Paths.get(getPath()), builder.toString().getBytes());
	}

	default void update(Map<String, Object> configuration) {
		getProperties().putAll(configuration);
	}
	
	default void visit(IConfigurationVisitor visitor) {
		for (Entry<String, Object> entry : getProperties().entrySet()) {
			visitor.visit(entry.getKey(), entry.getValue());
		}
	}

}
