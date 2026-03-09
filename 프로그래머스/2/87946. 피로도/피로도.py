def solution(k, dungeons):
    answer = 0;
    for i in range(len(dungeons)) :
        visited = [False] * len(dungeons)
        visited[i] = True
        if (k >= dungeons[i][0]) :
            count = dfs(dungeons, 1, visited, k - dungeons[i][1]);

        answer = max(answer, count)
    return answer

def dfs(arr, path, visited, rest) :
    max_path = path
    
    for i in range(len(arr)):
        if not visited[i] and rest >= arr[i][0]:
            visited[i] = True;
            result =  dfs(arr, path + 1, visited, rest - arr[i][1])
            
            max_path = max(max_path, result)
            
            visited[i] = False;
    return max_path