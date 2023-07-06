import sys

# input
w, h = map(int, sys.stdin.readline().split())
num_slices = int(sys.stdin.readline())

row = [0,h]
col = [0,w]
for i in range(num_slices) :
    slice_type, num = map(int, sys.stdin.readline().split())
    if slice_type == 0 : # 가로 점선 자르기
        row.append(num)
    else : # 세로 점선 자르
        col.append(num)
row.sort()
col.sort()

# calculate
areas = []
for i in range(len(row)-1) :
    for j in range(len(col)-1) :
        area = (row[i+1]-row[i]) * (col[j+1]-col[j])
        areas.append(area)

print(max(areas))