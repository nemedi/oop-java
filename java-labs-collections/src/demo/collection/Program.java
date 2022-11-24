package demo.collection;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		FilesCollection collection = new FilesCollection(new File("files"));
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				String command = scanner.nextLine();
				if (command == null || "exit".equals(command)) {
					break;
				} else if ("list".equals(command)) {
					for (Iterator<File> iterator = collection.iterator(); iterator.hasNext();) {
						System.out.println(iterator.next().getName());
					}
				} else if (command.startsWith("add")) {
					if (collection.add(new File(command.split("\\s+")[1]))) {
						System.out.println("OK");
					} else {
						System.out.println("KO");
					}
				} else if (command.startsWith("remove")) {
					if (collection.remove(new File(command.split("\\s+")[1]))) {
						System.out.println("OK");
					} else {
						System.out.println("KO");
					}
				} else if ("clear".equals(command)) {
					collection.clear();
				}
			}
		}
	}

}
