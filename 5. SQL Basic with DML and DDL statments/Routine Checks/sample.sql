SELECT borrowing_id,user_id, borrow_date
FROM borrowing
WHERE return_date>due_date
ORDER BY borrowing_id DESC;