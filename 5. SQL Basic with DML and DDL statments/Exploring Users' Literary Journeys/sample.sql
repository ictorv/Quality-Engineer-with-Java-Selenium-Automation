SELECT u.username, bo.genre, b.borrow_date
FROM user u
INNER JOIN borrowing b ON b.user_id=u.user_id 
INNER JOIN book bo ON bo.isbn=b.isbn
ORDER BY u.username DESC;