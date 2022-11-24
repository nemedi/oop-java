package demo.resource;

public class Resource implements AutoCloseable {
	
	public Resource() {
		System.out.println("Resource has been opened.");
	}
	
	public void doSomething() throws Exception {
		throw new Exception("Something went wrong.");
	}

	@Override
	public void close() throws Exception {
		System.out.println("Resource has been closed.");
	}

}
