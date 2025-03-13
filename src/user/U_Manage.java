package user;

import java.util.Scanner;

import movie.GUI;

public class U_Manage {
	U_DTO udto = new U_DTO();
	GUI gui = new GUI();
	public U_Manage(){
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		
		while(flag) {
			int a = in.nextInt();
			in.nextLine();
			
			switch(a) {
			case 1:
				join();
				break;
			case 2:
				//login();
				break;
			case 3:
				guest();
				break;
			case 4:
				resign();
				break;
			}
		}
	}

	public void resign() {
		
		
	}

	public void guest() {
		
		
	}

	public void login(String id, String pw) {
		
		
		
		
	}

	public void join() {
		
	}
}
