create table if not exists Spel (
id int auto_increment,
constraint PK_Spel primary key(id)
);

create table if not exists Steen (
id int auto_increment,
getal int, 
kleur varchar(255), 
isJoker boolean,
spelId int, 
gebruikersnaam varchar(255),
constraint PK_Steen primary key(id),
constraint FK_Steen_Spel foreign key(spelId) references Spel(id),
constraint FK_Steen_Speler foreign key(gebruikersnaam) references Speler(gebruikersnaam)
);

/* Waarden in steen toevoegen */  
insert into Steen (isJoker) values
(true),
(true);

insert into Steen (getal, kleur, isJoker) Values 
(1, 'rood', false),
(1, 'geel', false),
(1, 'blauw', false),
(1, 'zwart', false),
(2, 'rood', false),
(2, 'geel', false),
(2, 'blauw', false),
(2, 'zwart', false),
(3, 'rood', false),
(3, 'geel', false),
(3, 'blauw', false),
(3, 'zwart', false),
(4, 'rood', false),
(4, 'geel', false),
(4, 'blauw', false),
(4, 'zwart', false),
(5, 'rood', false),
(5, 'geel', false),
(5, 'blauw', false),
(5, 'zwart', false),
(6, 'rood', false),
(6, 'geel', false),
(6, 'blauw', false),
(6, 'zwart', false),
(7, 'rood', false),
(7, 'geel', false),
(7, 'blauw', false),
(7, 'zwart', false),
(8, 'rood', false),
(8, 'geel', false),
(8, 'blauw', false),
(8, 'zwart', false),
(9, 'rood', false),
(9, 'geel', false),
(9, 'blauw', false),
(9, 'zwart', false),
(10, 'rood', false),
(10, 'geel', false),
(10, 'blauw', false),
(10, 'zwart', false),
(11, 'rood', false),
(11, 'geel', false),
(11, 'blauw', false),
(11, 'zwart', false),
(12, 'rood', false),
(12, 'geel', false),
(12, 'blauw', false),
(12, 'zwart', false),
(13, 'rood', false),
(13, 'geel', false),
(13, 'blauw', false),
(13, 'zwart', false),
(1, 'rood', false),
(1, 'geel', false),
(1, 'blauw', false),
(1, 'zwart', false),
(2, 'rood', false),
(2, 'geel', false),
(2, 'blauw', false),
(2, 'zwart', false),
(3, 'rood', false),
(3, 'geel', false),
(3, 'blauw', false),
(3, 'zwart', false),
(4, 'rood', false),
(4, 'geel', false),
(4, 'blauw', false),
(4, 'zwart', false),
(5, 'rood', false),
(5, 'geel', false),
(5, 'blauw', false),
(5, 'zwart', false),
(6, 'rood', false),
(6, 'geel', false),
(6, 'blauw', false),
(6, 'zwart', false),
(7, 'rood', false),
(7, 'geel', false),
(7, 'blauw', false),
(7, 'zwart', false),
(8, 'rood', false),
(8, 'geel', false),
(8, 'blauw', false),
(8, 'zwart', false),
(9, 'rood', false),
(9, 'geel', false),
(9, 'blauw', false),
(9, 'zwart', false),
(10, 'rood', false),
(10, 'geel', false),
(10, 'blauw', false),
(10, 'zwart', false),
(11, 'rood', false),
(11, 'geel', false),
(11, 'blauw', false),
(11, 'zwart', false),
(12, 'rood', false),
(12, 'geel', false),
(12, 'blauw', false),
(12, 'zwart', false),
(13, 'rood', false),
(13, 'geel', false),
(13, 'blauw', false),
(13, 'zwart', false);

select count(id)
from Steen
where isJoker = 1;

