SELECT isbn, title, author, publication_date
FROM book
WHERE genre NOT IN ('Mystery','Fiction')
ORDER BY isbn DESC;