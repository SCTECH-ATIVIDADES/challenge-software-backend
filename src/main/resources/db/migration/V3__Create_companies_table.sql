-- Create companies table
CREATE TABLE companies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    responsible_name VARCHAR(255) NOT NULL,
    city_id BIGINT NOT NULL,
    email VARCHAR(255) NOT NULL,
    segment_id BIGINT NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities(id),
    FOREIGN KEY (segment_id) REFERENCES segments(id)
);