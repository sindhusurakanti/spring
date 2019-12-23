
drop database brightlife;
create database brightlife;

use brightlife;

create table user(
id bigint auto_increment primary key, 
email varchar(256) not null, 
password  text  , 
salt varchar(256) , 
facebook_id varchar(256),  
google_id varchar(256),
name varchar(256) ,
code varchar(256) , unique key (code),
is_verified boolean,
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

create table user_session(
id bigint auto_increment primary key,
session_id varchar(36),
user_id bigint, foreign key FK_user (user_id) references user(id),
login_time datetime not null,
is_active boolean,
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

CREATE TRIGGER before_insert_user_session
  BEFORE INSERT ON user_session
  FOR EACH ROW
  SET new.session_id = uuid();
    

create table sponsor(
id bigint auto_increment primary key, 
user_id bigint, foreign key FK_user (user_id) references user(id),
sponsored_student_id bigint,
firstname varchar(256),
lastname varchar(256),
organisation varchar(256),
email_address varchar(256),
brightlife_info varchar(256),
mobile_number bigint,
address varchar(256),
city varchar(256),
state varchar(256),
country varchar(256),
pin_code bigint,
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

create table guardian(
id bigint auto_increment primary key, 
user_id bigint, foreign key FK_user (user_id) references user(id),
firstname varchar(256),
lastname varchar(256),
organisation varchar(256),
email_address varchar(256),
brightlife_info varchar(256),
mobile_number bigint,
address varchar(256),
city varchar(256),
state varchar(256),
country varchar(256),
pin_code bigint,
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

create table otp (
id bigint auto_increment primary key, 
user_id bigint, foreign key FK_user (user_id) references user(id),
 email varchar(256),
purpose varchar(256), 
otp bigint not null,
issued_date datetime,
valid_upto datetime,
is_otp_verified tinyint(1),
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

create table message_template(
id bigint auto_increment primary key,
message_type varchar(256), 
purpose varchar(256), 
description text,
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);



create table resource(
id bigint auto_increment primary key,
`key` varchar(256),
value varchar(256),
data_type varchar(256),
sub_type varchar(256),
is_active boolean,
created_by varchar(256),
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

create table resource_profile (
id bigint primary key,
code varchar(256) not null unique,
name varchar(256) not null,
url_prefix text not null,
upload_path text not null,
is_default boolean not null default false,
is_active boolean not null default true,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) ,
last_updated_date datetime );

create table device_resource_profile (
id bigint primary key,
profile_code varchar(256) not null REFERENCES brightlife.resource_profile (code),
client_type varchar(256) not null,
device_type bigint,
device_sub_type varchar(256),
url_prefix text not null,
upload_path text not null,
is_default boolean not null default false,
is_active boolean not null default true,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) ,
last_updated_date datetime );

create table role(
id bigint auto_increment primary key,
name varchar(256),
role_code varchar(256) unique,
description varchar(256),
is_active boolean,
created_by varchar(256),
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);

create table user_role(
id bigint auto_increment primary key,
user_id bigint, foreign key FK_user (user_id) references user(id),
role_code varchar(256), foreign key FK_role (role_code) references role(role_code),
is_active boolean,
created_by varchar(256),
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime,
unique (user_id, role_code));

insert into role(name, role_code) values("Student", "student");
insert into role(name, role_code) values("Guardian", "guardian");
insert into role(name, role_code) values("Sponsor", "sponsor");

insert into resource(`key`, value, data_type, sub_type, is_active) values
("RES_S_ERR_INVALID_SESSION", "Invalid seession, please login again","String", "Error",true),
("RES_S_ERR_INVALID_USER_NAME_PASSWORD", "Invalid username/password","String", "Error",true),
("RES_S_ERR_INVALID_EMAIL", "Invalid Email","String", "Error",true),
("RES_S_ERR_EMAIL_EXISTS", "E-Mail exists","String", "Error",true),
("RES_S_ERR_INVALID_NAME", "Name Cannot be Null","String", "Error",true),
("RES_S_ERR_INVALID_PASSWORD", "Invalid Password","String", "Error",true),
("RES_S_ERR_INVALID_OTP", "Invalid OTP","String", "Error",true),
("RES_S_ERR_INVALID_FACEBOOK_TOKEN", "Invalid Facebook Token","String", "Error",true),
("RES_S_ERR_INVALID_ROLE", "Invalid role","String", "Error",true),
("RES_S_ERR_ROLE_EXISTS", "Role already exists","String", "Error",true),
("RES_S_ERR_UNKNOWN_REQUEST", "Unknown Request","String", "Error",true),
("RES_S_ERR_UNKNOWN_ERROR", "Unknown Error","String", "Error",true);

create table student_profile(
id bigint auto_increment primary key,
user_id bigint, foreign key FK_user (user_id) references user(id),
profile_pic_url text,
full_name varchar(256),
date_of_birth date not null,
language_spoken varchar(256),
is_orphan boolean not null,
gender varchar(256),
email varchar(256),
contact integer(10),
lives_with_or_in varchar(256),
door_no varchar(256),
street varchar(256),
city varchar(256),
district varchar(256),
state varchar(256),
country varchar(256),
postal_code integer(6),
income bigint,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table student_application( 
id bigint auto_increment primary key,
student_id bigint, foreign key FK_student (student_id) references student_profile(id),
status varchar(256) not null,
approved_by varchar(256),
approved_date datetime,
applied_by bigint,
applied_date datetime not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);
 
create table applicant_details( 
id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
email varchar(256), 
mobile_number bigint,
is_handicapped boolean not null,
class varchar(256) not null,
school varchar(256) not null,
performance varchar(256),
hobbies text,
aspirations text,
achievements text,
achievements_url varchar(256),
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table student_guardian(
id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
name varchar(256),
relation_with_student varchar(256),
profession varchar(256),
annual_income integer,
parent_details_available boolean,
fathers_name varchar(256),
mothers_name varchar(256),
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table student_orphanage(
id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
name varchar(256),
contact bigint,
address text,	
postal_code integer,
parent_details_available boolean,
fathers_name varchar(256),
mothers_name varchar(256),
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table student_parent(
id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
stays_with varchar(256),
father_name varchar(256),
father_profession varchar(256),
mother_name varchar(256),
mother_profession varchar(256),
annual_income bigint,
extra_allowance integer,
family_members integer,
member1_name varchar(256),
member1_age integer,
member2_name varchar(256),
member2_age integer,
member3_name varchar(256),
member3_age integer,
is_active varchar(256) not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table applicant_income( id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
earning_person varchar(256) not null,
relation_with_student varchar(256) not null,
annual_income bigint not null,
currency varchar(256) not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);
 
create table applicant_funding( id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
amount bigint not null,
expenses_amount bigint not null,
school_needs_amount bigint not null,
physical_health_amount bigint not null,
currency_code varchar(256) not null,
duration varchar(256) not null,
purpose text not null,
purpose_description text,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);
 
create table applicant_bank_details( id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
account_no bigint not null,
ifsc_code varchar(256) not null,
holder_name varchar(256) not null,
relation_with_applicant varchar(256) not null,
bank_code varchar(256) not null,
branch varchar(256) not null,
branch_address text not null,
state varchar(256) not null,
country varchar(256) not null,
pin_code varchar(256) not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);  
 
 
create table school( 
id bigint auto_increment primary key,
name varchar(256) not null,
area text not null,
area_encompassing varchar(256),
district varchar(256) not null,
state varchar(256) not null,
country varchar(256) not null,
pin_code bigint not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);
 
create table school_contact( 
id bigint auto_increment primary key,
school_id bigint, foreign key FK_school (school_id) references school(id),
person varchar(256) not null,
designation varchar(256) not null,
contact_no_1 bigint not null,
contact_no_2 bigint,
email_id varchar(256),
priority int,
is_primary_contact boolean not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table duration( id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
name varchar(256) not null,
code varchar(256) not null,
recurrency_type varchar(256),
is_recurring boolean,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table currency( id bigint auto_increment primary key,
name varchar(256) not null,
code varchar(256) not null,
symbol varchar(256) not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table funding_purpose( id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
name varchar(256) not null,
short_descritption text not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table applicant_document( 
id bigint auto_increment primary key,
application_id bigint, foreign key FK_application (application_id) references student_application(id),
document_code varchar(256) not null, 
document_image_url text not null,
is_verified varchar(256) not null,
verified_by varchar(256) not null,
verification_date datetime not null,
is_active boolean not null ,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);

create table bank( id bigint auto_increment primary key,
name varchar(256) not null,
code varchar(256) not null,
is_active boolean not null,
created_by varchar(256) not null,
created_date datetime not null,
last_updated_by varchar(256) not null,
last_updated_date datetime not null);


create table payment_details(
id bigint auto_increment primary key, 
sponsor_id bigint, foreign key FK_sponsor (sponsor_id) references sponsor(id),
student_id bigint,
pay_id text,
payment_method varchar(256),
amount bigint,
order_id text,
razorpay_payment_id text,
razorpay_payment_signature text,
subscription_id text,
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);


create table sponsor_card_details(
id bigint auto_increment primary key, 
sponsor_id bigint, foreign key FK_sponsor (sponsor_id) references sponsor(id),
card_name varchar(256),
card_number bigint,
card_exp_month int,
card_exp_year int,
razorpay_customer_id text,
razorpay_token_id text,
is_active boolean, 
created_by varchar(256), 
created_date datetime,
last_updated_by varchar(256),
last_updated_date datetime);