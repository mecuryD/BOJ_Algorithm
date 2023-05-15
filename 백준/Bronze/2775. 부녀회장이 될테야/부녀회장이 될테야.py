import sys
T = int(sys.stdin.readline())
res = [0]*T
for t in range(T) :
    k = int(sys.stdin.readline())
    n = int(sys.stdin.readline())
    # dp-table
    table = [[0]*15]*(k+1)
    # bottom-up dp
    table[0] = list(range(15))
    table[1][0] = table[0][0]
    for i in range(1,k+1) :
        for j in range(1, n+1) :
            table[i][j] = table[i-1][j] + table[i][j-1]
    res[t] = table[k][n]

# result
for t in range(T) :
    print(res[t])