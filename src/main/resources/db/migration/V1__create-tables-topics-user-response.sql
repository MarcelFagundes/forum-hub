CREATE TABLE user(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE response(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    fk_author BIGINT NULL,
    FOREIGN KEY (fk_author) REFERENCES user(id)
);

CREATE TABLE topics(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    topic_status VARCHAR(255),
    fk_response_solution BIGINT NULL,
    FOREIGN KEY (fk_response_solution) REFERENCES response(id)
);

ALTER TABLE response
    ADD COLUMN fk_topics BIGINT NOT NULL,
ADD CONSTRAINT fk_response_topics FOREIGN KEY (fk_topics) REFERENCES topics(id);