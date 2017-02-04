create table itubuzz.replies
(
reply_number bigint not null primary key auto_increment,
reply_id bigint not null,
reply_text longtext not null,
immparent_id bigint not null,
post_id int not null,
log_user_id int not null,
log_reply_name varchar(30) not null
);


create table itubuzz.questions
(
question_id int not null primary key auto_increment,
question_text longtext,
log_user_id int not null,
log_user_name varchar(30) not null
);

create table itubuzz.posts
(
post_id int not null primary key auto_increment,
post_text longtext not null,
log_user_id int not null,
log_user_name varchar(50) not null
);

create table itubuzz.answers
(
answer_number bigint not null primary key auto_increment,
answer_id bigint not null,
answer_text longtext,
immparent_id bigint not null,
question_id int not null,
log_user_id int not null,
log_user_name varchar(30) not null
);

CREATE TABLE itubuzz.profile_images (

        imageid int NOT NULL AUTO_INCREMENT,

        image LONGBLOB,

        e_mail_id varchar(100) NOT NULL UNIQUE, 

        PRIMARY KEY (imageid));

CREATE TABLE itubuzz.userLogin (
  user_id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  first_name varchar(30) NOT NULL,
  middle_name varchar(30) DEFAULT NULL,
  last_name varchar(30) NOT NULL,
  password varchar(30) NOT NULL ,
  e_mail_id varchar(100) NOT NULL UNIQUE,
  date_of_birth date DEFAULT NULL,
  department varchar(30) NOT NULL,
  trimester varchar(20) NOT NULL,
  year_of_passing date DEFAULT NULL,
  user_type varchar(15) NOT NULL
 );

CREATE TABLE itubuzz.user_group
(
	group_id bigint 
	,user_id bigint
);

CREATE TABLE itubuzz.groups
(
	group_id bigint primary key AUTO_INCREMENT
	,group_name VARCHAR(50) NOT NULL
);

create table itubuzz.group_replies
(
g_reply_number bigint not null primary key auto_increment,
g_reply_id bigint not null,
g_reply_text longtext not null,
g_immparent_id bigint not null,
g_post_id int not null,
log_user_id int not null,
log_reply_name varchar(30) not null,
group_id bigint NOT NULL
);

create table itubuzz.group_posts
(
g_post_id int not null primary key auto_increment,
g_post_text longtext not null,
log_user_id int not null,
log_user_name varchar(50) not null,
group_id bigint not null
);

commit;
