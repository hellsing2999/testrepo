INSERT INTO Compte(numero,label,solde,type_compte) VALUES (1,'compte 1',50.0,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte) VALUES (2,'compte 2',80.0,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte) VALUES (3,'compte 3',100.0,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte,taux) VALUES (4,'compte 4',200.50,'ComptePel',1.5);
INSERT INTO Compte(numero,label,solde,type_compte) VALUES (5,'compte 5',350.147,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte) VALUES (6,'compte 6',3500,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte) VALUES (7,'compte 7',85697.1,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte) VALUES (8,'compte 8',623.47,'CompteCourant');
INSERT INTO Compte(numero,label,solde,type_compte,taux) VALUES (9,'compte 9',623.47,'ComptePel',1.1);

INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 1',-120,'2018-09-07',1);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 2',-25,'2018-05-06',1);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 478',-257,'2018-04-02',2);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 14',-78,'2018-01-06',2);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 1569',-100,'2018-06-18',3);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 123',-2005,'2018-07-16',3);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 789',-2895,'2018-03-26',3);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 496',-2415,'2018-03-28',3);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 41',-725,'2018-02-12',6);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 54',-125,'2018-06-06',6);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 96',-6625,'2018-04-06',7);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 36',-825,'2018-09-01',7);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 2741',-69,'2018-05-20',7);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 1478',-65,'2018-01-21',4);
INSERT INTO Operation(label,montant,dateOp,id_compte) VALUES ('achat 693',-285,'2018-04-22',4);

INSERT INTO Client(numero,prenom,nom) VALUES (1,'prenom 1','nom 1');
INSERT INTO Client(numero,prenom,nom) VALUES (2,'prenom 2','nom 2');

INSERT INTO client_compte(id_Client,id_Compte) VALUES (1,1);
INSERT INTO client_compte(id_Client,id_Compte) VALUES (1,2);
INSERT INTO client_compte(id_Client,id_Compte) VALUES (2,3);
INSERT INTO client_compte(id_Client,id_Compte) VALUES (2,4);
