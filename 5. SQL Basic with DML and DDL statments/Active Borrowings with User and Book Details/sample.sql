SELECT u.user_id, u.username, u.email, bo.isbn, bo.author, b.borrow_date, b.due_date
FROM user u
INNER JOIN borrowing b ON u.user_id=b.user_id
INNER JOIN book bo ON b.isbn=bo.isbn
WHERE b.return_date IS NULL
ORDER BY u.user_id DESC;