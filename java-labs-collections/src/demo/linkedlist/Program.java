package demo.linkedlist;import java.io.File;

public class Program {

	public static void main(String[] args) {
		Folder root = new Folder(new File("folder"));
		for (int i = 0; i < 4; i++) {
			System.out.println(String.format("Subfolders Depth %d:", i));
			for (File file : root.getSubfolders(i)) {
				System.out.println(file.getName());
			}
			System.out.println();
		}
	}
}
