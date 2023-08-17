CREATE TABLE mail_item
(
    id                BIGINT PRIMARY KEY,
    type              VARCHAR(50) NOT NULL,
    recipient_index   VARCHAR(50),
    recipient_address VARCHAR(255),
    recipient_name    VARCHAR(255)
);
