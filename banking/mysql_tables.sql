-- create database banking; 
-- run as dba
 use banking;

drop user bellgl;
CREATE USER bellgl IDENTIFIED BY 'Snapper_22';
grant all on banking to bellgl;

drop table bank_transaction_t;
drop table cat_trans_lnk_t;
drop table category_t;
drop table user_t;

create table bank_transaction_t (
   tdate date, 
   description varchar(2000), 
   amount decimal(6,2),
   account varchar(200),
   usr varchar(20), 
   fye integer, 
   bank varchar(50), 
   category varchar(20),
   id integer auto_increment primary key);
   
create table category_t (
  name varchar(50), 
  type varchar(50), 
  deduction decimal(6,2), 
  usr varchar(50), 
  sortorder integer, 
  fye integer,
  primary key (name, usr, fye));

create table cat_trans_lnk_t (
  category varchar(50), 
  search varchar(200), 
  usr varchar(20), 
  fye integer,
  primary key (search, usr, fye),
  foreign key (category, usr, fye) references category_t(name, usr, fye));
  
create table user_t (
  grp varchar(20), usr varchar(20) primary key);
 
create or replace view bank_transaction  as 
select * 
from bank_transaction_t a
order by tdate;

create or replace view cat_trans_lnk as
select * 
from cat_trans_lnk_t a
order by search;

create or replace view category as
select * 
from category_t a
order by name;

create or replace view transaction_summary as
select c.type, t.category, t.fye, t.usr, sum(t.amount) as amount, sum(t.amount*c.deduction) as deductable
from bank_transaction t, category c
where t.category = c.name
group by c.type, t.category, t.fye, t.usr
order by c.type, t.category, t.usr;

create or replace view group_summary as
select c.type, t.category, t.fye, u.grp, sum(t.amount) as amount, sum(t.amount*c.deduction) as deductable
from bank_transaction_t t, category_t c, user_t u
where t.category = c.name
and u.grp = (select grp from user_t u2, session_t s where u2.usr = s.usr and u.usr = u2.usr)   
and u.usr = c.usr
and c.usr = t.usr
and c.fye = t.fye 
group by c.type, t.category, t.fye, u.grp
order by c.type, t.category;

grant all PRIVILEGES on banking.category_t to bellgl;
grant all PRIVILEGES on banking.category to bellgl;
grant all PRIVILEGES on banking.bank_transaction_t to bellgl;
grant all PRIVILEGES on banking.bank_transaction to bellgl;
grant all PRIVILEGES on banking.bank_statement to bellgl;
grant all PRIVILEGES on banking.user_t to bellgl;
grant all PRIVILEGES on banking.user to bellgl;
grant all PRIVILEGES on banking.cat_trans_lnk_t to bellgl;
grant all PRIVILEGES on banking.cat_trans_lnk to bellgl;
grant all PRIVILEGES on banking.transaction_summary to bellgl;
grant all PRIVILEGES on banking.transaction_summary_grp to bellgl;

 