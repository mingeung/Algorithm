import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static int M;
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        // 이건 플로이드 워셜로 풀어야겠다.
        // 모든 정점과의 최단경로를 구해야하니까.
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine()); // 학생 수 1번부터 시작
            M = Integer.parseInt(br.readLine()); // M번 반복
 
            dist = new int[N + 1][N + 1];
 
            // dist의 배열 초기화
            // 자기자신은 0으로 나머지는 INF로 두기
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = INF;
                    }
                }
            }
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                dist[a][b] = 1;
 
            }
 
            // 그리고 3중 for문 돌리기
            // 경유지
            for (int k = 1; k < N + 1; k++) {
                for (int from = 1; from < N + 1; from++) {
                    // 출발지
                    if (dist[from][k] == INF)
                        continue;
                    for (int to = 1; to < N + 1; to++) {
                        if (dist[k][to] == INF)
                            continue;
 
                        // 최소거리 갱신
                        dist[from][to] = Math.min(dist[from][to], dist[from][k] + dist[k][to]);
                    }
                }
            }
 
            // 만약 무한대가 없으면 걔는 good
            int ans = 0;
 
            for (int i = 1; i < N + 1; i++) {
                int target = i;
                int count = 0;
 
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) {
                        continue;
                    }
                    // target이 from이 될 때
                    if (dist[target][j] != INF) {
                        count += 1;
                    }
 
                    // target이 to가 될 때
                    if (dist[j][target] != INF) {
                        count += 1;
                    }
 
                }
                if (count == N - 1) {
                    ans += 1;
                }
            }
 
            System.out.println("#" + tc + " " + ans);
 
        }
    }
 
}