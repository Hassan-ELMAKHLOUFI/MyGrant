/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  13/05/42 10:35:36 ?                      */
/*==============================================================*/


drop table ADMINISTRATOR;

drop table ARCHIVE;

drop table BROTHER;

drop table ESTABLISHMENT;

drop table "GRANT";

drop table HAVE;

drop table LEGALGUARDIAN;

drop table MANAGE;

drop table STUDENT;

drop table UPDATE;

/*==============================================================*/
/* Table : ADMINISTRATOR                                        */
/*==============================================================*/
create table ADMINISTRATOR (
   ID_A                 NUMERIC              not null,
   FIRSTNAME_A          CHAR(1690)           null,
   LASTNAME_A           CHAR(160)            null,
   EMAIL_A              CHAR(160)            null,
   PHONENUMBER_A        CHAR(160)            null,
   PASSWORD_A           CHAR(160)            null,
   constraint PK_ADMINISTRATOR primary key (ID_A)
);

/*==============================================================*/
/* Table : ARCHIVE                                              */
/*==============================================================*/
create table ARCHIVE (
   ID_AR                NUMERIC              not null,
   LISTFILENAME_AR      CHAR(160)            null,
   YEAR_AR              DATE                 null,
   constraint PK_ARCHIVE primary key (ID_AR)
);

/*==============================================================*/
/* Table : BROTHER                                              */
/*==============================================================*/
create table BROTHER (
   ID_B                 NUMERIC              not null,
   FIRSTNAME_B          CHAR(160)            null,
   LASTNAME_B           CHAR(160)            null,
   BIRTHDATE_B          DATE                 null,
   ISSTUDYING           BOOL                 null,
   ISHANDICAPPED        BOOL                 null,
   constraint PK_BROTHER primary key (ID_B)
);

/*==============================================================*/
/* Table : ESTABLISHMENT                                        */
/*==============================================================*/
create table ESTABLISHMENT (
   ID_E                 NUMERIC              not null,
   NAME_E               CHAR(160)            null,
   CITY_E               CHAR(160)            null,
   ADRESS_E             CHAR(160)            null,
   constraint PK_ESTABLISHMENT primary key (ID_E)
);

/*==============================================================*/
/* Table : "GRANT"                                              */
/*==============================================================*/
create table "GRANT" (
   ID_G                 NUMERIC              not null,
   PRICE_G              FLOAT8               null,
   TYPE_G               CHAR(160)            null,
   constraint PK_GRANT primary key (ID_G)
);

/*==============================================================*/
/* Table : HAVE                                                 */
/*==============================================================*/
create table HAVE (
   ID_S                 NUMERIC              not null,
   ID_B                 NUMERIC              not null,
   constraint PK_HAVE primary key (ID_S, ID_B)
);

/*==============================================================*/
/* Table : LEGALGUARDIAN                                        */
/*==============================================================*/
create table LEGALGUARDIAN (
   ID_L                 NUMERIC              not null,
   FIRSTNAME_L          CHAR(160)            null,
   PHONENUMBER_L        CHAR(160)            null,
   ANNUALINCOME_L       CHAR(160)            null,
   constraint PK_LEGALGUARDIAN primary key (ID_L)
);

/*==============================================================*/
/* Table : MANAGE                                               */
/*==============================================================*/
create table MANAGE (
   ID_G                 NUMERIC              not null,
   ID_A                 NUMERIC              not null,
   constraint PK_MANAGE primary key (ID_G, ID_A)
);

/*==============================================================*/
/* Table : STUDENT                                              */
/*==============================================================*/
create table STUDENT (
   ID_S                 NUMERIC              not null,
   ID_E                 NUMERIC              not null,
   ID_G                 NUMERIC              null,
   ID_L                 NUMERIC              not null,
   FIRSTNAME_S          CHAR(160)            null,
   LASTNAME_S           CHAR(160)            null,
   BIRTHDATE_S          DATE                 null,
   CITY_S               CHAR(160)            null,
   PROVINCE_S           CHAR(160)            null,
   ADDRESS_S            CHAR(160)            null,
   CNE_S                CHAR(160)            null,
   CIN_S                CHAR(160)            null,
   SOCILASTATUS_S       CHAR(160)            null,
   HEALTHSTATUS_S       CHAR(160)            null,
   EMAIL_S              CHAR(160)            null,
   POFILEPHOTO_S        CHAR(160)            null,
   FULLNUMBER_S         NUMERIC              null,
   PASSWORD_S           CHAR(160)            null,
   constraint PK_STUDENT primary key (ID_S)
);

/*==============================================================*/
/* Table : UPDATE                                               */
/*==============================================================*/
create table UPDATE (
   ID_A                 NUMERIC              not null,
   ID_AR                NUMERIC              not null,
   constraint PK_UPDATE primary key (ID_A, ID_AR)
);

alter table HAVE
   add constraint FK_HAVE_HAVE_BROTHER foreign key (ID_B)
      references BROTHER (ID_B)
      on delete restrict on update restrict;

alter table HAVE
   add constraint FK_HAVE_HAVE2_STUDENT foreign key (ID_S)
      references STUDENT (ID_S)
      on delete restrict on update restrict;

alter table MANAGE
   add constraint FK_MANAGE_MANAGE_ADMINIST foreign key (ID_A)
      references ADMINISTRATOR (ID_A)
      on delete restrict on update restrict;

alter table MANAGE
   add constraint FK_MANAGE_MANAGE2_GRANT foreign key (ID_G)
      references "GRANT" (ID_G)
      on delete restrict on update restrict;

alter table STUDENT
   add constraint FK_STUDENT_BENEFIT_GRANT foreign key (ID_G)
      references "GRANT" (ID_G)
      on delete restrict on update restrict;

alter table STUDENT
   add constraint FK_STUDENT_REGISTER_ESTABLIS foreign key (ID_E)
      references ESTABLISHMENT (ID_E)
      on delete restrict on update restrict;

alter table STUDENT
   add constraint FK_STUDENT_TAKECARE_LEGALGUA foreign key (ID_L)
      references LEGALGUARDIAN (ID_L)
      on delete restrict on update restrict;

alter table UPDATE
   add constraint FK_UPDATE_UPDATE_ARCHIVE foreign key (ID_AR)
      references ARCHIVE (ID_AR)
      on delete restrict on update restrict;

alter table UPDATE
   add constraint FK_UPDATE_UPDATE2_ADMINIST foreign key (ID_A)
      references ADMINISTRATOR (ID_A)
      on delete restrict on update restrict;
