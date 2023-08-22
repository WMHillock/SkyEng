CREATE TABLE mail_item
(
    id                SERIAL PRIMARY KEY,
    type              VARCHAR(255),
    recipient_index   VARCHAR(255),
    recipient_address VARCHAR(255),
    recipient_name    VARCHAR(255)
);

CREATE TABLE postal_office
(
    id      SERIAL PRIMARY KEY,
    index   VARCHAR(255),
    name    VARCHAR(255),
    address VARCHAR(255)
);

CREATE TABLE item_history
(
    id                 SERIAL PRIMARY KEY,
    timestamp          TIMESTAMP,
    on_the_way         BOOLEAN,
    mail_item_id       BIGINT,
    interaction_type   VARCHAR(255),
    current_office     BIGINT,
    office_to_delivery BIGINT,
    FOREIGN KEY (mail_item_id) REFERENCES mail_item (id),
    FOREIGN KEY (current_office) REFERENCES postal_office (id),
    FOREIGN KEY (office_to_delivery) REFERENCES postal_office (id)
);