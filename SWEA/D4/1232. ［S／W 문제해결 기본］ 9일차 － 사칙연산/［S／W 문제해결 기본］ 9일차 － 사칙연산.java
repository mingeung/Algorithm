import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static int[] left;
	static int[] right;
	static String[] data; // 일단 정수와 연산자 둘 다 String으로 넣기

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(sc.nextLine()); // 정점의 개수민;

			// 왼쪽 자식, 오른쪽 자식, 자신 노드의 데이터,부모 노드 배열 생성
			left = new int[N + 1];
			right = new int[N + 1];
			data = new String[N + 1]; // 여기에 연산자가 들어갈 수도 있고, 숫자가 들어갈 수도 있는 것 같은데

			// 정점이 정수면 : 정점 번호, 양의 정수
			// 정점이 연산자면 : 정점 번호, 연산자, 왼자, 오자
			// 루트 정점의 번호는 항상 1

			for (int i = 0; i < N; i++) {
				String[] tokens = sc.nextLine().split(" ");
				int node = Integer.parseInt(tokens[0]);

				if (tokens.length == 2) { // 정점이 정수일 때 : 정점 번호, 양의 정수
					data[node] = tokens[1]; // 부모자식은 넣지 않아도 괜찮나?
				} else if (tokens.length == 4) {// 정점이 연산자일 때 : 정점 번호, 연산자, 왼자, 오자
					data[node] = tokens[1];
					
					left[node] = Integer.parseInt(tokens[2]);
					right[node] = Integer.parseInt(tokens[3]);
				}
			}
			
			double result = calculate(1); //루트부터 계산
			System.out.println("#" + tc + " "+ (int)result);


		}

	}

	//재귀적으로 트리 계산
	//처음에는 중위호출을 하고 문자열 -> 정수형으로 바꿔서 계산을 했는데 그러면 연산자 우선순위 처리가 제대로 되지 않는다.
	private static double calculate(int node) {
		//현재노드가 리프 노드라면 (왼쪽과 오른쪽 자식이 모두 없다.)
		if (left[node] == 0 & right[node] == 0) { 
			return Double.parseDouble(data[node]);
		}

		//자식노드 값 계산
		double leftVal = calculate(left[node]);
		double rightVal = calculate(right[node]);
		
		//연산자에 따라 계산
		switch(data[node]) {
		case "+" : return leftVal + rightVal;
		case "-" : return leftVal - rightVal;
		case "*" : return leftVal * rightVal;
		case "/" : return leftVal / rightVal;
		}
		
		return 0;
	}
	}


