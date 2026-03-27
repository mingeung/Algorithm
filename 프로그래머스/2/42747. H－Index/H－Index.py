def solution(citations):
    #내림차순 정렬하는 법
    citations.sort(reverse=True)
    
    for i in range(len(citations)) :
        if (citations[i] < i + 1) :
            return i
    return(len(citations))