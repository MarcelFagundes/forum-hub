CREATE TABLE response (

    id BIGINT PRIMARY KEY,
    message TEXT NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    author INT NOT NULL,
    topics INT NOT NULL,
    solution BOOLEAN DEFAULT FALSE
);