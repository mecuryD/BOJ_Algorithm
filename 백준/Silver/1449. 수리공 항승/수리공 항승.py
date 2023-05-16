import sys
n,l = map(int,sys.stdin.readline().split())
pos = list(map(int, sys.stdin.readline().rstrip().split()))
pos.sort()

# greedy
count = 1
tape_start = pos[0] - 0.5
tape_end = pos[0] - 0.5 + l

for i in range(1,n) :
    if tape_end >= pos[i]+0.5 :
        continue
    else :
        tape_start = pos[i] - 0.5 # new tape
    
    tape_end = tape_start + l
    count += 1
print(count)