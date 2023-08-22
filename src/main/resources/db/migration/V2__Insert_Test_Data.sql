INSERT INTO mail_item (type, recipient_index, recipient_address, recipient_name)
VALUES ('LETTER', '123', '123 Main St', 'John Doe'),
       ('PACKAGE', '456', '456 Elm St', 'Jane Smith');

INSERT INTO postal_office (index, name, address)
VALUES ('123', 'Main Postal Office', '789 Central Ave'),
       ('456', 'Local Postal Office', '567 Elm St');

INSERT INTO item_history (timestamp, on_the_way, mail_item_id, interaction_type, current_office, office_to_delivery)
VALUES (NOW(), false, 1, 'CREATED', 2, 1),
       (NOW(), false, 2, 'CREATED', 1, 1);