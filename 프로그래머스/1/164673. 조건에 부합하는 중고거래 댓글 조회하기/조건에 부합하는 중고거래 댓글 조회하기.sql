-- 2022년 10월에 작성된 게시글 제목, 게시글 ID, 댓글 ID, 댓글 작성자 ID, 댓글 내용, 댓글 작성일을 조회
-- 댓글 작성일을 기준으로 오름차순 정렬해주시고, 댓글 작성일이 같다면 게시글 제목을 기준으로 오름차순 정렬

SELECT B.TITLE, B.BOARD_ID, R.REPLY_ID, R.WRITER_ID, 
       R.CONTENTS, DATE_FORMAT(R.CREATED_DATE, '%Y-%m-%d') AS CREATED_DATE
FROM USED_GOODS_BOARD B INNER JOIN USED_GOODS_REPLY R
ON B.BOARD_ID = R.BOARD_ID
WHERE B.CREATED_DATE LIKE '2022-10-%'
ORDER BY R.CREATED_DATE ASC, TITLE ASC;
