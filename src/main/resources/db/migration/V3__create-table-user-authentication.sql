CREATE TABLE user_authentication (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    profile_user VARCHAR(255) NOT NULL

);