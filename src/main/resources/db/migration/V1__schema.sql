DROP TABLE IF EXISTS person CASCADE;
DROP SEQUENCE IF EXISTS person_id_seq;
CREATE TABLE person
(
    id           long identity NOT NULL PRIMARY KEY,
    firstname    varchar(20) NOT NULL,
    surname      varchar(20) NOT NULL,
    address      varchar(150),
    phone_number varchar(20)
);
DROP TABLE IF EXISTS building CASCADE;
DROP SEQUENCE IF EXISTS building_id_seq;
CREATE TABLE building
(
    id            long identity NOT NULL PRIMARY KEY,
    square_meters long NOT NULL,
    price         long NOT NULL
);
DROP TYPE IF EXISTS role_type_enum CASCADE;
CREATE TYPE role_type_enum AS ENUM ('architect', 'realtor', 'owner');

DROP TABLE IF EXISTS role CASCADE;
DROP SEQUENCE IF EXISTS role_id_seq;
CREATE TABLE role
(
    id          long identity NOT NULL PRIMARY KEY,
    role_type   role_type_enum NOT NULL,
    person_id   long           NOT NULL,
    building_id long           NOT NULL
);

DROP TYPE IF EXISTS room_type_enum CASCADE;
CREATE TYPE room_type_enum AS ENUM ('BALCONY', 'BATHROOM', 'BEDROOM', 'DINING_ROOM', 'GARAGE', 'GUEST_ROOM',
    'HALLWAY', 'KITCHEN', 'LIVING_ROOM', 'OFFICE', 'UTILITY_ROOM', 'WARDROBE');

DROP TABLE IF EXISTS room CASCADE;
DROP SEQUENCE IF EXISTS room_id_seq;
CREATE TABLE room
(
    id          long identity NOT NULL PRIMARY KEY,
    room_type   room_type_enum NOT NULL,
    size        long           NOT NULL,
    building_id long           NOT NULL
);

ALTER TABLE role
    ADD FOREIGN KEY (person_id)
        REFERENCES person (id)
        ON DELETE SET NULL;

ALTER TABLE role
    ADD FOREIGN KEY (building_id)
        REFERENCES building (id)
        ON DELETE SET NULL;

ALTER TABLE room
    ADD FOREIGN KEY (building_id)
        REFERENCES building (id)
        ON DELETE SET NULL;

