DROP TABLE IF EXISTS app_user_expenses;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS app_user_user_profile;
DROP TABLE IF EXISTS user_profile;
DROP TABLE IF EXISTS app_user;

/*All User's gets stored in APP_USER table*/
create table app_user (
   id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
   username VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(100) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (username)
);
   
/* USER_PROFILE table contains all possible roles */ 
create table user_profile(
   id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
   
/* Populate USER_PROFILE Table */
INSERT INTO user_profile(type)
VALUES ('USER');
  
INSERT INTO user_profile(type)
VALUES ('ADMIN');

INSERT INTO user_profile(type)
VALUES ('DBA');


/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE app_user_user_profile (
    user_id BIGINT UNSIGNED NOT NULL,
    user_profile_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT fk_app_user_user_profile_app_user_user_id FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_app_user_user_profile_user_profile_user_profile_id FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);


/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

CREATE TABLE app_user_expenses (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    purpose TINYTEXT NOT NULL,
    expense DECIMAL(10,2) NOT NULL,
    exp_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    KEY user_id (user_id),
    CONSTRAINT fk_app_user_expenses_app_user_user_id FOREIGN KEY (user_id) REFERENCES app_user (id)
); 
    
