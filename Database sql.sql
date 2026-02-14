create DataBase  bill_system;
use Bill_system;

create table Signup(meter_no varchar(20), username varchar(30), name varchar(30), password varchar(30), usertype varchar(20));

select * from Signup;

create table new_customer(name varchar(30), meter_no varchar(30), address varchar(30), city varchar(30), state varchar(30),email varchar(30),phone_no varchar(12));
select * from new_customer;

create table meter_info(meter_number varchar(30), meter_location varchar(30), meter_type varchar(30), phase_code varchar(30), bill_type varchar(30),days varchar(30));

select * from meter_info;

create table tax(cost_per_unit varchar(30), meter_rent varchar(30), service_charge varchar(30), service_tax varchar(30), swacch_bharat varchar(30), fixed_tax varchar(30));

select * from tax;

insert into tax values('10','45','20','58','5','18');

create table bill(meter_no varchar(30), month varchar(30), unit varchar(30), total_bill varchar(30), status varchar(30));

select * from bill;
