class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int mod = 1_000_000_007;
        
        //오른쪽과 아래쪽으로만 움직일 수 있다.
        //집까지 갈 수 있는 경로의 수를 저장하는 dp배열 만들기
        int[][] dp = new int[n+1][m+1];
        //우물이 있는 곳은 경로를 계산할 수 없으므로 -1로 채우기
        for (int i = 0 ; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            
            dp[y][x] = -1; //인덱스 순서 주의! [x][y]로 하면 틀림
        }
        //첫번째 경로 1처리
        dp[1][1] = 1;
        
        //현재까지의 경로의 수는, 왼쪽 경로 수의 + 1, 위의 경로 수의 +1 이다.
        for (int i = 1;  i < n +1; i++) {
            for (int j = 1; j < m + 1; j++) {
                //시작지점이라면 넘어가기
                if (i == 1 && j == 1) continue;
                //우물을 만났다면 넘어가기
                if (dp[i][j] == -1) continue;
            
                int down_path = dp[i - 1][j];
                int right_path = dp[i][j-1];
                //우물을 만났을 때의 처리방식
                if (down_path == -1) {
                    down_path = 0;
                } else if (right_path == -1){
                    right_path = 0;
                }
                
                dp[i][j] = (down_path + right_path) % mod;
            }
        }
        answer = dp[n][m];
        
        
        return answer;
    }
}