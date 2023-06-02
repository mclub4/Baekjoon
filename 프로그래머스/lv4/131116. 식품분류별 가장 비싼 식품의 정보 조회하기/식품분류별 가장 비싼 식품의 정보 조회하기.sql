SELECT CATEGORY, PRICE MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT
WHERE PRICE IN (
                    SELECT MAX(PRICE)
                    FROM FOOD_PRODUCT
                    GROUP BY CATEGORY
                )
    AND CATEGORY IN ('과자', '식용유', '김치', '국')
ORDER BY MAX_PRICE DESC