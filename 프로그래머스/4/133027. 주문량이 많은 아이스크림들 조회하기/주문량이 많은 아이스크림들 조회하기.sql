-- (7월 아이스크림 총 주문량 + 상반기 아이스크림 총 주문량)이 큰 순서대로 상위 3개의 맛
SELECT SUB.FLAVOR
FROM (
        SELECT * FROM FIRST_HALF
        UNION ALL
        SELECT * FROM JULY
    ) SUB
GROUP BY SUB.FLAVOR
ORDER BY SUM(SUB.TOTAL_ORDER) DESC
LIMIT 3
