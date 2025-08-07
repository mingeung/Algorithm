

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		int[] numArr = new int[T];
		int[] strikeArr = new int[T];
		int[] ballArr = new int[T];

		for (int i = 0; i < T; i++) {
			numArr[i] = sc.nextInt();
			strikeArr[i] = sc.nextInt();
			ballArr[i] = sc.nextInt();

		}

		int totalCount = 0;
		for (int i = 123; i <= 987; i++) {
			int count = 0;

			int num2 = i;
			int hundrend2 = i / 100;
			int ten2 = (num2 - (hundrend2 * 100)) / 10;
			int one2 = num2 % 10;

			for (int j = 0; j < T; j++) {

				int tryNum = numArr[j];
				int strikeCount = strikeArr[j];
				int ballCount = ballArr[j];

//				System.out.println("tryNum: " + tryNum);
//				System.out.println("strikeCount: " + strikeCount);
//				System.out.println("ballCount: " + ballCount);

				int num = tryNum;

				int hundrend = num / 100;
				int ten = (num - (hundrend * 100)) / 10;
				int one = num % 10;

				// 각 자리가 같거나 0이 포함되어 있으면 pass
				if (hundrend2 == ten2 || ten2 == one2 || hundrend2 == one2 || hundrend2 == 0 || ten2 == 0
						|| one2 == 0) {
					continue;
				}

				int strike2 = 0;
				int ball2 = 0;

				// 스트라이크 개수 세기
				if (hundrend == hundrend2) {
					strike2 += 1;
				}
				if (ten == ten2) {
					strike2 += 1;
				}
				if (one == one2) {
					strike2 += 1;
				}

				// 볼 개수 세기
				if (hundrend == ten2 || hundrend == one2) {
					ball2 += 1;
				}
				if (ten == hundrend2 || ten == one2) {
					ball2 += 1;
				}
				if (one == hundrend2 || one == ten2) {
					ball2 += 1;
				}
				// 원래 것이랑 같은지 확인
				if (strikeCount == strike2 && ballCount == ball2) {
					count += 1;
				}

			}
			if (count == T) {
				totalCount += 1;
			}
		}
		System.out.println(totalCount);
	}
}