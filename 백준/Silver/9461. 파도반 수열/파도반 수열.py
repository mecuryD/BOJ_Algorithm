import sys
T = int(sys.stdin.readline())

# dp-table
length = [1,1,1,2,2,]

# bottom-up dp
for i in range(5,100) :
    length.append(length[i-1]+length[i-5])

# out
for i in range(T) :
    print(length[int(sys.stdin.readline())-1])