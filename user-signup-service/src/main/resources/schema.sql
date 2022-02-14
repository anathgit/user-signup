DROP TABLE IF EXISTS USER_ADDRESS;
DROP TABLE IF EXISTS USERS;
CREATE TABLE IF NOT EXISTS USERS (
    id              UUID            DEFAULT random_uuid() PRIMARY KEY,
    mail_id          VARCHAR(255)  NOT NULL,
    registered_at     TIMESTAMP       NOT NULL,
    first_name       VARCHAR(255)    NOT NULL,
    last_name        VARCHAR(255)  NOT NULL,
    insta_uname      VARCHAR(255)  NOT NULL,
    twitter_uname    VARCHAR(255)  NOT NULL,
    development_env  VARCHAR(255)    NOT NULL
);

CREATE TABLE IF NOT EXISTS USER_ADDRESS (
    id              UUID            DEFAULT random_uuid() PRIMARY KEY,
    city          VARCHAR(255)  NOT NULL,
    state  VARCHAR(255)    NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);