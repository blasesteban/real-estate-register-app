/*DROP TYPE IF EXISTS role_type_enum CASCADE;
CREATE TYPE role_type_enum AS ENUM ('architect', 'realtor', 'owner');
*/
drop table if exists role CASCADE;

create table role
(
    id        bigint generated by default as identity,
    role_type integer not null,
    building  bigint,
    person    bigint,
    primary key (id)
);

alter table role
    add constraint Fk_roles_building foreign key (building) references building;

alter table role
    add constraint Fk_roles_person foreign key (person) references person;