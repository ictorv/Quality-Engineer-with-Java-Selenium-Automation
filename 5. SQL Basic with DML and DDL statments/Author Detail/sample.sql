SELECT CONCAT('The book ', title,' was published on the year ',YEAR(publication_date)) AS title_info
FROM book
ORDER BY YEAR(publication_date) ASC;
