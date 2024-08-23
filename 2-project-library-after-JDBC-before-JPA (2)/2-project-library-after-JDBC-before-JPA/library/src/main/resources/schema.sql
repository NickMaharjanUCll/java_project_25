DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS publications;
drop table if Exists membership;
Drop TABLE IF EXISTS loan;
drop table if exists loan_publications;


CREATE TABLE users(
    id BIGINT  PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    age int,
    email varchar(255),
    password varchar(255),
    profile_id VARCHAR(255),
    foreign key (profile_id) references users
);

CREATE TABLE profile(
    id BIGINT primary key AUTO_INCREMENT,
    bio VARCHAR(255),
    location varchar(255),
    interest varchar(255)
);

CREATE TABLE publications(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    title varchar(255),
    publication_year int,
    available_copies int,
    author varchar(255),
    ISBN varchar(20),
    editor varchar(255),
    ISSN varchar(255),
    type varchar(10)
);




CREATE TABLE membership(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    start_date DATE,  -- Changed to match @Column annotation
    end_date DATE,    -- Changed to match @Column annotation
    type VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY(user_id) REFERENCES users
);

create table loan(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    start_date Date,
    end_date Date,
    foreign key (user_id) references users
);

create table loan_publications(
    loan_id BIGINT,  -- Mistake: Incorrect use of PRIMARY KEY
    publication_id BIGINT,  -- Mistake: Incorrect use of PRIMARY KEY
    PRIMARY KEY(loan_id,publication_id),  -- Mistake: Redundant PRIMARY KEY declaration
    foreign key(loan_id) references loan(id),
    foreign key(publication_id) references publications(id)
);


-- INSERT INTO users (name, age, email, password) VALUES ('John Doe', 25, 'john.doe@ucll.be', 'john1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Jane Toe', 30, 'jane.toe@ucll.be', 'jane1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Jack Doe', 5, 'jack.doe@ucll.be', 'jack1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Sarah Doe', 4, 'sarah.doe@ucll.be', 'sarah1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Birgit Doe', 18, 'birgit.doe@ucll.be', 'birgit1234');