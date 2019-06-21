DROP DATABASE taxiyaz;
create DATABASE taxiyaz;
use taxiyaz;
create table comment
(
  id       bigint not null,
  comment  varchar(255),
  date     datetime,
  route_id bigint not null,
  user_id  varchar(255),
  primary key (id)
) engine = InnoDB

create table hibernate_sequence
(
  next_val bigint
) engine = InnoDB

insert into hibernate_sequence
values (1)

insert into hibernate_sequence
values (1)

insert into hibernate_sequence
values (1)

insert into hibernate_sequence
values (1)

insert into hibernate_sequence
values (1)

insert into hibernate_sequence
values (1)

insert into hibernate_sequence
values (1)

create table node
(
  id        bigint           not null,
  latitude  double precision not null,
  longitude double precision not null,
  name      varchar(255),
  primary key (id)
) engine = InnoDB

create table node_available_nodes
(
  node_id                  bigint not null,
  available_nodes_route_id bigint not null
) engine = InnoDB

create table node_connection
(
  route_id     bigint           not null,
  price        double precision not null,
  dest_node_id bigint,
  primary key (route_id)
) engine = InnoDB

create table rate
(
  id             bigint           not null,
  rate           double precision not null,
  route_route_id bigint,
  user_phone_no  varchar(255),
  primary key (id)
) engine = InnoDB

create table route
(
  route_id         bigint           not null,
  hops             integer          not null,
  price            double precision not null,
  rating           double precision not null,
  title            varchar(255),
  dest_node_id     bigint,
  routing_nodes_id bigint,
  start_node_id    bigint,
  primary key (route_id)
) engine = InnoDB

create table routing_nodes
(
  id           bigint           not null,
  price        double precision not null,
  next_node_id bigint,
  node_id      bigint,
  primary key (id)
) engine = InnoDB

create table users
(
  phone_no  varchar(255) not null,
  user_name varchar(255),
  primary key (phone_no)
) engine = InnoDB

alter table node_available_nodes
  add constraint FKakks0gsqbsi5akfkm0b9vp481
    foreign key (available_nodes_route_id)
      references node_connection (route_id)

alter table node_available_nodes
  add constraint FK6avftyg2w62ejmo84yswsh0nn
    foreign key (node_id)
      references node (id)

alter table node_connection
  add constraint FKrd4yhrbw4jxucovgij67qiqu6
    foreign key (dest_node_id)
      references node (id)

alter table rate
  add constraint FKqcu1pjhnq7bnajb73mxvx8swe
    foreign key (route_route_id)
      references route (route_id)

alter table rate
  add constraint FKmbu874g5v3oramm5nuslvs2js
    foreign key (user_phone_no)
      references users (phone_no)

alter table route
  add constraint FK7ehsfsvt0a5etp6cu3rt5lhgv
    foreign key (dest_node_id)
      references node (id)

alter table route
  add constraint FKa7vfwifop5pt4p4qh2odan4br
    foreign key (routing_nodes_id)
      references routing_nodes (id)

alter table route
  add constraint FK1r0n1vt229efbt1bqx7ocyf9v
    foreign key (start_node_id)
      references node (id)

alter table routing_nodes
  add constraint FKlxqqi5iejkbe6do5hswij7y1a
    foreign key (next_node_id)
      references routing_nodes (id)

alter table routing_nodes
  add constraint FK2r2c553827g6q781mkqak4yvt
    foreign key (node_id)
      references node (id)
