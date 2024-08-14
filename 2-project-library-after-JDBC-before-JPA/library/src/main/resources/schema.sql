DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id long AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    age int,
    email varchar(255),
    password varchar(255)
);

-- INSERT INTO users (name, age, email, password) VALUES ('John Doe', 25, 'john.doe@ucll.be', 'john1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Jane Toe', 30, 'jane.toe@ucll.be', 'jane1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Jack Doe', 5, 'jack.doe@ucll.be', 'jack1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Sarah Doe', 4, 'sarah.doe@ucll.be', 'sarah1234');
-- INSERT INTO users (name, age, email, password) VALUES ('Birgit Doe', 18, 'birgit.doe@ucll.be', 'birgit1234');