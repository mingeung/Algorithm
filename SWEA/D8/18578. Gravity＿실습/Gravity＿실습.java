

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//첫번째 케이스롤 생각하기
//7개의 상자가 있음
//7개의 열을 만들기 
//[5, 5, 4, 4, 3, 3, 2]

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int width = sc.nextInt();
			int[] height = new int[width];

			int maxHeight = 0;
			for (int i = 0; i < width; i++) {
				height[i] = sc.nextInt();
				if (height[i] > maxHeight) {
					maxHeight = height[i];
				}
			}

//			System.out.println(Arrays.toString(height));

			// 가장 높은 수 찾기
//			System.out.println(maxHeight);

			// 높이 2차원 배열 만들기 -> 기본으로 0이 채워져 있음
			int[][] arr = new int[maxHeight][width];

			// 블럭이 있는 부분에 1로 채우기. 여긴 열이 고정되어 있어야 하네.
			// ->지금 배열은 바닥이 위에 있다. -> 노상관 왼쪽 블럭수를 세면 됨
			for (int i = 0; i < width; i++) {
//				System.out.println("i: " + i);
				for (int j = 0; j < height[i]; j++) {
//					System.out.println("j: " + j);

					arr[j][i] = 1;
//					System.out.println("=======================");

				}
			}

//			System.out.println(Arrays.deepToString(arr));
			//

			int maxChange = 0;
			for (int i = 0; i < maxHeight; i++) {
				for (int j = 0; j < width; j++) {
					if (arr[i][j] == 1) {
						int count = 0;
						// 얘의 오른쪽에 블럭이 몇 개 있는지 세기
						for (int x = j + 1; x < width; x++) {
							if (arr[i][x] == 1) {
								count += 1;
							}
						}
//						System.out.println("count:" + count);
						
						int change = (width - (j + 1)) - count;
//						System.out.println("change:" + change);
						
						if (change > maxChange) {
							maxChange = change;
						}
					}
					else {
						continue;
					}
//					System.out.println("어딘교: " + "i :" + i +", j : " + j +" maxChange : " + maxChange);
				}
			}

			System.out.println("#" + tc + " " + maxChange);
		}

	}

}
