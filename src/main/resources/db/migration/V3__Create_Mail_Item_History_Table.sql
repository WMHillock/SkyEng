CREATE TABLE item_history
(
    id                    BIGINT PRIMARY KEY,
    mail_item_id          BIGINT      NOT NULL,
    source_office_id      BIGINT,
    destination_office_id BIGINT,
    interaction_date      TIMESTAMP   NOT NULL,
    interaction_type      VARCHAR(50) NOT NULL,
    FOREIGN KEY (mail_item_id) REFERENCES mail_item (id),
    FOREIGN KEY (source_office_id) REFERENCES postal_office (id),
    FOREIGN KEY (destination_office_id) REFERENCES postal_office (id)
);
