# 요리시간 T 입력
t = int(input())


count = [0,0,0]
buttons = [300, 60, 10]

# 최소 버튼 조작 방법 계산
for i in range(len(buttons)) :
    count[i] = t // buttons[i] # 조작 횟수
    t = t % buttons[i]

# T초를 맞출 수 없다면 음수 -1 출력
if not t == 0 :
    print('-1')
else :
    print(f'{count[0]} {count[1]} {count[2]}')