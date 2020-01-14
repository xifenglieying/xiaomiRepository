create table USER
(
    ID    INTEGER auto_increment,
    NAME  VARCHAR(32) not null,
    CODE  VARCHAR(32) not null,
    TOKEN VARCHAR(32),
    constraint EMPLOYEE_PK
        primary key (ID)
);