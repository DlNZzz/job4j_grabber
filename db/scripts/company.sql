CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);


insert into company(id, name) values (0, 'abc'), (1, 'qwe'), (2, 'asd'), (3, 'zxc'), (4, 'sdf'), (5, 'xcv');
insert into person(id, name, company_id) values (0, 'q', 1), (1, 'a', 0), (2, 'z', 4), (3, 'x', 5), (4, 's', 1), (5, 'w', 3), (6, 'e', 4);

select * from company;
select * from person;

select * from person as p left join company as c on p.company_id = c.id where company_id != 5;

select c.name, count(p.company_id) from person p right join company c on p.company_id = c.id
group by c.name, p.company_id;
