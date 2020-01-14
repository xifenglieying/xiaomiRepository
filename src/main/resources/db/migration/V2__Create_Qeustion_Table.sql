create table QUESTION
(
	pkid int auto_increment,
	question varchar(64),
	tag varchar(32),
	title varchar(32) not null,
	constraint QUESTION_pk
		primary key (pkid)
);