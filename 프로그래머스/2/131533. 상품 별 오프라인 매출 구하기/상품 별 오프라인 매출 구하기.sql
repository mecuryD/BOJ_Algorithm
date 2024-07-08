-- PRODUCT : 의류 쇼핑몰에서 판매중인 상품들의 상품 정보
-- OFFLINE_SALE : 오프라인 상품 판매 정보
-- 상품코드 별 매출액(판매가 * 판매량) 합계를 출력
-- 매출액 내림차순, 상품코드 오름차순


SELECT PRODUCT_CODE,
       SUM(SALES_AMOUNT)*PRICE SALES
FROM OFFLINE_SALE S LEFT JOIN PRODUCT P ON S.PRODUCT_ID = P.PRODUCT_ID
GROUP BY PRODUCT_CODE
ORDER BY SALES DESC, PRODUCT_CODE