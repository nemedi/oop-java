package demo.throwing;

public class Student {

	public Student(String name) {
		if (name == null) {
			throw new InstantiationError("Name cannot be null.");
		}
	}
	
	public static Student createStudent(String name) throws Exception {
		try {
			return new Student(name);
		} catch (Throwable e) {
			throw new Exception(e.getMessage(), e);
		}
	}
}
