CREATE TABLE mail_iltem
(
    id                BIGINT PRIMARY KEY,
    type              VARCHAR(50) NOT NULL,
    recepient_index   VARCHAR(50),
    recepient_address VARCHAR(255),
    recepient_name    VARCHAR(255)
);
