package myATM_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	private FileManager () {}
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
	
	String filename = "atm2.txt";
	String data = "";
	UserManager um = UserManager.getInstance();
	
	File f = null;
	FileWriter fw = null;
	FileReader fr = null;
	BufferedReader br = null;
	
	void setData() {
		// 현재 가진 정보를 data로 만든다 
		data += (um.userCnt + "\n");
		for (int i = 0; i < um.userCnt; i++) {
			User currUsr = um.userList[i];
			data += (currUsr.id + "," + currUsr.pw + "," + currUsr.accCnt + ",");
			for (int j = 0; j < currUsr.accCnt; j++) {
				data += (currUsr.accs[j].accNumber + "/" + currUsr.accs[j].money);
				if(j != currUsr.accCnt-1)
					data += ",";
			}
			data += "\n";			
		}
	}
	
	void save() {
		this.setData();
		try {
			fw = new FileWriter(filename);
			
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void load() {
		String temp = "";
		String data = "";
		f = new File(filename);
		if(f.exists()) {
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				
				temp = br.readLine();
				while(temp != null) {
					data += (temp + "\n");
					temp = br.readLine();
				}
				
				
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// 읽은 data를 넣어주기
			String[] splitData = data.split("\n");
			um.userCnt = Integer.parseInt(splitData[0]);
			um.userList = new User[um.userCnt];
			
			for (int i = 0; i < um.userCnt; i++) {
				String[] currUsrData = splitData[i+1].split(",");
				um.userList[i] = new User(currUsrData[0], currUsrData[1], Integer.parseInt(currUsrData[2]));
				
				if(um.userList[i].accCnt > 0) {
					um.userList[i].accs = new Account[um.userList[i].accCnt];
					for (int j = 0; j < um.userList[i].accCnt; j++) {
						um.userList[i].accs[j] = new Account(currUsrData[3+j].split("/")[0], Integer.parseInt(currUsrData[3+j].split("/")[1]));
					}
				}
			}
			
		} else {
			um.setDummy();
			setData();
			save();
		}
	}
}
