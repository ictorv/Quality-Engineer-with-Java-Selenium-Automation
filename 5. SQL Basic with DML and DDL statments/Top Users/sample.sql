SELECT user_id
FROM user
WHERE user_id IN(
    SELECT user_id
    FROM borrowing
    GROUP BY user_id
    HAVING COUNT(borrowing_id)=(SELECT MIN(borrow_count)
    FROM (SELECT COUNT(borrowing_id) AS borrow_count
            FROM borrowing
            GROUP BY user_id
        ) AS borrow_count
    )
)
ORDER BY user_id DESC;