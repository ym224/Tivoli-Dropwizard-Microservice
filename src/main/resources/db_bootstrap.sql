drop table event_note;
drop table agenda;
drop table event_comment;
drop table event_user;
drop table event_user_tag;
drop table event;
drop table cal_user;

create table IF NOT EXISTS cal_user(
  id SERIAL PRIMARY KEY,
  first_name varchar(255),
  last_name VARCHAR(255),
  gender VARCHAR(255),
  age integer,
  email VARCHAR(255) not null
);


create table IF NOT EXISTS event (
  id SERIAL PRIMARY KEY,
  gcal_id VARCHAR(2000) not null,
  title VARCHAR(2000) not null,
  description VARCHAR(2000),
  status VARCHAR(255),
  creator_email VARCHAR(255),
  video_link VARCHAR(255),
  location VARCHAR(255),
  start_time TIMESTAMP not null,
  end_time TIMESTAMP not null,
  created_time TIMESTAMP,
  last_updated TIMESTAMP
);

create table IF NOT EXISTS event_user (
  id SERIAL PRIMARY KEY,
  event_id BIGINT REFERENCES event(id),
  user_id BIGINT REFERENCES cal_user(id),
  last_updated TIMESTAMP
);

create table IF NOT EXISTS event_user_tag (
  id SERIAL PRIMARY KEY,
  event_id BIGINT REFERENCES event(id),
  user_id BIGINT REFERENCES cal_user(id),
  tag VARCHAR(255) NOT NULL,
  last_updated TIMESTAMP
);

create table IF NOT EXISTS agenda (
  id SERIAL PRIMARY KEY,
  event_id BIGINT REFERENCES event(id),
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  creator_id BIGINT REFERENCES cal_user(id),
  duration BIGINT,
  last_updated TIMESTAMP
);

create table IF NOT EXISTS event_note (
  id SERIAL PRIMARY KEY,
  event_id BIGINT REFERENCES event(id),
  agenda_id BIGINT REFERENCES agenda(id),
  rank BIGINT,
  note VARCHAR(750) NOT NULL,
  is_key_decision BOOLEAN,
  is_private BOOLEAN,
  assignee_id BIGINT REFERENCES cal_user(id),
  creator_id BIGINT REFERENCES cal_user(id),
  last_updated TIMESTAMP
);

create table IF NOT EXISTS event_comment (
  id SERIAL PRIMARY KEY,
  event_id BIGINT REFERENCES event(id),
  creator_id BIGINT REFERENCES cal_user(id),
  comment VARCHAR(255) NOT NULL,
  last_updated TIMESTAMP
);