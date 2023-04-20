# 로프 입력
ropes = []
N = int(input())
for i in range(N) :
    ropes.append(int(input()))

# 최대 중량 계산
weights = []
ropes.sort()

while ropes :
    weights.append(ropes[0]*len(ropes))
    del ropes[0]
    
max_weight = max(weights)
print(max_weight)