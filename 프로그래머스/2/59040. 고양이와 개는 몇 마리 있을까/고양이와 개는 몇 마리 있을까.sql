-- 동물 보호소에 들어온 동물 중, 고양이와 개를 각각 몇 마리인지 조회
SELECT ANIMAL_TYPE, COUNT(*) AS 'count'
FROM ANIMAL_INS
WHERE LOWER(ANIMAL_TYPE) IN ('cat', 'dog')
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE ASC