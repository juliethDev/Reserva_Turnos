use DentalOffice;

insert into role (name) values ("USER");
select * from role;

insert into user (password,username) values ("Ju1234@@","Julieth");
select * from user;

insert into roles_user (user_id,role_id) values (1,1);
select * from roles_user;

select * from patient;