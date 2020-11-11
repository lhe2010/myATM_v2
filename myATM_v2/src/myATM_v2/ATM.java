package myATM_v2;

import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	
	int identifier;
	UserManager um = UserManager.getInstance();
	AccountManager am = AccountManager.getInstance();
	FileManager fm = FileManager.getInstance();
	
	void play() {
		fm.load();
//		um.setDummy();
//		um.printAllUser();
		
		while(true) {
			System.out.println("\n[ATM V2 동작중...]");
			System.out.println("[1. 회원가입]\n[2. 로그인]\n[3. 전체 회원출력]\n[0. 저장/종료]\n[4. atm2.txt에서 가져오기]\n");
			System.out.print("...메뉴를 선택하세요 : ");
			int sel = scan.nextInt();
			if(sel == 1) {			join();
			} else if (sel == 2) {	login();
			} else if (sel == 3) {  um.printAllUser();
			} else if (sel == 0) {	
				System.out.println("[ATM V2 종료]");
				fm.save();
				break;
			} else if(sel == 4) {
				fm.load();
			}
		}
	}
	
	void login() {
		identifier = um.logUser();
		if(identifier == -1)
			System.out.println("아이디와 비밀번호를 다시 확인하고 이용해주세요.");
		else 
			loginMenu();
	}
	 
	void loginMenu() {
		while(true) {
			fm.save();
			System.out.printf("\n[%s님이 로그인중]\n", um.userList[identifier].id);
			System.out.println("[1.계좌생성]\n[2.계좌삭제]\n[3.조    회]\n[4.회원탈퇴]\n[0.로그아웃]");
			System.out.print("...메뉴를 선택하세요 : ");
			int sel = scan.nextInt();
			if(sel == 1) {			am.createAcc(identifier);
			} else if (sel == 2) {	
				am.printAcc(identifier);
				System.out.print("회원님이 지우고싶은 계좌번호를 입력하세요: ");
				String accNum = scan.next();
				if(UserManager.getInstance().getCheckAcc(identifier, accNum) != true) {
					System.out.println("올바르지 못한 계좌번호입니다. ");
				} else 
					am.deleteAcc(identifier, accNum);
			} else if (sel == 3) {	am.printAcc(identifier);
			} else if (sel == 4) {
				if(um.deleteMember(identifier) == 1) {// 회원탈퇴성공
					System.out.printf("안녕히가세요. 회원탈퇴 완료!\n");
					break;
				}
			} else if (sel == 0) {
				System.out.println("[로그아웃]");
				break;
			}
		}
	}
	
	void join() {
		um.joinMember();		
	}
}
