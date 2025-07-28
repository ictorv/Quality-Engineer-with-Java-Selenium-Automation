SELECT user_id, username, gender
FROM user
WHERE email NOT LIKE '%@gmail.com'
ORDER BY gender ASC;