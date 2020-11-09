package myATM_v2;

public class FileManager {
	
	private FileManager () {}
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
	
	String filename;
	String data = "";
	UserManager um = UserManager.getInstance();
	
	void setData() {
		
	}
	
	void save() {
		
	}
	
	void load() {
		
	}

}
