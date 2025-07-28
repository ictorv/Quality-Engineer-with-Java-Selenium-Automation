SELECT b1.title as title1, b2.title as title2, b1.author, b1.genre
FROM book b1
JOIN book b2 ON b1.author=b2.author AND b1.genre=b2.genre AND b1.title<>b2.title
ORDER BY b1.title DESC, b2.title ASC;