# 문서 및 단어 입력
doc = input()
word = input()

len_doc = len(doc)
len_word = len(word)

# 단어 반복 횟수 계산
i = 0 
count = 0 

while i < len_doc :
    if doc[i:i+len_word] == word :
        count += 1
        i += len_word
    else :
        i += 1

print(count)