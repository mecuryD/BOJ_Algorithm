# 잔돈 계산
money = 1000 - int(input())

count = 0
coins = [500, 100, 50, 10, 5, 1]

# 최소 잔돈 개수 계산
for i in range(len(coins)) :
    count += money // coins[i]
    money = money % coins[i]

print(count)