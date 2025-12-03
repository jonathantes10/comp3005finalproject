INSERT INTO Member (first_name, last_name, email, date_of_birth, phone_number) VALUES
('John', 'Doe', 'john.doe@example.com', '1999-01-01', '613-123-4567'),
('Jane', 'Smith', 'jane.smith@example.com', '1999-01-02', '613-123-1234'),
('James', 'Swish', 'james.swish@example.com', '1999-02-02', '613-122-2234');

INSERT INTO Trainer (first_name, last_name, email) VALUES
('Jim', 'Beam', 'jim.beam@example.com'),
('Ab', 'Nehemiah', 'ab.nehemiah@example.com'),
('Solo', 'Beans', 'solo.beans@example.com'),
('Jon', 'Test', 'jon.test@example.com');

INSERT INTO AdminStaff (first_name, last_name, email) VALUES
('Troy', 'Daniels', 'troy.daniels@example.com'),
('Mark', 'Two', 'mark.peteres@example.com'),
('Matthew', 'Parker', 'matthew.parker@example.com');

INSERT INTO Room (name, capacity) VALUES
('Room 1', 2),
('Room 2', 2),
('Room 3', 3);

INSERT INTO HealthMetric (member_id, height_inches, weight_lbs, heart_rate_bpm) VALUES
(1, 70.0, 180.0, 70),
(2, 76.0, 195.0, 88),
(3, 68.0, 160.0, 80);


INSERT INTO PersonalTrainingSession (member_id, trainer_id, room_id, admin_id, start_time, end_time, status) VALUES
(1, 1, 1, 1, '2026-01-01 10:00', '2026-01-01 11:00', 'Scheduled'),
(2, 2, 2, 1, '2026-01-02 12:00', '2026-01-02 13:00', 'Scheduled');

