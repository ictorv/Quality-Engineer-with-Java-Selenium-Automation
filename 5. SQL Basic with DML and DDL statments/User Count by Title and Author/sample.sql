SELECT b.author, b.genre, COUNT(br.user_id) AS user_total
FROM book b
JOIN borrowing br ON b.isbn=br.isbn
WHERE br.isbn IN (
    SELECT isbn
    FROM borrowing
    WHERE user_id='UN002'
)
GROUP BY b.author, b.genre;
