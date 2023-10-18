-- 동물의 생물 종, 이름, 성별 및 중성화 여부 조회
-- 아이디 순으로 조회
-- 이름이 없는 동물의 이름은 No name 표시
SELECT ANIMAL_TYPE, IFNULL(NAME,'No name') 'NAME', SEX_UPON_INTAKE
FROM ANIMAL_INS;
