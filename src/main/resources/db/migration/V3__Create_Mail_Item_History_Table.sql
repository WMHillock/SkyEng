CREATE TABLE item_history
(

    mail_item_id          BIGINT,
    source_office_id      BIGINT,
    destination_office_id BIGINT,
    interaction_type      VARCHAR(255),
    interaction_date      DATE,
    PRIMARY KEY (mail_item_id, source_office_id),
    FOREIGN KEY (mail_item_id) REFERENCES mail_item (id),
    FOREIGN KEY (source_office_id) REFERENCES postal_office (id),
    FOREIGN KEY (destination_office_id) REFERENCES postal_office (id)
);

