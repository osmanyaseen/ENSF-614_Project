DROP TABLE IF EXISTS RECEIPT;
CREATE TABLE RECEIPT (
	ReceiptID				integer	not null,
	Price					double not null,
	Date					varchar(10) not null,
	primary key (ReceiptID)
);

INSERT INTO RECEIPT (ReceiptID, Price, Date)
VALUES
(255, 18.56, "2021-08-13"),
(352, 14.43, "2021-09-10"),
(1806, 20.79, "2021-03-22");

DROP TABLE IF EXISTS VOUCHER;
CREATE TABLE VOUCHER (
	voucherID				integer	not null,
	voucherCode				integer not null,
    amount				    double not null,
    expiryDate              varchar(10) not null,
	primary key (voucherID)
);

INSERT INTO VOUCHER (voucherID, voucherCode, amount, expiryDate)
VALUES
(1910, 43323, 5.00, "2022-09-13"),
(23450, 15468, 5.50, "2022-10-06"),
(65756, 57423, 10.25, "2022-04-21"), 
(34535, 23445, 7.50, "2022-05-19"),
(43544, 63561, 6.00, "2022-07-04"),
(1323, 75324, 15.00, "2022-11-11");
