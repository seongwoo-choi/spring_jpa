-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (1, 'martin', 'martin@another.com', now(), now());


-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (2, 'dennis', 'dennis@another.com', now(), now());


-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (3, 'sophia', 'sophia@another.com', now(), now());


-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (4, 'james', 'james@another.com', now(), now());


-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (5, 'martin', 'martin@another.com', now(), now());


insert into publisher(`id`, `name`)
values (1, 'jpa');


insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`)
values (1, 'JPA1', 1, false, 100);


insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`)
values (2, 'Spring Security', 1, false, 200);


insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`)
values (3, 'SpringBoot', 1, true, 100);