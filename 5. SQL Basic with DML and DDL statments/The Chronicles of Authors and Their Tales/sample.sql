SELECT author, COUNT(author) AS books_total
FROM book
GROUP BY author
ORDER BY author DESC;
