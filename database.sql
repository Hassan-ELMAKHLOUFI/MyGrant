CREATE DATABASE MyGrant;

drop table administrators;
create table ADMINISTRATORS
(
    ID_A           SERIAL              not null,
    FIRST_NAME_A   VARCHAR(160)        null,
    LAST_NAME_A    VARCHAR(160)        null,
    EMAIL_A        VARCHAR(160) unique null,
    PHONE_NUMBER_A VARCHAR(160) unique null,
    PASSWORD_A     VARCHAR(160)        null,
    constraint PK_ADMINISTRATOR primary key (ID_A)
);

drop table universities;
create table universities
(
    id_u   SERIAL       not null,
    name_u VARCHAR(160) null,
    city_u VARCHAR(160) null,
    constraint PK_UNIVERSITY primary key (id_u)
);

drop table archives;
create table ARCHIVES
(
    ID_AR             SERIAL       not null,
    LIST_FILE_NAME_AR VARCHAR(160) null,
    YEAR_AR           DATE         null,
    constraint PK_ARCHIVE primary key (ID_AR)
);

DROP TABLE BROTHERS;
create table BROTHERS
(
    ID_B           SERIAL       not null,
    FIRST_NAME_B   VARCHAR(160) null,
    LAST_NAME_B    VARCHAR(160) null,
    BIRTH_DATE_B   DATE         null,
    IS_STUDYING    BOOL         null,
    IS_HANDICAPPED BOOL         null,
    constraint PK_BROTHER primary key (ID_B)
);

drop table establishments;
create table ESTABLISHMENTS
(
    ID_E      SERIAL       not null,
    ID_U      NUMERIC,
    NAME_E    VARCHAR(160) null,
    CITY_E    VARCHAR(160) null,
    ADDRESS_E VARCHAR(160) null,
    constraint PK_ESTABLISHMENT primary key (ID_E)
);

drop table GRANTs;
create table GRANTS
(
    ID_G    NUMERIC      not null,
    PRICE_G FLOAT8       null,
    TYPE_G  VARCHAR(160) null,
    constraint PK_GRANT primary key (ID_G)
);

drop table HAVE;
create table HAVE
(
    ID_S NUMERIC not null,
    ID_B NUMERIC not null,
    constraint PK_HAVE primary key (ID_S, ID_B)
);

DROP TABLE LEGAL_GUARDIAN;
create table LEGAL_GUARDIAN
(
    ID_L            SERIAL       not null,
    FULL_NAME_L     VARCHAR(160) null,
    PHONE_NUMBER_L  VARCHAR(160) null,
    ANNUAL_INCOME_L VARCHAR(160) null,
    constraint PK_LEGAL_GUARDIAN primary key (ID_L)
);


create table MANAGE
(
    ID_G NUMERIC not null,
    ID_A NUMERIC not null,
    constraint PK_MANAGE primary key (ID_G, ID_A)
);

drop table students;
create table STUDENTS
(
    ID_S            SERIAL       not null,
    ID_E            NUMERIC      null,
    ID_G            NUMERIC      null,
    ID_L            NUMERIC      null,
    FIRST_NAME_S    VARCHAR(160) null,
    LAST_NAME_S     VARCHAR(160) null,
    BIRTH_DATE_S    DATE         null,
    CITY_S          VARCHAR(160) null,
    PROVINCE_S      VARCHAR(160) null,
    ADDRESS_S       VARCHAR(160) null,
    CNE_S           VARCHAR(160) null unique,
    CIN_S           VARCHAR(160) null unique,
    SOCIAL_STATUS_S VARCHAR(160) null,
    HEALTH_STATUS_S VARCHAR(160) null,
    EMAIL_S         VARCHAR(160) null unique,
    PROFILE_PHOTO_S TEXT         null,
    PHONE_NUMBER_S  VARCHAR(160) null unique,
    PASSWORD_S      VARCHAR(160) null,
    constraint PK_STUDENT primary key (ID_S)
);

create table UPDATE
(
    ID_A  NUMERIC not null,
    ID_AR NUMERIC not null,
    constraint PK_UPDATE primary key (ID_A, ID_AR)
);

alter table HAVE
    add constraint FK_HAVE_HAVE_BROTHER foreign key (ID_B)
        references BROTHERS (ID_B)
        on delete cascade on update cascade;

alter table HAVE
    add constraint FK_HAVE_HAVE2_STUDENT foreign key (ID_S)
        references STUDENTS (ID_S)
        on delete cascade on update cascade;

alter table MANAGE
    add constraint FK_MANAGE_MANAGE_ADMINIST foreign key (ID_A)
        references ADMINISTRATORS (ID_A)
        on delete restrict on update restrict;

alter table MANAGE
    add constraint FK_MANAGE_MANAGE2_GRANT foreign key (ID_G)
        references GRANTS (ID_G)
        on delete restrict on update restrict;

alter table STUDENTS
    add constraint FK_STUDENT_BENEFIT_GRANT foreign key (ID_G)
        references GRANTS (ID_G)
        on delete restrict on update restrict;

alter table STUDENTS
    add constraint FK_STUDENT_REGISTER_ESTABLIS foreign key (ID_E)
        references ESTABLISHMENTS (ID_E)
        on delete restrict on update restrict;

alter table STUDENTS
    add constraint FK_STUDENT_TAKECARE_LEGALGUA foreign key (ID_L)
        references LEGAL_GUARDIAN (ID_L)
        on delete restrict on update restrict;

alter table UPDATE
    add constraint FK_UPDATE_UPDATE_ARCHIVE foreign key (ID_AR)
        references ARCHIVES (ID_AR)
        on delete restrict on update restrict;

alter table UPDATE
    add constraint FK_UPDATE_UPDATE2_ADMINIST foreign key (ID_A)
        references ADMINISTRATORS (ID_A)
        on delete restrict on update restrict;

SELECT password_a
FROM administrators
WHERE email_a = ?;


INSERT INTO students (id_s, email_s, phone_number_s, password_s)
VALUES (DEFAULT, ?, ?, ?);

INSERT INTO universities (id_u, name_u)
VALUES (8, 'Abdelmalek Essaadi University');
INSERT INTO universities (id_u, name_u)
VALUES (9, 'Cadi Ayyad University');
INSERT INTO universities (id_u, name_u)
VALUES (10, 'Chouaib Doukkali University');
INSERT INTO universities (id_u, name_u)
VALUES (11, 'Hassan II University');
INSERT INTO universities (id_u, name_u)
VALUES (12, 'Ibn Zohr University');
INSERT INTO universities (id_u, name_u)
VALUES (13, 'Mohamed Premier University');
INSERT INTO universities (id_u, name_u)
VALUES (14, 'Mohammed V University');
INSERT INTO universities (id_u, name_u)
VALUES (15, 'Moulay Ismail University');
INSERT INTO universities (id_u, name_u)
VALUES (16, 'Sidi Mohamed Ben Abdellah University');
INSERT INTO universities (id_u, name_u)
VALUES (17, 'Moulay Slimane University');

SELECT *
FROM administrators
WHERE email_a = 'admin@my-grant.ma';



UPDATE students
set email_s = cne_s || '@taalim.ma';
