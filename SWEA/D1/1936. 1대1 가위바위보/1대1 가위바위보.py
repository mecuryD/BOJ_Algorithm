ina, inb = map(int, input().split())
result = 'A'
 
if (ina==1&inb==2)or(ina==2&inb==3)or(ina==3&ina==1) :
    result = 'B'
print(result)