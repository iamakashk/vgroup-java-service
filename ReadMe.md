1. Creating databases for
CREATE DATABASE vgroup-dev;
CREATE DATABASE vgroup-test;


CREATE TABLE user_inputs (
    ID int NOT NULL AUTO_INCREMENT,
    input_value varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);
CREATE TABLE input_combinations (
    comb_id int NOT NULL AUTO_INCREMENT,
    input_combination varchar(255) NOT NULL,
    input_id int,
    PRIMARY KEY (comb_id),
    FOREIGN KEY (input_id) REFERENCES user_inputs(ID)
);

CREATE TABLE input_permutations (
    PERM_ID int NOT NULL AUTO_INCREMENT,
    input_combination int NOT NULL,
    input_id int,
    PRIMARY KEY (PERM_ID),
    FOREIGN KEY (input_id) REFERENCES user_inputs(ID)
);

To run on test please set in  ( Right click on project > Run as > Run Configuration > Arguments > VM Arguments )
-Dspring.profiles.active=test

To run on test please set in  ( Right click on project > Run as > Run Configuration > Arguments > VM Arguments )
-Dspring.profiles.active=dev

ADMIN USER: 
username: admin
password: password

Normal User: 
usrname: user
password: password
