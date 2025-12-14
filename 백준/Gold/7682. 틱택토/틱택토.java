import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		char[][] map;
		
		int order = 1;
		
		
		while(!str.equals("end")) {
//			System.out.println("순서: "+ order);
			order += 1;
			int xCount = 0;
			int oCount = 0;
			boolean valid = true;
		
			
			//map 배열에 저
			 map = new char[3][3];
			for (int i = 0; i  < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = str.charAt(i * 3 +j);
					//카운트 확인
					if (map[i][j] == 'X') {
						xCount += 1;
					} else if(map[i][j] == 'O') {
						oCount += 1;
					}
				}
			}
			
			//1. 기본 턴 수 체크 
			if (oCount > xCount ) {
//				System.out.println("가능성1");
				valid = false;
			}
			
			//승리여부 체크
			boolean xWin = checkWin('X', map);
			boolean oWin = checkWin('O', map);
			
			//둘 다 승리하면 invalid
			if (xWin && oWin) {
//				System.out.println("가능성2");
				valid = false;
			}
			if (xWin && !(xCount == oCount +1)) {
//				System.out.println("가능성3");
				valid = false;
			}
			if (oWin && !(xCount == oCount)) {
//				System.out.println("가능성4");
				valid = false;
			}
			
			if (!oWin && !xWin && (xCount + oCount != 9)) {
//				System.out.println("가능성5");
				valid = false;
			}
			
			if (valid ) {
				System.out.println("valid");
			} else {
				System.out.println("invalid");
			}
			str = sc.nextLine();
		}
		
		
		
		
	}

	private static boolean checkWin(char c, char[][] map) {
		char target = c;
		boolean bingo = false;
		boolean flag = true;
		
		//가로 검사
		for (int i = 0; i < 3; i++) {
			flag = true;
			for (int j =0; j < 3; j++) {
				if (map[i][j] != target) {
					flag = false;
					continue;
				}
			}
			if (flag) {
				bingo = true;
			}
		}
		
		//세로 검사 
		for (int j = 0; j < 3; j++) {
			flag = true;
			for (int i =0; i < 3; i++) {
				if (map[i][j] != target) {
					flag = false;
					continue;
				}
			}
			if (flag) {
				bingo = true;
			}
		}
		
		//대각선 검사
		flag = true;
		for (int i =0; i < 3; i++) {
			if (map[i][i] != target) {
				flag = false;
			}
		}
		if (flag) {
			bingo = true;
		}
		
		//(2, 0), (1, 1), (0, 2)
		flag = true;
		for (int i =2; i >= 0; i--) {
			if (map[i][2- i] != target) {
				flag = false;
			}
		}
		if (flag) {
			bingo = true;
		}
		
			if (flag) {
				bingo = true;
		}
		return bingo;
	}
}


