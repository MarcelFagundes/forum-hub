ALTER TABLE response
ADD CONSTRAINT fk_response_author
FOREIGN KEY (author) REFERENCES user (id);