-- Вставка тестовых данных для таблицы MailItemEntity
INSERT INTO mail_item (id, type, recipient_index, recipient_address, recipient_name)
VALUES (1, 'PARCEL', '12345', '123 Main St', 'John Doe'),
       (2, 'LETTER', '67890', '456 Elm St', 'Jane Smith');

-- Вставка тестовых данных для таблицы PostalOfficeEntity
INSERT INTO postal_office (id, index, name, address)
VALUES (1, 'A123', 'Central Office', '789 Center St'),
       (2, 'B456', 'North Office', '123 North St');

-- Вставка тестовых данных для таблицы MailItemHistoryEntity
INSERT INTO item_history (id, mail_item_id, source_office_id, destination_office_id, interaction_date,
                                   interaction_type)
VALUES (1, 1, 1, 2, '2023-08-01', 'ARRIVED'),
       (2, 2, 2, 1, '2023-08-02', 'SENTED');
