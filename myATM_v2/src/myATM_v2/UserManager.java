package myATM_v2;

import java.util.Scanner;

public class UserManager {
	
	private UserManager() {}
	private static UserManager instance = new UserManager();
	public static UserManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	static int ACC_MAX_CNT = 3;
	User[] userList = null;
	int userCnt = 0;
	
	void setDummy() {
		
		String[] ids = {"user1","user2","user3","user4","user5"};
		String[] pws = {"1111","2222","3333","4444","5555"};
		String[] accs = {"1234567","2345692","1078912","2489123","7391234"};
		int[] moneys = {87000,12000,49000,34000,128000};
		userCnt = 5;
		userList = new User[userCnt];
		
		for (int i = 0; i < userCnt; i++) {
			userList[i] = new User(); 			// *****
			userList[i].id = ids[i];
			userList[i].pw = pws[i];
			userList[i].accs[0] = new Account(); // ******
			userList[i].accs[0].accNumber = accs[i];
			userList[i].accs[0].money = moneys[i];
			userList[i].accCnt++;
		}
	}
	
	void printAllUser() {
		for (int i = 0; i < userCnt; i++) {
			System.out.printf("[%d] ID(%s) PW(%s)\t", i+1, userList[i].id, userList[i].pw);
			if(userList[i].accCnt != 0) {
				for (int j = 0; j < userList[i].accCnt; j++) {
					System.out.printf("(%s:%d)", userList[i].accs[j].accNumber, userList[i].accs[j].money);
					if(j != userList[i].accCnt-1) {
						System.out.print(", ");
					}
				}
			}
			System.out.println();
		}
	}
	
	boolean getCheckAcc(String acc) {
		boolean nDuple = false;
		for (int i = 0; i < userCnt; i++) {
			for (int j = 0; j < userList[i].accCnt; j++) {
				if(userList[i].accs[j].accNumber.equals(acc)) {
					nDuple = true;
				}
			}
		}
		return nDuple;
	}
	
	int logUser() {
		int identifier = -1;
		// 아이디와 비밀번호를 입력받는다. 
		System.out.print("...ID : ");
		String myId = scan.next();
		System.out.print("...PW : ");
		String myPw = scan.next();
		
		// 아이디가 없는 아이디면 리턴
		identifier = checkId(myId);
		if(identifier == -1 || !userList[identifier].pw.equals(myPw)) 
			return -1;
		// 아이디가 있는경우 조회, 아이디와 비밀번호가 일치하지 않으면
		// 아이디와 비밀번호가 일치하면
		return identifier;
		
	}
	
	int checkId(String id) {
		int nExist = -1;
		for (int i = 0; i < userCnt; i++) {
			if(id.equals(userList[i].id)) {
				nExist = i;
				
			}
		}
		return nExist;		
	}
	
	void joinMember() {
		
	}
	
	void deleteMember() {
		
	}

}
