import sys

# area map
array = [[0]*101 for i in range(101)]

# calculate
for i in range(4) :
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    for j in range(x1,x2) :
        for k in range(y1, y2) :
            array[j][k] = 1 # area check

area = 0
for i in range(101) :
    area += sum(array[i])

# output
print(area)