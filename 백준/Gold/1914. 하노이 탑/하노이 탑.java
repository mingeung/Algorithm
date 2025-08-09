
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
        BigInteger moves = BigInteger.TWO.pow(N).subtract(BigInteger.ONE);
        System.out.println(moves);

		
	    if (N <= 20) { // N이 20 이하일 때만 경로 출력
	        hanoi(N, 1, 3, 2);
	    }
	}
	private static void hanoi(int n,int start, int end,int via) {
		if(n==0) return;
		

		hanoi(n-1,start,via,end);
		System.out.println(start+" "+end);
		hanoi(n-1,via,end,start);
		
	}

}
