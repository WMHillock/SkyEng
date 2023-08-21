INSERT INTO mail_item (type, state, recipient_index, recipient_address, recipient_name, current_office_id)
VALUES
    ('Type1', 'State1', 'Index1', 'Address1', 'Name1', 1),
    ('Type2', 'State2', 'Index2', 'Address2', 'Name2', 2),
    ('Type3', 'State3', 'Index3', 'Address3', 'Name3', 3),
    ('Type4', 'State4', 'Index4', 'Address4', 'Name4', 1),
    ('Type5', 'State5', 'Index5', 'Address5', 'Name5', 2);

INSERT INTO item_history (mail_item_id, interaction_type, timestamp, current_office_id, office_to_delivery_id)
VALUES
    (1, 'InteractionType1', '2023-08-21 10:00:00', 1, 2),
    (2, 'InteractionType2', '2023-08-21 11:00:00', 2, 3),
    (3, 'InteractionType3', '2023-08-21 12:00:00', 1, 3),
    (4, 'InteractionType4', '2023-08-21 13:00:00', 3, 1),
    (5, 'InteractionType5', '2023-08-21 14:00:00', 2, 1);

INSERT INTO postal_office (index, name, address)
VALUES
    ('Index1', 'OfficeName1', 'Address1'),
    ('Index2', 'OfficeName2', 'Address2'),
    ('Index3', 'OfficeName3', 'Address3'),
    ('Index4', 'OfficeName4', 'Address4'),
    ('Index5', 'OfficeName5', 'Address5');
