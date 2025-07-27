SELECT 
    isbn, title,author,
CASE 
    WHEN price<300 THEN 'Affordable Price'
    WHEN price>=300 AND price<500 THEN 'Moderate Price'
    ELSE 'Very Expensive'
END  AS price_categorisation
FROM book
ORDER BY title ASC;