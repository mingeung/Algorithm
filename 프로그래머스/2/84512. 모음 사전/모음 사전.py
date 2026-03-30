#dfs이다!
def solution(word):
    arr = ['A', 'E', 'I', 'O', 'U' ]
    count = 0

    def dfs (word,arr, current) :
        nonlocal count
        
        if current != "" :
            count += 1
            if (current == word) :
                return count
        if len(current) >= len(arr) : #파이썬 문자열 길이 len()
            return 
        for letter in arr :
            new_count = dfs(word, arr, current + letter)
            if (new_count) :
                return new_count

    answer = dfs(word, arr, "")
    return answer
    
    
    