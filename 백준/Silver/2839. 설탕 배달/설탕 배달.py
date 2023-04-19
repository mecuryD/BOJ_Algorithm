# 설탕 무게 입력
sugar = int(input())

# 최소 봉지 수 계산
count = -1
counts = [] 


for i in range((sugar//5)+1) : 
    left = sugar - 5 * i
    if left % 3 == 0 :
        counts.append(i + left//3) # 나누어 떨어지는 경우만 추가

if counts : # 나누어 떨어지는 경우가 1개라도 있으면
    count = min(counts)
    
print(count)