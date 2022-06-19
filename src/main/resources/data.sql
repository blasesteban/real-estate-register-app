INSERT INTO building(square_meters, price)
values (100, 150),
       (150, 200),
       (200, 250),
       (250, 300),
       (300, 350);

INSERT INTO person(firstname, surname, address, phone_number)
values ('Ana', 'Taylor', '1011 bp', '0036101111111'),
       ('Bea', 'Smith', '1021 bp', '0036202222222'),
       ('Cecil', 'Winston', '1031 bp', '0036303333333'),
       ('Daniel', 'Johnson', '1041 bp', '0036404444444'),
       ('Edith', 'Del Castillo', '1051 bp', '0036505555555'),
       ('Frida', 'Taylor', '1061 bp', '0036606666666'),
       ('Gary', 'Olsen', '1071 bp', '0036707777777'),
       ('Heidi', 'Smith', '1081 bp', '0036808888888'),
       ('Ivone', 'Black', '1091 bp', '0036909999999');

-- enum ('architect', 'realtor', 'owner')
INSERT INTO role(role_type, person, building)
values (0, 1, 1),
       (1, 1, 2),
       (2, 2, 3),
       (0, 2, 4);
-- enum ('BALCONY', 'BATHROOM', 'BEDROOM', 'DINING_ROOM', 'GARAGE', 'GUEST_ROOM',
--         'HALLWAY', 'KITCHEN', 'LIVING_ROOM', 'OFFICE', 'UTILITY_ROOM', 'WARDROBE')
INSERT INTO room(room_type, size, building)
values (8, 20, 1),
       (7, 12, 1),
       (2, 15, 1),
       (1, 5, 1),
       (0, 4, 1);