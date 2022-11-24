package demo.collection;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class FilesCollection implements Collection<File> {
	
	private File folder;

	public FilesCollection(File folder) {
		if (folder == null || !folder.isDirectory()) {
			throw new InvalidParameterException();
		}
		this.folder = folder;
		
	}

	@Override
	public int size() {
		return folder.listFiles().length;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object file) {
		return Arrays.asList(folder.listFiles()).contains(file);
	}

	@Override
	public Iterator<File> iterator() {
		return Arrays.asList(folder.listFiles()).iterator();
	}

	@Override
	public Object[] toArray() {
		return folder.listFiles();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] array) {
		File[] files = folder.listFiles();
		if (array != null && array.length >= files.length) {
			for (int i = 0; i < files.length; i++) {
				array[i] = (T) files[i];
			}
		}
		return array;
	}

	@Override
	public boolean add(File file) {
		try {
			File newFile = new File(folder, file.getName());
			return !newFile.exists() && newFile.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean remove(Object file) {
		return file instanceof File
				&& Arrays.asList(folder.listFiles()).contains(file)
				&& ((File) file).delete();
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		if (collection == null) {
			return false;
		}
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
			Object file = iterator.next();
			if (!(file instanceof File) || !contains(file)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends File> collection) {
		if (collection == null) {
			return false;
		}
		for (Iterator<? extends File> iterator = collection.iterator();
				iterator.hasNext();) {
			if (!add(iterator.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		if (collection == null) {
			return false;
		}
		for (Iterator<?> iterator = collection.iterator();
				iterator.hasNext();) {
			Object file = iterator.next();
			if (!(file instanceof File) || !add((File) file)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		if (collection == null) {
			return false;
		}
		ArrayList<Object> list = new ArrayList<Object>(collection);
		for (File file : folder.listFiles()) {
			if (!list.contains(file)) {
				file.delete();
			}
		}
		return true;
	}

	@Override
	public void clear() {
		for (File file : folder.listFiles()) {
			file.delete();
		}
	}

}
