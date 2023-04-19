counts = []
i = 0
while True :
    # 입력
    l, p, v = map(int, input().split()) 
    
    if l+p+v == 0 :
        break;
    i += 1
    
    # 캠핑장 사용 가능 일수 계산    
    count = 0
    while v > 0 :
        if v >= p :
            count += l
        else :
            count += min(v, l)
        v -= p

    print(f'Case {i}: {count}')