DROP TABLE IF EXISTS PersonalTrainingSession CASCADE;
DROP TABLE IF EXISTS HealthMetric CASCADE;
DROP TABLE IF EXISTS Room CASCADE;
DROP TABLE IF EXISTS AdminStaff CASCADE;
DROP TABLE IF EXISTS Trainer CASCADE;
DROP TABLE IF EXISTS Member CASCADE;

CREATE TABLE Member (
    member_id      SERIAL PRIMARY KEY,
    first_name     TEXT NOT NULL,
    last_name      TEXT NOT NULL,
    email          TEXT NOT NULL UNIQUE,
    date_of_birth  DATE NOT NULL,
    phone_number   TEXT
);

CREATE TABLE Trainer (
    trainer_id     SERIAL PRIMARY KEY,
    first_name     TEXT NOT NULL,
    last_name      TEXT NOT NULL,
    email          TEXT NOT NULL UNIQUE
);

CREATE TABLE AdminStaff (
    admin_id       SERIAL PRIMARY KEY,
    first_name     TEXT NOT NULL,
    last_name      TEXT NOT NULL,
    email          TEXT NOT NULL UNIQUE
);

CREATE TABLE Room (
    room_id        SERIAL PRIMARY KEY,
    name           TEXT NOT NULL,
    capacity       INT NOT NULL CHECK (capacity > 0)
);

CREATE TABLE HealthMetric (
    metric_id      SERIAL PRIMARY KEY,
    member_id      INT NOT NULL REFERENCES Member(member_id) ON DELETE CASCADE,
    recorded_at    TIMESTAMP NOT NULL DEFAULT NOW(),
    height_inches      NUMERIC(5,2),
    weight_lbs      NUMERIC(5,2),
    heart_rate_bpm INT
);

CREATE TABLE PersonalTrainingSession (
    pt_session_id  SERIAL PRIMARY KEY,
    member_id      INT NOT NULL REFERENCES Member(member_id) ON DELETE CASCADE,
    trainer_id     INT NOT NULL REFERENCES Trainer(trainer_id) ON DELETE CASCADE,
    room_id        INT NOT NULL REFERENCES Room(room_id) ON DELETE RESTRICT,
    admin_id       INT REFERENCES AdminStaff(admin_id) ON DELETE SET NULL,
    start_time     TIMESTAMP NOT NULL,
    end_time       TIMESTAMP NOT NULL,
    status         TEXT NOT NULL DEFAULT 'Scheduled',
    CHECK (end_time > start_time),
    CHECK (status IN ('Scheduled', 'Completed', 'Cancelled'))
);
