# 입력
N = int(input())
A = list(map(int,input().split()))
B = list(map(int, input().split()))

# A 재배열
A.sort()
B.sort(reverse=True)

# S 계산
S = [A[i]*B[i] for i in range(N)]
S = sum(S)

print(S)