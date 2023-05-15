import sys
n = int(sys.stdin.readline())

# dp-table
table = [0] * 91
# bottom-up dp
table[1] = 1
for i in range(2, n+1) :
    table[i] = table[i-1] + table[i-2]
print(table[n])