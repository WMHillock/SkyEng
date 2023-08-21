-- Добавление внешнего ключа на mail_item_id в таблице item_history
ALTER TABLE item_history
    ADD CONSTRAINT fk_mail_item
        FOREIGN KEY (mail_item_id)
            REFERENCES mail_item (id);

-- Добавление внешнего ключа на current_office_id в таблицах mail_item и item_history
ALTER TABLE mail_item
    ADD CONSTRAINT fk_current_office
        FOREIGN KEY (current_office_id)
            REFERENCES postal_office (id);

ALTER TABLE item_history
    ADD CONSTRAINT fk_current_office_history
        FOREIGN KEY (current_office_id)
            REFERENCES postal_office (id);

-- Добавление внешнего ключа на office_to_delivery_id в таблице item_history
ALTER TABLE item_history
    ADD CONSTRAINT fk_office_to_delivery
        FOREIGN KEY (office_to_delivery_id)
            REFERENCES postal_office (id);
