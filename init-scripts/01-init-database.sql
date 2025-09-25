-- Initialize Questions Database
-- This script runs when PostgreSQL container starts up

-- Use the questions database
\c
questions_db;

-- Create extensions if needed
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tables will be created by Hibernate DDL auto-update when the Spring Boot app starts
-- Data initialization will be handled by the DbInitializer class in the Spring Boot app

-- Grant permissions to the questions_user for future tables
GRANT ALL PRIVILEGES ON SCHEMA
public TO questions_user;
GRANT ALL PRIVILEGES ON ALL
TABLES IN SCHEMA public TO questions_user;
GRANT ALL PRIVILEGES ON ALL
SEQUENCES IN SCHEMA public TO questions_user;

-- Grant default privileges for future objects
ALTER
DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO questions_user;
ALTER
DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO questions_user;