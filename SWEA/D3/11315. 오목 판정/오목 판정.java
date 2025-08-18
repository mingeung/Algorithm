
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution{
    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            char[][] board = new char[N][N];

            for (int i = 0; i < N; i++) {
                String str = sc.next();
                board[i] = str.toCharArray();
            }

            boolean bingo = false;

            outer:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 'o') {
                        // 4가지 방향 체크
                        if (checkDirection(board, N, i, j, 0, 1)) { // 가로
                            bingo = true;
                            break outer;
                        }
                        if (checkDirection(board, N, i, j, 1, 0)) { // 세로
                            bingo = true;
                            break outer;
                        }
                        if (checkDirection(board, N, i, j, 1, 1)) { // 우하 대각선
                            bingo = true;
                            break outer;
                        }
                        if (checkDirection(board, N, i, j, -1, 1)) { // 우상 대각선
                            bingo = true;
                            break outer;
                        }
                    }
                }
            }

            System.out.print("#" + tc + " ");
            System.out.println(bingo ? "YES" : "NO");
        }
    }

    // 특정 방향(dx, dy)으로 연속 5개 있는지 체크
    private static boolean checkDirection(char[][] board, int N, int x, int y, int dx, int dy) {
        for (int k = 0; k < 5; k++) {
            int nx = x + dx * k;
            int ny = y + dy * k;
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) return false;
            if (board[nx][ny] != 'o') return false;
        }
        return true;
    }
}
