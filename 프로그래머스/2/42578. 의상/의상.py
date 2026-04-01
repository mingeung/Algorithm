#경우의 수로 푸는 문제
from collections import Counter

def solution(clothes):
    count = Counter([kind for item, kind in clothes])

    answer = 1
    
    for value in count.values():
        answer *= (value + 1)
    answer -= 1
    
    return answer