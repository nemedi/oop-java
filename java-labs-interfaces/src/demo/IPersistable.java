package demo;

public interface IPersistable {

	void load() throws Exception;
	
	void store() throws Exception;
}
