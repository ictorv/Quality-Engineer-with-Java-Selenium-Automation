SELECT user_id, username, email, ROUND(DATEDIFF(CURDATE(),dob)/365) AS age_in_years
FROM user 
WHERE ROUND(DATEDIFF(CURDATE(),dob)/365.25)<35
ORDER BY username ASC;
