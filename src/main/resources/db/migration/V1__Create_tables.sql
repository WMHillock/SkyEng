-- Создание таблицы mail_item
CREATE TABLE mail_item
(
    id                SERIAL PRIMARY KEY,
    type              VARCHAR(255),
    state             VARCHAR(255),
    recipient_index   VARCHAR(255),
    recipient_address VARCHAR(255),
    recipient_name    VARCHAR(255),
    current_office_id BIGINT
);

-- Создание таблицы item_history
CREATE TABLE item_history
(
    id                    SERIAL PRIMARY KEY,
    mail_item_id          BIGINT,
    interaction_type      VARCHAR(255),
    timestamp             TIMESTAMP,
    current_office_id     BIGINT,
    office_to_delivery_id BIGINT
);

-- Создание таблицы postal_office
CREATE TABLE postal_office
(
    id      SERIAL PRIMARY KEY,
    index   VARCHAR(255),
    name    VARCHAR(255),
    address VARCHAR(255)
);
