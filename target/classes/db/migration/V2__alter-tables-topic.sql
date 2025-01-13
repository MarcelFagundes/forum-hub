ALTER TABLE topics
    ADD COLUMN fk_user BIGINT NULL,
    ADD CONSTRAINT fk_user_topics FOREIGN KEY (fk_user) REFERENCES user(id);