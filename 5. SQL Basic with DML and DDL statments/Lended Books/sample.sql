SELECT user_id, COUNT(user_id) AS count_borrowed
FROM borrowing
WHERE YEAR(borrow_date) <>2024
GROUP BY user_id
ORDER BY user_id DESC;