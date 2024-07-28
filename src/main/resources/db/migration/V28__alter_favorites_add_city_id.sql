ALTER TABLE favorites
    ADD COLUMN city_id INTEGER;

ALTER TABLE favorites
    ADD CONSTRAINT fk_city
        FOREIGN KEY (city_id) REFERENCES cities(id);
