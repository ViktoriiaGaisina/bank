create table loan (
id serial primary key ,
principal DECIMAL(19, 2) NOT NULL,
months_until_maturity INT NOT NULL
);