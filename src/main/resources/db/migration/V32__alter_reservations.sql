ALTER TABLE reservations
    ADD COLUMN user_id INTEGER;

ALTER TABLE reservations
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES users(id);
