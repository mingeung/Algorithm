import java.util.*; //import 써주기

class Node implements Comparable<Node>{
    int to;
    int cost;
    
    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}


class Solution {
    static final int INF = Integer.MAX_VALUE; //이것도 체크
    static List<List<Node>> graph;
    static int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i ++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }
        
        //s -> 모든 지점의 최단거리 
        //a -> 모든지점
        //b -> 모든 지점
        int[] dist_s = dijstra(s);
        int[] dist_a = dijstra(a);
        int[] dist_b = dijstra(b);
        
        int min_fare = INF;
        for (int k = 1; k <= n ; k ++) {
            min_fare = Math.min(min_fare, dist_s[k] + dist_a[k] + dist_b[k]);
        }

        return min_fare;
    }
    
    //start에서 모든 지점까지의 최소거리
public int[] dijstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    int[] dist = new int[N + 1];
    Arrays.fill(dist, INF); //이것도 확인 Array.를 써야 한다
    dist[start] = 0;
    pq.offer(new Node(start, 0));
    
    while(!pq.isEmpty()) {
        Node curr = pq.poll();
        if (curr.cost > dist[curr.to]) continue;
        
        for (Node next : graph.get(curr.to)) {
            int new_cost = curr.cost + next.cost;
            if (new_cost < dist[next.to]) { //조건을 걸어서 만족할 경우에만 큐에 넣어야 함
                dist[next.to] = new_cost;
                pq.offer(new Node(next.to, new_cost));
            }
            

        }
    }
    return (dist);
}


    
}