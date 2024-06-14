create table product (
product_key VARCHAR(255) PRIMARY KEY,
description VARCHAR(255),
pay_rate_unit NUMERIC(19, 2),
unit VARCHAR(255),
value INT,
operation VARCHAR(255),
type VARCHAR(255),
customer_id BIGINT,
loan_id BIGINT,
CONSTRAINT fk_customer
FOREIGN KEY(customer_id)
REFERENCES customer(id),
CONSTRAINT fk_loan
FOREIGN KEY(loan_id)
REFERENCES loan(id)
);
