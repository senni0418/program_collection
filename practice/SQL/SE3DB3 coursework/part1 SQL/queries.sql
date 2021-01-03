connect to SE3DB3;

---q1
select count(*)
from player_mast;

---q2 
select * 
from player_mast;

---q3
---i
select count(distinct c.country_id)
from soccer_country c, (select distinct country_id
from soccer_team t, player_mast player
where t.team_id = player.team_id
) p
where c.country_id = p.country_id;

---ii
select count(*)
from goal_details g
where g.goal_schedule = 'ET';

---iii
select count(*)
from goal_details g
where g.goal_schedule = 'ST';

---iv
select count(*)
from goal_details g
where g.goal_schedule = 'NT';

---v
select count(*)
from player_booked b
where b.play_schedule = 'ST';

---vi
select count(*)
from player_booked b
where b.play_schedule = 'ET';

---q4
---i
select min(m. play_date)
from match_mast m;

---ii
select max(m. play_date)
from match_mast m;

---q5
---i
select distinct c.country_name
from soccer_team t, soccer_country c, match_details d
where t.country_id = c.country_id and t.team_id = d.team_id and d.match_no = 1;

---ii
select distinct c.country_name
from soccer_team t, soccer_country c, match_mast m, match_details d
where t.country_id = c.country_id and t.team_id = d.team_id and m.match_no = d.match_no and d.win_lose = 'W' and m.play_date = (
select max(m. play_date)
from match_mast m
);

---q6
---1
select c.country_name, r.cou2 counts 
from soccer_country c, (select t.country_id, new.cou1 cou2 
from soccer_team t, (select team_id, count(*) cou1 
from penalty_shootout group by team_id) new 
where t.team_id = new.team_id) r 
where c.country_id = r.country_id;
---2
select c.country_name
from soccer_country c, (select t.country_id
from soccer_team t, (select d.team_id
from match_details d, (select m.match_no
from match_mast m
where m.play_date =  (select max(m. play_date)
from match_mast m)) m
where d.match_no = m.match_no) team
where t.team_id = team.team_id) country
where c.country_id = country.country_id;

---3
select m.play_date
from match_mast m
where m.match_no in (select distinct match_no
from penalty_shootout);

---4
select v.venue_name
from soccer_venue v, (select m.venue_id
from match_mast m
where m.match_no in (select distinct match_no
from penalty_shootout)) venue
where v.venue_id = venue.venue_id;

---5
select count(*) + 11 
from soccer_team t, player_in_out p
where p.match_no = (select max(match_no) 
from match_mast) and p.team_id = t.team_id and p.in_out = 'I' and t.country_id = (select c.country_id
from soccer_country c 
where c.country_name = 'France');


terminate;