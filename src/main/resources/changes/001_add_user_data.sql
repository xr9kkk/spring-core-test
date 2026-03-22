CREATE TABLE profile(
    id UUID primary key,
    login TEXT NOT NULL UNIQUE,
    balance numeric
);
