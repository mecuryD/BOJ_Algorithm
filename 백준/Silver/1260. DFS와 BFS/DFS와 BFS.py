import sys
from collections import deque

n, m, v = map(int,sys.stdin.readline().split())

# graph 생성
graph = [[] for i in range(n+1)]
for i in range(m) :
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

# dfs 알고리즘
visited = [0] * (n+1)
def dfs(graph, s, visited) :
    # 현재 노드 방문 처리
    visited[s] = True
    print(s, end=' ')
    # 연결된 노드 방문
    graph[s].sort()
    for i in graph[s] :
        if not visited[i] :
            dfs(graph, i, visited)
dfs(graph, v, visited)
print('')

# bfs 알고리즘
visited = [0] * (n+1)
def bfs(graph, s, visited) :
    queue = deque()
    queue.append(s)
    while queue : # 모든 노드를 탐색할 때까지
        a = queue.popleft()
        if visited[a] : # 이미 방문한 노드면 리턴
            continue
        visited[a] = True
        print(a, end=' ')
        for i in graph[a] :
            queue.append(i)
        
bfs(graph, v, visited)
