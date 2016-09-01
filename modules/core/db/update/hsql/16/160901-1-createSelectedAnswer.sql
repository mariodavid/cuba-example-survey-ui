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
);
