import sys

# input
num_switch = int(sys.stdin.readline())
state_switch = list(map(int, sys.stdin.readline().split()))
num_student = int(sys.stdin.readline())

state_switch.insert(0,0)


# reverse
def reverse(array, num) :
    array[num] = 1 - array[num]

# calculate
for i in range(num_student) :
    gender, num = map(int, sys.stdin.readline().split())
    #성별에 따라 동작 구분
    if gender == 1 : # 남학생
        for j in range(1, 1 + num_switch//num) :
            reverse(state_switch,j*num)
    else : # 여학생
        reverse(state_switch, num)
        for j in range(1, min(num, num_switch-num+1)) : 
            if state_switch[num-j] == state_switch[num+j] :
                reverse(state_switch, num-j)
                reverse(state_switch, num+j)
            else : # 더 이상 대칭 구간이 없음
                break

for i in range(1, num_switch+1):
    print(state_switch[i], end = " ")
    if i % 20 == 0 :
        print()