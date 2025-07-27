SELECT u.user_id, COUNT(b.borrowing_id) AS grouping_books
FROM user u
LEFT JOIN borrowing b ON u.user_id=b.user_id
GROUP BY u.user_id
ORDER BY u.user_id DESC;