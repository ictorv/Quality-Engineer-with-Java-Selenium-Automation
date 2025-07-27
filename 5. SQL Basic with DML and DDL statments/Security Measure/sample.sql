SELECT user_id , username, CONCAT(LEFT(phoneno,4),LEFT(username,5)) AS password
FROM user 
ORDER BY username ASC;