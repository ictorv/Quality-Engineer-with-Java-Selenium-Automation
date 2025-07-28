CREATE TABLE Borrowing(
    borrowing_id varchar(10),
    isbn varchar(20),
    borrow_date date,
    due_date date,
    return_date date,
    fine decimal(10,2),
    primary key (borrowing_id),
    constraint fk_isbn foreign key (isbn) references Book(isbn)
);