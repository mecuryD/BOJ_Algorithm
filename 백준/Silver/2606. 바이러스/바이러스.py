# 2606번 바이러스
import sys

# input
n = int(sys.stdin.readline())
r = int(sys.stdin.readline())

visited = [0] * (n + 1)
coms = [[] for _ in range(n + 1)]

for i in range(r):  # graph input
    a, b = map(int, sys.stdin.readline().split())
    coms[a].append(b)
    coms[b].append(a)

# dfs
num = -1
arr = [1]
while (arr):
    # pop
    data = arr.pop()
    
    # 이미 방문한 곳인지 체크
    if visited[data] == 1 :
        continue  
    visited[data] = 1
    num = num + 1
    
    # search
    for i in range(len(coms[data])):
        idx = coms[data][i]
        arr.append(idx)
print(num)
