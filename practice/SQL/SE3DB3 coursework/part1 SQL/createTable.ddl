connect to se3db3;

--++++++++++++++++++++++++++++++++++++++++++++++
-- CREATE TABLES
--++++++++++++++++++++++++++++++++++++++++++++++
------------------------------------------------
-- 1 DDL Statements for table soccer_country
------------------------------------------------
CREATE TABLE soccer_country (
    country_id integer NOT NULL,
    country_abbr varchar(4) NOT NULL,
    country_name varchar(40) NOT NULL,
    PRIMARY KEY (country_id)
);

------------------------------------------------
-- 2 DDL Statements for table soccer_city
------------------------------------------------
CREATE TABLE soccer_city (
    city_id integer NOT NULL,
    city varchar(25) NOT NULL,
    country_id integer NOT NULL,
    PRIMARY KEY (city_id),
    FOREIGN KEY (country_id) REFERENCES soccer_country(country_id) on delete cascade
);

------------------------------------------------
-- 3 DDL Statements for table soccer_venue
------------------------------------------------
CREATE TABLE soccer_venue (
    venue_id integer NOT NULL,
    venue_name varchar(30) NOT NULL,
    city_id integer NOT NULL,
    aud_capacity integer NOT NULL,
    PRIMARY KEY (venue_id),
    FOREIGN KEY (city_id) REFERENCES soccer_city(city_id) on delete cascade
);

------------------------------------------------
-- 4 DDL Statements for table soccer_team
------------------------------------------------
CREATE TABLE soccer_team (
    team_id integer NOT NULL,
    country_id integer NOT NULL,
    team_group char(1) NOT NULL,
    match_played integer NOT NULL,
    won integer NOT NULL,
    draw integer NOT NULL,
    lost integer NOT NULL,
    goal_for integer NOT NULL,
    goal_agnst integer NOT NULL,
    goal_diff integer NOT NULL,
    points integer NOT NULL,
    group_position integer NOT NULL,
    PRIMARY KEy (team_id),
    FOREIGN KEY (country_id) REFERENCES soccer_country(country_id) on delete cascade
);

------------------------------------------------
-- 5 DDL Statements for table playing_position
------------------------------------------------
CREATE TABLE playing_position (
    position_id char(2) NOT NULL,
    position_desc varchar(15) NOT NULL,
    PRIMARY KEY (position_id)
);

------------------------------------------------
-- 6 DDL Statements for table player_mast
------------------------------------------------
CREATE TABLE player_mast (
    player_id integer NOT NULL,
    team_id integer NOT NULL,
    jersey_no integer NOT NULL,
    player_name varchar(40) NOT NULL,
    posi_to_play char(2) NOT NULL,
    dt_of_bir date,
    age integer,
    playing_club varchar(40),
    PRIMARY KEY (player_id),
    FOREIGN KEY (posi_to_play) REFERENCES playing_position(position_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);

------------------------------------------------
-- 7 DDL Statements for table referee_mast
------------------------------------------------
CREATE TABLE referee_mast (
    referee_id integer NOT NULL,
    referee_name varchar(40) NOT NULL,
    country_id integer NOT NULL,
    PRIMARY KEY (referee_id),
    FOREIGN KEY (country_id) REFERENCES soccer_country(country_id) on delete cascade
);

------------------------------------------------
-- 8 DDL Statements for table match_mast
------------------------------------------------
CREATE TABLE match_mast (
    match_no integer NOT NULL,
    play_stage char(1) NOT NULL,
    play_date date NOT NULL,
    results char(5) NOT NULL,
    decided_by char(1) NOT NULL,
    goal_score char(5) NOT NULL,
    venue_id integer NOT NULL,
    referee_id integer NOT NULL,
    audence integer NOT NULL,
    plr_of_match integer NOT NULL,
    stop1_sec integer NOT NULL,
    stop2_sec integer NOT NULL,
    PRIMARY KEY (match_no),
    FOREIGN KEY (plr_of_match) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (referee_id) REFERENCES referee_mast(referee_id) on delete cascade,
    FOREIGN KEY (venue_id) REFERENCES soccer_venue(venue_id) on delete cascade
    
);

------------------------------------------------
-- 9 DDL Statements for table coach_mast
------------------------------------------------
CREATE TABLE coach_mast (
    coach_id integer NOT NULL,
    coach_name varchar(40) NOT NULL,
    PRIMARY KEY (coach_id)
);

------------------------------------------------
-- 10 DDL Statements for table asst_referee_mast
------------------------------------------------
CREATE TABLE asst_referee_mast (
    ass_ref_id integer NOT NULL,
    ass_ref_name varchar(40) NOT NULL,
    country_id integer NOT NULL,
    PRIMARY KEY(ass_ref_id),
    FOREIGN KEY (country_id) REFERENCES soccer_country(country_id) on delete cascade
);

------------------------------------------------
-- 11 DDL Statements for table match_details
------------------------------------------------
CREATE TABLE match_details (
    match_no integer NOT NULL,
    play_stage char(1) NOT NULL,
    team_id integer NOT NULL,
    win_lose char(1) NOT NULL,
    decided_by char(1) NOT NULL,
    goal_score integer NOT NULL,
    penalty_score integer,
    ass_ref integer NOT NULL,
    player_gk integer NOT NULL,
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (ass_ref) REFERENCES asst_referee_mast(ass_ref_id) on delete cascade,
    FOREIGN KEY (player_gk) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);

------------------------------------------------
-- 12 DDL Statements for table goal_details
------------------------------------------------
CREATE TABLE goal_details (
    goal_id integer NOT NULL,
    match_no integer NOT NULL,
    player_id integer NOT NULL,
    team_id integer NOT NULL,
    goal_time integer NOT NULL,
    goal_type char(1) NOT NULL,
    play_stage char(1) NOT NULL,
    goal_schedule char(2) NOT NULL,
    goal_half integer,
    PRIMARY KEY(goal_id),
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (player_id) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);

------------------------------------------------
-- 13 DDL Statements for table penalty_shootout
------------------------------------------------
CREATE TABLE penalty_shootout (
    kick_id integer NOT NULL,
    match_no integer NOT NULL,
    team_id integer NOT NULL,
    player_id integer NOT NULL,
    score_goal char(1) NOT NULL,
    kick_no integer NOT NULL,
    PRIMARY KEY (kick_id),
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (player_id) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);

------------------------------------------------
-- 14 DDL Statements for table player_booked
------------------------------------------------
CREATE TABLE player_booked (
    match_no integer NOT NULL,
    team_id integer NOT NULL,
    player_id integer NOT NULL,
    booking_time integer NOT NULL,
    sent_off char(1) DEFAULT NULL,
    play_schedule char(2) NOT NULL,
    play_half integer NOT NULL,
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (player_id) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);

------------------------------------------------
-- 15 DDL Statements for table player_in_out
------------------------------------------------
CREATE TABLE player_in_out (
    match_no integer NOT NULL,
    team_id integer NOT NULL,
    player_id integer NOT NULL,
    in_out char(1) NOT NULL,
    time_in_out integer NOT NULL,
    play_schedule char(2) NOT NULL,
    play_half integer NOT NULL,
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (player_id) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);

------------------------------------------------
-- 16 DDL Statements for table match_captain
------------------------------------------------
CREATE TABLE match_captain (
    match_no integer NOT NULL,
    team_id integer NOT NULL,
    player_captain integer NOT NULL,
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (player_captain) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id)on delete cascade
);

------------------------------------------------
-- 17 DDL Statements for table team_coaches
------------------------------------------------
CREATE TABLE team_coaches (
    team_id integer NOT NULL,
    coach_id integer NOT NULL,
    FOREIGN KEY (coach_id) REFERENCES coach_mast(coach_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade 
);

------------------------------------------------
-- 18 DDL Statements for table penalty_gk
------------------------------------------------
CREATE TABLE penalty_gk (
    match_no integer NOT NULL,
    team_id integer NOT NULL,
    player_gk integer NOT NULL,
    FOREIGN KEY (match_no) REFERENCES match_mast(match_no) on delete cascade,
    FOREIGN KEY (player_gk) REFERENCES player_mast(player_id) on delete cascade,
    FOREIGN KEY (team_id) REFERENCES soccer_team(team_id) on delete cascade
);
TERMINATE;

