/* Aanmaken van de databank */
create table if not exists Speler (
gebruikersnaam varchar(255),
wachtwoord varchar(255),
constraint PK_Speler primary key(Gebruikersnaam)
);

/* Aanmaken van de Spelers */
insert into Speler values 
('Gilles', '123'),
('Tibo', '123'),
('Oliver', '123'),
('Aron', '123'),
('Mario', '123'),
('Tom', '123'),
('Siska', '123'),
('Louise', '123'),
('Alien', '123'),
('Luna', '123'),
('Natasha', '123');

/* score aan tabel Speler toevoegen */
alter table Speler
add score int;

/*score 0 aan spelers toevoegen*/
update Speler set score = 0;

/* Spelers tonen */
select *
from Speler;

