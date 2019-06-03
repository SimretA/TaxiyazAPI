
    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    create table route (
       id bigint not null,
        hops integer not null,
        latitude double precision not null,
        longitude double precision not null,
        price double precision not null,
        title varchar(255),
        primary key (id)
    ) engine=InnoDB
