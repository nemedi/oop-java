package demo.throwing;

public class Program {

	public static void main(String[] args) {
		try {
			new Student(null);
		} catch (RuntimeException e) {
			System.out.println("Runtime exception: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} catch (Error e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Throwable e) {
			System.out.println("Throwable: " + e.getMessage());
		}
		try {
			Student.createStudent(null);
		} catch (RuntimeException e) {
			System.out.println("Runtime exception: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} catch (Error e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Throwable e) {
			System.out.println("Throwable: " + e.getMessage());
		}
	}

}
