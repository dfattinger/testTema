Drop table if exists TIPOS;
Create table TIPOS(
 id_tipo int auto_increment primary key,
 nom_tipo varchar2(1000));

Drop table if exists CLASSES;
Create table CLASSES(
 id_classe int auto_increment primary key,
 nom_classe varchar2(1000));

Drop table if exists CARTAS;
Create table CARTAS(
 id_carta int auto_increment primary key,
 nom_carta varchar2(1000) not null,
 dsc_carta varchar2(1000) not null,
 val_ataque int not null,
 val_defesa int not null,
 id_tipo int not null,
 id_classe int not null,
 constraint car_ataque_chk check(val_ataque between 0 and 10),
 constraint car_defesa_chk check(val_defesa between 0 and 10),
 constraint car_tip_fk foreign key (id_tipo) references TIPOS(id_tipo),
 constraint car_cla_fk foreign key (id_classe) references CLASSES(id_classe));

Drop table if exists BARALHOS;
Create table BARALHOS(
 id_baralho int auto_increment primary key,
 nom_baralho varchar2(1000) not null);

Drop table if exists BARALHOS_CARTAS;
Create table BARALHOS_CARTAS(
 id_baralho_carta int auto_increment primary key,
 id_baralho int not null,
 id_carta int not null,
 constraint bar_car_bar_fk foreign key (id_baralho) references BARALHOS(id_baralho),
 constraint bar_car_car_fk foreign key (id_carta) references CARTAS(id_carta));
