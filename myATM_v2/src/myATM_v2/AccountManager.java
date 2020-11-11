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
		User currUsr = um.userList[identifier];
		String newAccNumber = "";

		if(currUsr.accCnt == 0) {
			currUsr.accs = new Account[1];
		} else if (currUsr.accCnt < UserManager.ACC_MAX_CNT && currUsr.accCnt > 0){
			Account[] temp = currUsr.accs;
			currUsr.accs = new Account[currUsr.accCnt+1];
			for (int i = 0; i < currUsr.accCnt; i++) {
				currUsr.accs[i] = temp[i];
			}
		} else if (currUsr.accCnt == UserManager.ACC_MAX_CNT) {
			System.out.println("최대 계좌수는 3개입니다. 더이상 계좌를 만들 수 없는 회원입니다. ");
			return;			
		}
		
		while(true) {
			newAccNumber = Integer.toString(ran.nextInt(100000)+1000000);
			if(um.getCheckAcc(newAccNumber) == false) 
				break;
		}  // true면 나옴 
		
		currUsr.accs[currUsr.accCnt] = new Account(); // ****** NULL ERROR
		currUsr.accs[currUsr.accCnt].accNumber = newAccNumber; 
		currUsr.accs[currUsr.accCnt++].money = 0;
		System.out.printf("새로운 계좌 생성 완료! 계좌번호 : %s\n", newAccNumber);
		
	}
	
	void printAcc (int identifier) {
		User currUsr = um.userList[identifier];
		if(currUsr.accCnt != 0) {
			for (int i = 0; i < currUsr.accCnt; i++) {
				System.out.printf("(%s:%d)", currUsr.accs[i].accNumber, currUsr.accs[i].money);
				if(i != currUsr.accCnt-1) 
					System.out.print(", ");
			}
		} else if(currUsr.accCnt == 0) {
			System.out.print("아직 계좌를 가지지 않는 회원님입니다. ");
		}
		System.out.println();
	}
	
	void deleteAcc(int identifier, String accNum) {
		// accNum이 회원의 존재하는 계좌임은 이미 확인된 상태이다. 
		User currUsr = um.userList[identifier];
		int targetIdx = -1;

		if(currUsr.accCnt == 1) {
			System.out.printf("%s회원님의 계좌(%s)의 잔액 %d를 가져가세요.", currUsr.id, accNum, currUsr.accs[0].money);
			currUsr.accs = null;
		} else {
			// 몇번째 계좌인가
			for (int i = 0; i < currUsr.accCnt; i++) {
				if(currUsr.accs[i].accNumber.equals(accNum))
					targetIdx = i;
			}
			System.out.printf("%s회원님의 계좌(%s)의 잔액 %d를 가져가세요.\n\n", currUsr.id, accNum, currUsr.accs[targetIdx].money);
			// 복사
			Account[] temp = currUsr.accs;
			currUsr.accs = new Account[currUsr.accCnt-1];
			int j = 0;
			for (int i = 0; i < currUsr.accCnt; i++) {
				if(i != targetIdx) {	// ******* 또!!!!
					currUsr.accs[j++] = temp[i];
				}
			}
		} 	
		currUsr.accCnt--;
	}

}
