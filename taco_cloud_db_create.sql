
    create table comment (
       id bigint not null,
        comment varchar(255),
        route_id bigint not null,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table location (
       id bigint not null,
        latitude double precision not null,
        longitude double precision not null,
        primary key (id)
    ) engine=InnoDB

    create table rate (
       id bigint not null,
        route_route_id bigint,
        user_phone_no bigint,
        primary key (id)
    ) engine=InnoDB

    create table route (
       route_id bigint not null,
        hops integer not null,
        locations tinyblob,
        price double precision not null,
        title varchar(255),
        primary key (route_id)
    ) engine=InnoDB

    create table user (
       phone_no bigint not null,
        user_name varchar(255),
        primary key (phone_no)
    ) engine=InnoDB

    alter table rate 
       add constraint FKqcu1pjhnq7bnajb73mxvx8swe 
       foreign key (route_route_id) 
       references route (route_id)

    alter table rate 
       add constraint FK7y3whmd53r9n8sp865kvsmjme 
       foreign key (user_phone_no) 
       references user (phone_no)

    create table comment (
       id bigint not null,
        comment varchar(255),
        route_id bigint not null,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table location (
       id bigint not null,
        latitude double precision not null,
        longitude double precision not null,
        primary key (id)
    ) engine=InnoDB

    create table rate (
       id bigint not null,
        route_route_id bigint,
        user_phone_no bigint,
        primary key (id)
    ) engine=InnoDB

    create table route (
       route_id bigint not null,
        hops integer not null,
        locations tinyblob,
        price double precision not null,
        title varchar(255),
        primary key (route_id)
    ) engine=InnoDB

    create table user (
       phone_no bigint not null,
        user_name varchar(255),
        primary key (phone_no)
    ) engine=InnoDB

    alter table rate 
       add constraint FKqcu1pjhnq7bnajb73mxvx8swe 
       foreign key (route_route_id) 
       references route (route_id)

    alter table rate 
       add constraint FK7y3whmd53r9n8sp865kvsmjme 
       foreign key (user_phone_no) 
       references user (phone_no)
