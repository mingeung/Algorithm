import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int maxNum = Integer.MIN_VALUE;
	static int minNum = Integer.MAX_VALUE;;

	static String[] operations = new String[] { "+", "-", "*", "/" };

	public static void main(String[] args) throws FileNotFoundException {

		// 아래 두 줄 순서 바뀌면 안됨
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

//		System.out.println("arr:" + Arrays.toString(arr));

		int[] operationsCount = new int[4];

		for (int i = 0; i < 4; i++) {
			operationsCount[i] = sc.nextInt();
		}

//		System.out.println("operationsCount: " + Arrays.toString(operationsCount));

		int sum = arr[0];
		int nextNumIdx = 1;

		opertaion(sum, nextNumIdx, operationsCount, N, arr);

		System.out.println(maxNum);
		System.out.println(minNum);
	}

	private static void opertaion(int sum, int idx, int[] operationsCount, int N, int[] arr) {

		// 다음 숫자가 인덱스의 마지막을 넘 어섰다면
		if (idx == N) {
			// 최댓값보다 크면
			if (sum > maxNum) {
				maxNum = sum;
			}
			// 최솟값보다 작으면
			if (sum < minNum) {
				minNum = sum;
			}
			// 여기까지는 되는데 전역 변수에 반영이 안 된 것 같다.
			return;
		}

		// 남은 연산자가 있는 것들 탐색
		String op = "";
		for (int i = 0; i < 4; i++) {
			if (operationsCount[i] > 0) {
				operationsCount[i]--;
				op = operations[i];

//				System.out.println("op:" + op);

				int nextSum = sum;

				// 연산 수행
				switch (op) {
				case "+":
					nextSum += arr[idx];
					break;
				case "-":
					nextSum -= arr[idx];
					break;
				case "*":
					nextSum *= arr[idx];
					break;

				case "/":
					// C++14 나눗셈 규칙 구현
					if (nextSum < 0) {
						nextSum = -(-nextSum / arr[idx]);
					} else {
						nextSum /= arr[idx];
					}

					break;

				}
				opertaion(nextSum, idx + 1, operationsCount, N, arr);
				operationsCount[i]++;
			}
		}
	}
}
