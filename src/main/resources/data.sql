insert into TIPOS (nom_tipo) values('Magia' );
insert into TIPOS (nom_tipo) values('Criatura' );
insert into CLASSES (nom_classe) values('Mago' );
insert into CLASSES (nom_classe) values('Paladino' );
insert into CLASSES (nom_classe) values('Caçador' );
insert into CLASSES (nom_classe) values('Druida' );
insert into CLASSES (nom_classe) values('Qualquer' );
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 1', 'Descrição 1', 1, 9, 1, 1);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 2', 'Descrição 2', 2, 9, 2, 1);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 3', 'Descrição 3', 4, 5, 2, 3);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 4', 'Descrição 4', 4, 5, 2, 4);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 5', 'Descrição 5', 4, 5, 2, 5);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 6', 'Descrição 6', 4, 5, 2, 2);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 7', 'Descrição 7', 4, 5, 2, 1);
insert into CARTAS( nom_carta,dsc_carta,val_ataque,val_defesa,id_tipo,id_classe) values
 ('Carta 8', 'Descrição 8', 4, 5, 2, 5);
insert into BARALHOS(nom_baralho) values('Baralho 1');
insert into BARALHOS_CARTAS(id_baralho, id_carta) values(1,1);
insert into BARALHOS_CARTAS(id_baralho, id_carta) values(1,2);
insert into BARALHOS(nom_baralho) values('Baralho 2');
insert into BARALHOS_CARTAS(id_baralho, id_carta) values(2,1);
insert into BARALHOS_CARTAS(id_baralho, id_carta) values(2,2);