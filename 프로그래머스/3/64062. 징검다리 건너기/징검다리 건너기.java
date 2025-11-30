import java.util.*;

//이분탐색의 while문은 break를 넣지 말고 left <= right 조건이 만족할 때까지 지속한다.
class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = Arrays.stream(stones).max().getAsInt();
        while (left <= right) { 
            int mid = (left + right) / 2;
            
            if(canCross(mid, stones, k) == -1) {
                right = mid -1;
            } else  {
                left = mid + 1;
                
            } 
        }
        //return 값이 왜 rigth가 되는지 이해할 것
        return right;
    }
    
    public int canCross(int mid, int[] stones, int k) {
        int currStoneChain = 0;
        
        for (int i = 0; i < stones.length; i++) {
            //stones[i] -= mid; 배열의 값을 직접 변경하면 안된다. 
            //다음 탐색 때 영향을 주기 때문에 
            if (stones[i] - mid < 0) {
               currStoneChain += 1;
                if (currStoneChain >= k) {
                    return -1;
                }
            } else {
                currStoneChain = 0;
            }
        }
        return 1;
    }
}
/**
Soluion1
1. while문을 돌리기
2. 배열을 돌면서 하나씩 -1을 하기
3. 0을 만나면 그 다음에 0이 아닌 수를 찾기. 
4. 다음 0이 아닌 수와 현재의 차이가 k보다 작거나 같은지 확인한다.
=> 배열의 크기가 크고 무제한이기 때문에 시간초과가 터질 것

Solution2
* 최대로 연속된 수 M을 둔다. 
* 연속된 수를 어떻게 찾을 것인가? 
* boolean 배열? (x) 
* 우선순위 큐? 
 
1. 배열에서 최솟값을 찾는다. 최솟값 수만큼의 친구 수는 징검다리를 건널 수 있다. 전체 배열을 마이너스 해준다. 
2. 두 번째 최솟값을 찾는다. 
3. 세 번째 최솟값을 찾는다. 
**/

//[2, 4, 5, 3, 2, 1, 4, 2, 5, 1]
//니니즈 친구가 4명인 경우 
//[-2, 0, 1, -1, -2, -3, 0, -2, 1, -3]
//니니즈 친구가 3명인 경우
//[-1, 1, 2, 0, -1, -2, 1, -1, 2, -2]