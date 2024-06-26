create sequence brand_seq start with 1 increment by 1;
create sequence cart_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;
create sequence composition_seq start with 1 increment by 1;


create table brands
(
    id    bigint not null,
    title varchar(255),
    product_id bigint unique,
    primary key (id)
);
create table carts
(
    id      bigint not null,
    user_id bigint unique,
    primary key (id)
);
create table carts_products
(
    cart_id    bigint not null,
    product_id bigint not null
);
create table categories
(
    id    bigint not null,
    title varchar(255),
    primary key (id)
);
create table orders
(
    total_price float(53),
    total_quantity int not null,
    created     timestamp(6),
    id          bigint not null,
    updated     timestamp(6),
    user_id     bigint,
    address     varchar(255),
    orderstatus varchar(255) check (orderstatus in ('NEW', 'APPROVED', 'CANCELED', 'PAID', 'CLOSED')),
    title       varchar(255),
    primary key (id)
);
create table products
(
    price integer,
    id    bigint not null,
    color varchar(50),
    quantity INT,
    unit varchar(50),
    brand_id bigint,
    composition_id bigint,
    primary key (id)
);
create table products_categories
(
    category_id bigint not null,
    product_id  bigint not null
);
create table users
(
    archive  boolean not null,
    cart_id  bigint unique,
    id       bigint  not null,
    email    varchar(255),
    username varchar(255),
    password varchar(255),
    phone    varchar(255),
    role     varchar(255) check (role in ( 'ROLE_CLIENT', 'ROLE_MANAGER', 'ROLE_ADMIN')),
    primary key (id)
);
create table compositions
(
    id    bigint not null,
    title varchar(255),
    product_id bigint,
    primary key (id)
);
alter table if exists carts add constraint carts_user_id foreign key (user_id) references users;
alter table if exists carts_products add constraint carts_products_product_id foreign key (product_id) references products;
alter table if exists carts_products add constraint carts_products_cart_id foreign key (cart_id) references carts;
alter table if exists orders add constraint orders_user_id foreign key (user_id) references users;
alter table if exists products_categories add constraint products_categories_category_id foreign key (category_id) references categories;
alter table if exists products_categories add constraint products_categories_product_id foreign key (product_id) references products;
alter table if exists users add constraint users_cart_id foreign key (cart_id) references carts;
alter table if exists products add constraint products_brand_id foreign key (brand_id) references brands;
alter table if exists BRANDS add constraint brands_product_id foreign key (product_id) references products;
alter table if exists compositions add constraint composition_product_id foreign key (product_id) references products;