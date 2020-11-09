package myATM_v2;

import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	
	int identifier;
	UserManager um = UserManager.getInstance();
	
	void play() {
		um.printAllUser();
		
		while(true) {
			System.out.println("\n[ATM V2 동작중...]");
			System.out.println("[1. 회원가입]\n[2. 로그인]\n[0. 종료]");
			System.out.print("...메뉴를 선택하세요 : ");
			int sel = scan.nextInt();
			if(sel == 1) {			join();
			} else if (sel == 2) {	login();
			} else if (sel == 0) {	System.out.println("[ATM V2 종료]");
									break;
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
			System.out.printf("\n[%s님이 로그인중]", um.userList[identifier].id);
			System.out.println("[1.계좌생성]\n[2.계좌삭제]\n[3.조    회]\n[4.회원탈퇴]\n[0.로그아웃]");
			System.out.print("...메뉴를 선택하세요 : ");
			int sel = scan.nextInt();
			if(sel == 1) {			
			} else if (sel == 2) {	
			} else if (sel == 3) {
			} else if (sel == 4) {
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
