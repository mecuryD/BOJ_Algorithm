import sys

# 입력
n = int(sys.stdin.readline().rstrip())
students = []
for i in range(n) :
    student = int(sys.stdin.readline().rstrip())
    students.append(student)

# 불만도 계산
real = list(range(1,n+1))
students.sort()
diff = [abs(x-y) for x,y in zip(real, students)]
score = sum(diff)
print(score)