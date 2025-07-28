SELECT u.username, b.borrowing_id, DATE_FORMAT(borrow_date, "%M/%Y") AS borrow_details
FROM user u
INNER JOIN borrowing b ON u.user_id=b.user_id
ORDER BY u.username DESC;