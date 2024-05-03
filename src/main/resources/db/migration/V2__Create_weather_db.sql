CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE weather_info
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    temperature DOUBLE PRECISION NOT NULL,
    feels_like DOUBLE PRECISION NOT NULL,
    curr_date TIMESTAMPTZ NOT NULL,
    city VARCHAR(255) NOT NULL
);