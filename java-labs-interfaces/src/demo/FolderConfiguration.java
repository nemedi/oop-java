package demmo;

public class FolderConfiguration extends Configuration
	implements IFolderConfigurationConstants, ISerializable {

	public FolderConfiguration(String path) {
		super(path);
	}
	
	public String getRoot() {
		return (String) getProperty(ROOT);
	}
	
	public void setRoot(String value) {
		setProperty(ROOT, value);
	}
	
	public String getFilter() {
		return (String) getProperty(FILTER);
	}
	
	public void setFilter(String value) {
		setProperty(FILTER, value);
	}
	
	@Override
	public void print() {
		String.format("%s/%s", getRoot(), getFilter());
	}

}
