create database loja;

INSERT INTO marca (id, nome) VALUES (1, 'Bierbaum'),(2, 'Cerveja Blumenau'),(3, 'Polvo Loco'),(4, 'Van der Haus'),(5, 'Monasto');

INSERT INTO produto (id, nome, descricao, valor, classificacao, imagem, id_marca) VALUES 
(6,'Bierbaum Weiss 600ml','Cerveja clara de trigo de alta fermentação.',25.50,3,'bierbaum-weiss.jpg',1),
(7,'Bierbaum Vienna 600ml','Cerveja do estilo Vienna Lager, de baixa fermentação, com sabor suave e levemente adocicado.',25.00,4,'bierbaum-vienna.jpg',1),
(8,'Macalé com Café 310ml','A queridíssima Extra Stout da Polvo Loco',15.00,2,'macale-cafe.jpg',3),
(9,'Blumenau Pé de Moleque 500ml','A cerveja Blumenau Macuca Pé de Moleque é uma cerveja brasileira e artesanal.',22.00,5,'blumenau-moleque.jpg',2),
(10,'Gin Van der Haus London Dry 700ml','O Gin Van Der Haus é produzido cuidadosamente com botânicos selecionados.',125.00,5,'gin-van.jpg',4),
(11,'Azeite Monasto 100ml','Este azeite foi preparado com um blend exclusivo com variedades de olivais de alberquinas.',35.00,4,'azeita-monasto.jpg',5);