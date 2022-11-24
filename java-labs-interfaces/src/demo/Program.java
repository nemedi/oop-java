package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static interfaces.IDatabaseConfigurationConstants.PASSWORD;
import static interfaces.IFolderConfigurationConstants.FILTER;

public class Program {

	public static void main(String[] args) throws Exception {
		IConfiguration[] configurations = new IConfiguration[] {
				new DatabaseConfiguration("./resources/database.cfg"),
				new FolderConfiguration("./resources/folder.cfg")
		};
		for (IConfiguration configuration : configurations) {
			System.out.println("==================================================");
			System.out.println(configuration.getClass().getSimpleName());
			configuration.load();
			configuration.print();
			configuration.visit((key, value) ->
				System.out.println(String.format("Key = %s, Value = %s", key, value)));
			Map<String, Object> properties = new HashMap<String, Object>();
			String[] valuesToUpdateWith = null;
			String keyToUpdate = null;
			if (configuration instanceof DatabaseConfiguration) {
				valuesToUpdateWith = new String[] {"test", "demo", "password"};
				keyToUpdate = PASSWORD;
			} else {
				valuesToUpdateWith = new String[] {"*.txt", "*.csv", "*.xml"};
				keyToUpdate = FILTER;
			}
			int index = new Random().nextInt(100) % valuesToUpdateWith.length;
			properties.put(keyToUpdate, valuesToUpdateWith[index]);
			configuration.update(properties);
			configuration.store();
			configuration.load();
			if (configuration instanceof DatabaseConfiguration) {
				assert valuesToUpdateWith[index].equals(((DatabaseConfiguration) configuration).getPassword());
			} else if (configuration instanceof FolderConfiguration) {
				assert valuesToUpdateWith[index].equals(((FolderConfiguration) configuration).getFilter());
			}
			System.out.println("==================================================");
			System.out.println();
		}
		
	}

}
