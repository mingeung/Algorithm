
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int callNum;

	public static void main(String[] args)   {

		Scanner sc = new Scanner(System.in);

		int[][] arr = new int[5][5];
		boolean[][] visited = new boolean[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int[] bingoNum = new int[25];

		// 부르는 숫자 배열에 담기
		for (int i = 0; i < 25; i++) {

			callNum = sc.nextInt();
			bingoNum[i] = callNum;

		}
//		System.out.println(Arrays.toString(bingoNum));

		int thisNum = 0;

		for (int i = 0; i < 25; i++) {
			// 이번에 확인할 숫자
			thisNum = bingoNum[i];

			
		
			// 빙고칸에 있는지 찾기
			for (int r = 0; r < 5; r++) {
				for (int c = 0; c < 5; c++) {
					if (arr[r][c] == thisNum) {
						visited[r][c] = true;
					}
				}
			}			// 빙고가 있는지 확인
			int line = checkBingo(visited);
			// 빙고개수가 3개가 되면 끝!
			if (line >= 3) {
				// i + 1 번째 숫자를 불렀을 때 정답
				System.out.println(i + 1);
				break;
   			}
}
	}

	private static int checkBingo(boolean[][] visited) {
		boolean isLine;
		int line = 0;
		// 가로, 세로빙고가 있는지 확인
		for (int k = 0; k < 5; k++) {
			// 가로빙고가 있는지 확인
			isLine = true;
			for (int r = 0; r < 5; r++) {
				if (visited[k][r] == false) {
					isLine = false;
				}
			}
			if (isLine) {
				line += 1;
			}
			// 세로빙고가 있는지 확인
			isLine = true;
			for (int r = 0; r < 5; r++) {
				if (visited[r][k] == false) {
					isLine = false;
				}
			}
			if (isLine) {
				line += 1;
			}

		}
		// 대각선 빙고기 있는지 확인
		isLine = true;

		for (int r = 0; r < 5; r++) {
			if (visited[r][r] == false) {
				isLine = false;
			}
		}
		if (isLine) {
			line += 1;
		}
		isLine = true;
		for (int r = 0; r < 5; r++) {
			if (visited[r][5 - r - 1] == false) {
				isLine = false;
			}
		}
		if (isLine) {
			line += 1;
		}

		return line;

	}
}