import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
 
        Scanner sc = new Scanner(System.in);
 
 
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
 
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();
 
            // 2차원 배열 채우
            char[][] arr = new char[N][M];
 
            for (int i = 0; i < N; i++) {
                String str = sc.nextLine();
                for (int j = 0; j < M; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
//          System.out.println(Arrays.deepToString(arr));
 
//          int changeNum = 0;
 
            // 맨 윗줄은 무조건 W이여야 한다.
//          for (int i = 0; i < M; i++) {
//              if (arr[0][i] != 'W') {
//                  changeNum += 1;
//              }
//          }
//
//          // 맨 아줄은 무조건 R이여야 한다.
//          for (int i = 0; i < M; i++) {
//              if (arr[N - 1][i] != 'R') {
//                  changeNum += 1;
//              }
//          }
 
//          System.out.println("cahngeNum: " + changeNum);
 
            // N - 2가 1이면 무조건 파란색이어야 함
            // N - 2가 2이면 흰, 파 or 파, 빨 or 파파
            // N - 2가 3이면 파파파, 흰파파, 흰흰파, 파파빨, 파빨빨
            // N - 2가 4면 파파파파, 흰파파파, 흰흰파파, 흰흰흰파,
            // 경우의 수가 넘 많다...
            // -> 파란색을 1개부터 N -2개까지 칠하는 경우의 수
            // 칠해야 하는 행은 1행부터 N-2행까지
            // for 문 돌려보자
 
            // 행의 색깔 경우의 수
             
            int minChangeNum = 100000;
             
             
 
            for (int n = 1; n < N - 1; n++) {
                // n는 파란색을 칠해야 하는 개
                for (int r = 1; r < N - n; r++) {
//                  int totalChangeNum = changeNum;
                    int changeNum = 0;
                     
                    char[] rowColor = new char[N];
                    // 이거는 칠해야 하는 행
                    for (int k = 0; k < n; k++) {
                        rowColor[r + k] = 'B';
                    }
//                  System.out.println(Arrays.toString(rowColor));
                     
                    //B의 위는 W로 채우고 B의 아래는 R로 채우기
                    boolean isBlue = false;
                    for (int i = 0; i < N; i++) {
                        if (rowColor[i] == 'B') {
                            isBlue = true;
                        } else {
                            //B 이전이니 흰색으로 채우
                            if (isBlue == false) {
                                rowColor[i] = 'W';
                                         
                            } else if (isBlue == true){
                                rowColor[i] = 'R';
                            }
                        }
                    }//가능한 색조합이 모두 나왔다!
//                  System.out.println(Arrays.toString(rowColor));
                     
                    //여기서 또 for문을 돌리면서 교체해야 하는 색을 구하기 
                    for (int a = 0; a < N; a++) {
                        for (int b = 0; b < M; b++) {
                            if (arr[a][b] != rowColor[a]) {
                                changeNum += 1;
                            }
                        }
                    }
                    if (changeNum < minChangeNum) {
                        minChangeNum = changeNum;
                    }
                     
                }
            }
             
            System.out.println("#" + tc + " " +minChangeNum);
             
 
        }
 
    }
 
}