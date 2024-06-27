create sequence brand_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence composition_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table brands
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);
create table categories
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);
create table compositions
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);
create table orders
(
    total_price float(53),
    total_quantity integer,
    created timestamp(6),
    id bigint not null,
    updated timestamp(6),
    user_id bigint,
    address varchar(255),
    order_status varchar(255) check (order_status in ('CART','APPROVED','CANCELED','PAID','CLOSED')),
    primary key (id)
);
create table products
(
    price integer,
    quantity integer,
    brand_id bigint,
    category_id bigint,
    composition_id bigint,
    id bigint not null,
    order_id bigint,
    article varchar(255) unique,
    color varchar(255),
    unit varchar(255),
    primary key (id)
);
create table users
(
    archive boolean not null,
    id bigint not null,
    email varchar(255),
    password varchar(255),
    phone varchar(255),
    role varchar(255) check (role in ('ROLE_CLIENT','ROLE_MANAGER','ROLE_ADMIN')),
    username varchar(255),
    primary key (id)
);
alter table if exists orders add constraint orders_user_id foreign key (user_id) references users;
alter table if exists products add constraint products_brand_id foreign key (brand_id) references brands;
alter table if exists products add constraint products_category_id foreign key (category_id) references categories;
alter table if exists products add constraint products_composition_id foreign key (composition_id) references compositions;
-- alter table if exists products add constraint products_order_id foreign key (order_id) references orders;
