import sys

# 로프 입력
ropes = []
N = int(sys.stdin.readline().rstrip())
for i in range(N) :
    rope = int(sys.stdin.readline().rstrip())
    ropes.append(rope)

# 최대 중량 계산
weights = []
ropes.sort()

for i in range(N) :
    weights.append(ropes[i] * (N-i))
    
max_weight = max(weights)
print(max_weight)