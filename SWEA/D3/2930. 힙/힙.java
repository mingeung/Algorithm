

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int[] heap;
	static int heapSize;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <=T; tc++) {
			int N = sc.nextInt();
			//힙을 생성
//			System.out.println("N: " + N);
			sc.nextLine();
			heap = new int[N + 1];
			heapSize = 0;
			
			System.out.print("#" + tc + " ");
			
			for (int i = 0; i < N; i++) {
				
				//자연수가 2개 ' 1 x'이면 연산 1을 수행(x를 자연수를 삽입)
				//입력값이 1개가(2) 주어진다면 현재 최대 힙의 루트 노드를 출력하고 해당 노드를 삭제한다.
				String[] input = sc.nextLine().split(" ");
//				System.out.println("input: " + Arrays.toString(input));
				if (input.length == 2) { // 삽입 연산 수행
					int insertNum = Integer.parseInt(input[1]) ;
					heapPush(insertNum);
					
				}
				else { //삭제연산 수행
					heapPop();
					
				}
//				System.out.println(Arrays.toString(heap));
				
				
			}
			System.out.println();
		}
		
	}
	
	private static void heapPush(int item) {
		//마지막자리에 아이템을 추가하기
		heap[++heapSize] = item;
		
		//올라갈 수 있으면 올라가기
		int ch = heapSize; //자식
		int p = ch / 2; //부모
		
		while (p > 0 && heap[p] < heap[ch]) {
			//swap
			int tmp = heap[p];
			heap[p] = heap[ch];
			heap[ch] = tmp;
			
			//올라가기 위해서 포인터 갱신
			ch = p;
			p = ch/ 2;
		}
		
		
		
	}

	private static Integer heapPop() {
		//힙이 공백이라면 넘어가
		if (heapSize == 0) {
			System.out.print(-1 + " ");
			return -1;
		}
		//삭제할 값 - 루트. 저장하기
		int item = heap[1];
		
		//루트 삭제하기 -> 가장 끝단을 부모로 올리기
		heap[1] = heap[heapSize--];
		
		int p = 1;
		int ch = p * 2; //왼쪽 자식
		if (ch + 1 <= heapSize && heap[ch] < heap[ch + 1]) {
			ch += 1; //오자로 다시 세팅 -> 큰 값과 바꿔야지 힙 속성이 유지가 되기 때문에 (부모 > 자식)
		}
		
		//내려가자!
		while(ch <= heapSize && heap[p] < heap[ch]) {
			//swap
			int tmp = heap[p];
			heap[p] = heap[ch];
			heap[ch] = tmp;
			
			//갱신하기
			p = ch; //일단 왼쪽 자식으로 세팅
			ch = p * 2;
			if (ch + 1 <= heapSize && heap[ch] < heap[ch + 1]) {
				ch += 1; 
			}
		}
		System.out.print (item + " ");
		return item;
		
	}



}
