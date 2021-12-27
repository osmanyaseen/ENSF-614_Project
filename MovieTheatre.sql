DROP DATABASE IF EXISTS MOVIETHEATRE;
CREATE DATABASE MOVIETHEATRE; 
USE MOVIETHEATRE;

DROP TABLE IF EXISTS REGISTEREDUSER;
CREATE TABLE REGISTEREDUSER (
	Name					varchar(30)	not null,
	Password				varchar(30) not null,
    Email			        varchar(30) not null,
    CreditCardNumber        varchar(16) not null,
    CreditCardDate          varchar(5) not null,
    CreditCardCVV           integer not null,
    AnnualFeeDate           date,
    ActiveStatus            boolean,
	primary key (Email)
);

INSERT INTO REGISTEREDUSER (Name, Password, Email, CreditCardNumber, CreditCardDate, CreditCardCVV, AnnualFeeDate, ActiveStatus)
VALUES
("Deylin Yiao", "Apples123!", "dyiao@ucalgary.ca", "1111222233334444", "04/22", 344, "2012-12-02", true),
("Ash Ketchum", "oranges", "akat@ucalgary.ca", "5555666677778888", "06/22", 123, "2012-11-02", true),
("Dark Clark", "Password", "dclark@ucalgary.ca", "0000111122223333", "07/22", 435, "2012-12-22", true);

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
	MovieID					    integer	not null,
	MovieName				varchar(30) not null,
    TheatreName			    varchar(30) not null,
    Auditorium              integer not null,
    Showtime                datetime,
    isPublic                boolean,
	primary key (movieID)
);

INSERT INTO MOVIE (MovieID, MovieName, TheatreName, Auditorium, Showtime, isPublic)
VALUES
(0, "Dune", "Chinook Mall Cineplex", 1, "2021-12-08 17:00", true),
(1, "Dune", "Chinook Mall Cineplex", 1, "2021-12-24 21:00", true),
(2, "Dune", "Chinook Mall Cineplex", 1, "2021-12-24 23:00", true),
(3, "Clifford (Pre-order)", "Chinook Mall Cineplex", 2, "2021-12-08 13:00", false),
(4, "Clifford (Pre-order)", "Chinook Mall Cineplex", 2, "2021-12-24 15:00", false),
(5, "Clifford (Pre-order)", "Chinook Mall Cineplex", 2, "2021-12-24 17:00", false);

DROP TABLE IF EXISTS SEAT;
CREATE TABLE SEAT (
	MovieID			        integer	not null,
    SeatID                  integer not null,
	RowLetter				varchar(1) not null,
    ColumnNumber			integer not null,
    Available               boolean,
	primary key(MovieID, SeatID),
    foreign key(MovieID) references MOVIE(MovieID)
);

INSERT INTO SEAT (MovieID, SeatID, RowLetter, ColumnNumber, Available)
VALUES
(0, 0, "A", 1, false),
(0, 1, "A", 2, true),
(0, 2, "A", 3, true),
(0, 3, "A", 4, true),
(0, 4, "B", 1, true),
(0, 5, "B", 2, false),
(0, 6, "B", 3, true),
(0, 7, "B", 4, true),
(0, 8, "C", 1, true),
(0, 9, "C", 2, true),
(0, 10, "C", 3, false),
(0, 11, "C", 4, true),
(1, 0, "A", 1, true),
(1, 1, "A", 2, true),
(1, 2, "A", 3, true),
(1, 3, "A", 4, true),
(1, 4, "B", 1, true),
(1, 5, "B", 2, true),
(1, 6, "B", 3, true),
(1, 7, "B", 4, true),
(1, 8, "C", 1, true),
(1, 9, "C", 2, true),
(1, 10, "C", 3, true),
(1, 11, "C", 4, true),
(2, 0, "A", 1, true),
(2, 1, "A", 2, true),
(2, 2, "A", 3, true),
(2, 3, "A", 4, true),
(2, 4, "B", 1, true),
(2, 5, "B", 2, true),
(2, 6, "B", 3, true),
(2, 7, "B", 4, true),
(2, 8, "C", 1, true),
(2, 9, "C", 2, true),
(2, 10, "C", 3, true),
(2, 11, "C", 4, true),
(3, 0, "A", 1, true),
(3, 1, "A", 2, true),
(3, 2, "A", 3, false),
(3, 3, "A", 4, true),
(3, 4, "B", 1, true),
(3, 5, "B", 2, true),
(3, 6, "B", 3, false),
(3, 7, "B", 4, true),
(3, 8, "C", 1, true),
(3, 9, "C", 2, true),
(3, 10, "C", 3, true),
(3, 11, "C", 4, true),
(4, 0, "A", 1, true),
(4, 1, "A", 2, true),
(4, 2, "A", 3, true),
(4, 3, "A", 4, true),
(4, 4, "B", 1, true),
(4, 5, "B", 2, true),
(4, 6, "B", 3, true),
(4, 7, "B", 4, true),
(4, 8, "C", 1, true),
(4, 9, "C", 2, true),
(4, 10, "C", 3, true),
(4, 11, "C", 4, true),
(5, 0, "A", 1, true),
(5, 1, "A", 2, true),
(5, 2, "A", 3, true),
(5, 3, "A", 4, true),
(5, 4, "B", 1, true),
(5, 5, "B", 2, true),
(5, 6, "B", 3, true),
(5, 7, "B", 4, true),
(5, 8, "C", 1, true),
(5, 9, "C", 2, true),
(5, 10, "C", 3, true),
(5, 11, "C", 4, true);

DROP TABLE IF EXISTS RECEIPT;
CREATE TABLE RECEIPT (
	ReceiptID				integer	not null auto_increment,
	Price					double not null,
	Date					datetime not null,
	primary key (ReceiptID)
);

DROP TABLE IF EXISTS VOUCHER;
CREATE TABLE VOUCHER (
	VoucherID				integer	not null auto_increment,
	VoucherCode				integer not null,
    Amount				    double not null,
    ExpiryDate              datetime,
	primary key (voucherID)
);

DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
	TicketID				integer	not null auto_increment,
    SeatID                  integer not null,
    MovieID                 integer not null,
    Price				    double not null,
    Date                    datetime,
    ActiveStatus            boolean not null,
    Email                   varchar(30) not null,
	primary key (ticketID),
    foreign key (MovieID, SeatID) references SEAT(MovieID, SeatID)
);