ALTER TABLE BOOK
MODIFY genre varchar(10);

ALTER TABLE BOOK
ADD genre_spec varchar(50)
AFTER genre;

