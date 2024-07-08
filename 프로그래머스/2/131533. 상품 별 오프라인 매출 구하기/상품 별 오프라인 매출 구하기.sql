-- PRODUCT : 의류 쇼핑몰에서 판매중인 상품들의 상품 정보
-- OFFLINE_SALE : 오프라인 상품 판매 정보
-- 상품코드 별 매출액(판매가 * 판매량) 합계를 출력
-- 매출액 내림차순, 상품코드 오름차순

SELECT SUB.PRODUCT_CODE, (SUB.PRICE * SUB.TOTAL) AS SALES
FROM (
        SELECT P.PRODUCT_CODE AS PRODUCT_CODE,
               P.PRICE AS PRICE,
               SUM(O.SALES_AMOUNT) AS TOTAL
        FROM PRODUCT P
        INNER JOIN OFFLINE_SALE O
        ON P.PRODUCT_ID=O.PRODUCT_ID
        GROUP BY P.PRODUCT_CODE
     ) AS SUB
ORDER BY SALES DESC, SUB.PRODUCT_CODE ASC