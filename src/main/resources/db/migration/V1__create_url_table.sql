CREATE TABLE urls (
    id UUID PRIMARY KEY default gen_random_uuid(),
    url VARCHAR(255) NOT NULL,
    short_code VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);