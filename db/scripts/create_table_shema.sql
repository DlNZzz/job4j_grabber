create table if not exists schema(
	id serial primary key,
	name varchar(255),
	text text,
	link varchar(255) unique,
	created timestamp
);