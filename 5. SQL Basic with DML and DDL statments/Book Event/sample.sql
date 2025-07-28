SELECT isbn, title, publication_date, price
FROM book
WHERE price BETWEEN 500 AND 900
ORDER BY publication_date DESC;