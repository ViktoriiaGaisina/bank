create table account (
id BIGSERIAL PRIMARY KEY,
customer_id BIGINT,
CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);