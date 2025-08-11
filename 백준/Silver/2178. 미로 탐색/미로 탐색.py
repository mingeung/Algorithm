import sys
from collections import deque;


N, M = map(int, sys.stdin.readline().split())
#띄어쓰기 안 되 있는 건 strip()
arr = [list(map(int, sys.stdin.readline().strip())) for _ in range(N)]

d = [(0, -1), (0, 1), (1, 0), (-1, 0)]

def bfs(y, x) :
    q = deque()
    q.append((y, x))
    while q:
        y, x = q.popleft()
        for dy, dx in d:
            Y, X = y + dy, x + dx
            if (0 <= Y < N) and (0 <= X < M) and arr[Y][X] == 1 :
                q.append((Y, X))
                arr[Y][X] = arr[y][x] + 1


bfs(0, 0)
print(arr[N-1][M-1])