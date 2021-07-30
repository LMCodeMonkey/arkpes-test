DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS Investors;
DROP TABLE IF EXISTS Funds;
DROP TABLE IF EXISTS Clients_Investors;

Create table Clients
(
  id              INT unsigned NOT NULL AUTO_INCREMENT,
  name            text NOT NULL,
  description     text NOT NULL,
  phone_number    text,
  type			  text NOT NULL,
  PRIMARY KEY     (id)
);

Create table Investors
(
  id              INT unsigned NOT NULL AUTO_INCREMENT,
  name            text NOT NULL,
  PRIMARY KEY     (id)
);

Create table Funds
(
  id              INT unsigned NOT NULL AUTO_INCREMENT,
  amount          double NOT NULL,
  investor_id     INT unsigned NOT NULL,
  PRIMARY KEY     (id)
);

Create table Clients_Investors
(
  client_id              INT unsigned NOT NULL,
  investor_id            INT unsigned NOT NULL
);

INSERT INTO Clients (name, description, phone_number, type) VALUES
('test 1 client', 'test 1 Description', '603', 'DOMESTIC'),
('test 2 client', 'test 2 Description', '603', 'DOMESTIC'),
('test 3 client', 'test 3 Description', '604', 'INTERNATIONAL');

INSERT INTO Investors (name) VALUES
('test investor 1'),
('test investor 2'),
('test investor 3');

INSERT INTO Funds (amount, investor_id) VALUES
(8.5, 1),
(4, 1),
(1000, 2),
(166, 3),
(11111, 2),
(8.5, 1);

INSERT INTO Clients_Investors (client_id, investor_id) VALUES
(1, 1),
(2, 2),
(2, 3);