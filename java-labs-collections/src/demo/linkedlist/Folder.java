package demo.linkedlist;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

public class Folder {

	private File file;

	public Folder(File file) {
		if (file == null || !file.isDirectory()) {
			throw new InvalidParameterException();
		}
		this.file = file;
	}
	
	private Entry<File, Integer> entry(File file, int depth) {
		return new AbstractMap.SimpleEntry<File, Integer>(file, depth);
	}
	
	public File[] getSubfolders(int depth) {
		if (depth < 0) {
			return new File[] {};
		} else if (depth == 0) {
			return new File[] {file};
		} else {
			List<File> files = new ArrayList<File>();
			Queue<Entry<File, Integer>> queue = new LinkedList<Entry<File, Integer>>();
			queue.add(entry(file, 0));
			while (queue.size() > 0) {
				Entry<File, Integer> entry = queue.poll();
				if (entry.getValue() == depth) {
					files.add(entry.getKey());
				} else if (entry.getValue() < depth) {
					for (File file : entry.getKey().listFiles()) {
						if (file.isDirectory()) {
							queue.add(entry(file, entry.getValue() + 1));
						}
					}
				}
			}
			File[] array = new File[files.size()];
			files.toArray(array);
			Arrays.sort(array);
			return array;
		}
	}
}
