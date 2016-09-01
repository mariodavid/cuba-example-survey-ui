-- begin SURVEY_QUESTION
create table SURVEY_QUESTION (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    QUESTION_TEXT varchar(255),
    --
    primary key (ID)
)^
-- end SURVEY_QUESTION
-- begin SURVEY_POSSIBLE_ANSWER
create table SURVEY_POSSIBLE_ANSWER (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(255),
    ANSWER_TEXT varchar(255),
    QUESTION_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end SURVEY_POSSIBLE_ANSWER
-- begin SURVEY_SURVEY
create table SURVEY_SURVEY (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255) not null,
    --
    primary key (ID)
)^
-- end SURVEY_SURVEY
-- begin SURVEY_FILLED_SURVEY
create table SURVEY_FILLED_SURVEY (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SURVEY_ID varchar(36) not null,
    FILLED_DATE date not null,
    USER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end SURVEY_FILLED_SURVEY

-- begin SURVEY_SURVEY_QUESTION_LINK
create table SURVEY_SURVEY_QUESTION_LINK (
    SURVEY_ID varchar(36) not null,
    QUESTION_ID varchar(36) not null,
    primary key (SURVEY_ID, QUESTION_ID)
)^
-- end SURVEY_SURVEY_QUESTION_LINK
-- begin SURVEY_SELECTED_ANSWER
create table SURVEY_SELECTED_ANSWER (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    QUESTION_ID varchar(36) not null,
    ANSWER_ID varchar(36) not null,
    FILLED_SURVEY_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end SURVEY_SELECTED_ANSWER
