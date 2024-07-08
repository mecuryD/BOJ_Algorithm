-- 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회
-- 보호 시작일이 빠른 순으로 조회
SELECT AI.ANIMAL_ID, AI.NAME
FROM ANIMAL_INS AI
INNER JOIN ANIMAL_OUTS AO
ON AI.ANIMAL_ID=AO.ANIMAL_ID
WHERE AI.DATETIME > AO.DATETIME
ORDER BY AI.DATETIME ASC