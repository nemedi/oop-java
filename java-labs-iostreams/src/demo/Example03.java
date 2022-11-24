package demo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Example03 {

	public static void main(String[] args) {
		for (Object number :
			new Object[] {2, -2, 0.5f, -0.5f, 0.5, -0.5, 0.1f, 0.1}) {
			try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
				if (number instanceof Integer) {
					new DataOutputStream(stream).writeInt((int) number);
				} else if (number instanceof Float) {
					new DataOutputStream(stream).writeFloat((float) number);
				} else if (number instanceof Double) {
					new DataOutputStream(stream).writeDouble((double) number);
				} else {
					continue;
				}
				byte[] bytes = stream.toByteArray();
				int[] masks = new int[] {1, 2, 4, 8, 16, 32, 64, 128};
				for (int i = 0; i < bytes.length; i++) {
					for (int j = masks.length - 1; j >= 0; j--) {
						System.out.print(Integer.toString((bytes[i] & masks[j]) >> j));
					}
				}
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
