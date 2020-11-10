package myATM_v2;

import java.util.Random;

public class AccountManager {
	
	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	Random ran = new Random();
	UserManager um = UserManager.getInstance();
	
	void createAcc(int identifier) {
		User curUsr = um.userList[identifier];
		String newAccNumber = "";

		if(curUsr.accCnt == 0) {
			curUsr.accs = new Account[1];
		} else if (curUsr.accCnt < um.ACC_MAX_CNT && curUsr.accCnt > 0){
			Account[] temp = curUsr.accs;
			curUsr.accs = new Account[curUsr.accCnt+1];
			for (int i = 0; i < curUsr.accCnt; i++) {
				curUsr.accs[i] = temp[i];
			}
		} else if (curUsr.accCnt == um.ACC_MAX_CNT) {
			System.out.println("최대 계좌수는 3개입니다. 더이상 계좌를 만들 수 없는 회원입니다. ");
			return;			
		}
		
		boolean isDuple = false; 
		while(true) {
			int temp = ran.nextInt(100000)+1000000;
			if(um.getCheckAcc(Integer.toString(temp)) == false) {
				newAccNumber = Integer.toString(temp);
				break;
				
			}
		}  // true면 나옴 
		
		curUsr.accs[curUsr.accCnt].accNumber = newAccNumber; 
		curUsr.accs[curUsr.accCnt++].money = 0;
		
	}
	
	void printAcc (int identifier) {
		
	}

}
