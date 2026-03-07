-- Create segments table
CREATE TABLE segments (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Insert initial segments
INSERT INTO segments (name) VALUES
('Tecnologia'),
('Comércio'),
('Indústria'),
('Serviços'),
('Agronegócio');