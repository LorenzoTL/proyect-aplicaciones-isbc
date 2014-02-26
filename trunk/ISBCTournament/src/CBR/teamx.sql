drop table if exists teamdescription;
create table teamdescription(id INTEGER, goalKeeper INTEGER, defensa INTEGER, lateral2 INTEGER, lateral4 INTEGER, forward INTEGER, df INTEGER, remainingTime DOUBLE);
insert into teamdescription values(1,0,4,2,2,3,0,55000);
insert into teamdescription values(2,0,4,6,6,3,2,25000);
insert into teamdescription values(3,0,4,5,5,3,-1,15000);