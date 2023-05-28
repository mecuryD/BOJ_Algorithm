import sys

# 입력
n = int(sys.stdin.readline())
array = []
for i in range(n) :
    array.append(list(map(int, list(sys.stdin.readline().rstrip()))))

# dfs 알고리즘
def dfs(array, row, col,count) :
    # 지도 범위 벗어나면 무시
    if (row<0)or(row>=n)or(col<0)or(col>=n) :
        return count
    # 이미 방문한 노드라면 리턴
    if array[row][col] == 0 :
        return count
    # 방문 체크  
    array[row][col] = 0
    count += 1
    # 상하좌우로 연결된 노드 방문
    count=dfs(array, row-1, col, count)
    count=dfs(array, row+1, col, count)
    count=dfs(array, row, col-1, count)
    count=dfs(array, row, col+1, count)
    return count
      
# 지도 한 칸씩 탐색
apts = []
for i in range(n) :
    for j in range(n) :
        if array[i][j] == 0 : # 집이 없는 곳이거나, 이미 탐색한 곳
            continue
        else :
            count = 0
            apts.append(dfs(array,i,j,count))
            
apts.sort()
print(len(apts))
for i in range(len(apts)) :
    print(apts[i])