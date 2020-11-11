package myATM_v2;

public class User {
	
	String id;
	String pw;
	Account[] accs;
	int accCnt;
	
	User(){
		
	}
	User(String id, String pw, int accCnt){
		this.id = id;
		this.pw = pw;
		this.accCnt = accCnt;
	}

}
