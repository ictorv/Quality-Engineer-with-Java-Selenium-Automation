SELECT username, phoneno,  
(SELECT MAX(fine) from borrowing b WHERE b.user_id=u.user_id GROUP BY user_id) AS max_fine
FROM user u
ORDER BY phoneno ASC;
