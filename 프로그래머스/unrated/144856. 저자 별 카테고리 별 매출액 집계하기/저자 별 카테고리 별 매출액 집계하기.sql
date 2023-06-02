-- 코드를 입력하세요
SELECT A.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY, SUM(S.SALES*PRICE) AS TOTAL_SALES
FROM BOOK_SALES S
JOIN BOOK B USING (BOOK_ID)
JOIN AUTHOR A USING (AUTHOR_ID)
WHERE S.SALES_DATE LIKE '2022-01%'
GROUP BY B.CATEGORY, A.AUTHOR_ID
ORDER BY A.AUTHOR_ID, CATEGORY DESC

