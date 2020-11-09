package myATM_v2;

public class AccountManager {
	
	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	UserManager um = UserManager.getInstance();
	
	void createAcc(int identifier) {
		
	}
	
	void printAcc (int identifier) {
		
	}

}
