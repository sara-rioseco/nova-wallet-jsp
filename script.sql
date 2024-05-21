START transaction;
SET SQL_SAFE_UPDATES = 0;

CREATE SCHEMA nova_wallet;
USE nova_wallet;

CREATE TABLE currencies(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
symbol CHAR(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(60) NOT NULL,
creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Create unique index on users' id and email
CREATE UNIQUE INDEX user_email ON users(id ASC, email);

CREATE TABLE accounts(
id INT PRIMARY KEY AUTO_INCREMENT,
owner_id INT NOT NULL,
currency_id INT NOT NULL,
balance INT NOT NULL DEFAULT 0,
creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(owner_id) REFERENCES users(id),
FOREIGN KEY(currency_id) REFERENCES currencies(id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Create unique index on accounts' id and owner_id
CREATE UNIQUE INDEX account_owner ON accounts(id ASC, owner_id);

CREATE TABLE contacts(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30),
email VARCHAR(50) NOT NULL,
contact_user_id INT NOT NULL,
owner_user_id INT NOT NULL,
creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(contact_user_id) REFERENCES users(id),
FOREIGN KEY(owner_user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Create unique index on contacts' id and owner_id
CREATE UNIQUE INDEX contact_owner ON contacts(id ASC, owner_user_id);

CREATE TABLE transactions(
id INT PRIMARY KEY AUTO_INCREMENT,
amount INT NOT NULL,
currency_id INT NOT NULL,
transaction_type VARCHAR(20) NOT NULL,
sender_user_id INT NOT NULL,
sender_account_id INT NOT NULL,
receiver_user_id INT NOT NULL,
receiver_account_id INT NOT NULL,
creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(sender_user_id) REFERENCES users(id),
FOREIGN KEY(sender_account_id) REFERENCES accounts(id),
FOREIGN KEY(receiver_user_id) REFERENCES users(id),
FOREIGN KEY(receiver_account_id) REFERENCES accounts(id),
FOREIGN KEY(currency_id) REFERENCES currencies(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Create unique index on contacts' id and owner_id
CREATE UNIQUE INDEX transaction_sender_receiver ON transactions(id ASC, sender_user_id, receiver_user_id);

-- Insert currencies
INSERT INTO nova_wallet.currencies(name, symbol)
VALUES
('US Dollars', 'USD'),
('Chilean Pesos', 'CLP'),
('Euros', 'EUR');

-- Insert users
INSERT INTO nova_wallet.users(first_name, last_name, email, password, creation_date)
VALUES 
('Pepito', 'Perez', 'pepito@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Fulanito', 'Perez', 'fulano@mail.com', '$2a$10$F93Pzfdzcd7msk62EKkv7u9VpNcNNrTnwFyJvegLj6f5P5MVJkVb2', NOW()),
('Natalia', 'Rojas', 'naty@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Juan', 'Durán', 'jduran@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('María', 'Perez', 'mperez@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Luffy', 'Monkey D', 'luffy@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Diego', 'Gonzalez', 'diego@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Matías', 'Lillo', 'mlillo@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Carlos', 'Gomez', 'carlos@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Andrea', 'Perez', 'aperez@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Rodrigo', 'Martinez', 'rmartinez@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Marta', 'Gajardo', 'mgajardo@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW()),
('Pedro', 'Perez', 'pperez@mail.com', '$2a$10$OboGwlU9.83FbUylNMakKOtwNrHhbH9kLySK3qbjpZrmUt.tEge4C', NOW());

-- Insert accounts
INSERT INTO nova_wallet.accounts(owner_id, balance, currency_id, creation_date)
VALUES
(1, 0, 1, NOW()),
(2, 0, 1, NOW()),
(3, 0, 1, NOW()),
(4, 0, 1, NOW()),
(5, 0, 1, NOW()),
(6, 0, 1, NOW()),
(7, 0, 1, NOW()),
(8, 0, 1, NOW()),
(9, 0, 1, NOW()),
(10, 0, 1, NOW()),
(11, 0, 1, NOW()),
(12, 0, 1, NOW()),
(13, 0, 1, NOW());

-- Insert contacts
INSERT INTO nova_wallet.contacts (first_name, last_name, email, contact_user_id, owner_user_id, creation_date)
VALUES
('Fulanito', 'Perez','fulano@mail.com', 2, 1, NOW()),
('Fulanito', 'Perez','fulano@mail.com', 2, 3, NOW()),
('Fulanito', 'Perez','fulano@mail.com', 2, 4, NOW()),
('Fulanito', 'Perez','fulano@mail.com', 2, 5, NOW()),
('Natalia', 'Rojas','naty@mail.com', 3, 1, NOW()),
('Juan', 'Durán','jduran@mail.com', 4, 1, NOW()),
('María', 'Perez','mperez@mail.com', 5, 1, NOW()),
('Monkey D', 'Luffy','luffy@mail.com', 6, 1, NOW());

-- Insert transactions
INSERT INTO nova_wallet.transactions (sender_user_id, sender_account_id, receiver_user_id, receiver_account_id,
currency_id, amount, transaction_type, creation_date) 
VALUES 
(1, 1, 1, 1, 1, 2600, 'deposit', NOW()),
(1, 1, 1, 1, 1, 800, 'withdrawal', NOW()),
(1, 1, 2, 2, 1, 400, 'transfer', NOW()),
(1, 1, 3, 3, 1, 1000, 'transfer', NOW());

-- Update accounts according to transaction movements
UPDATE accounts SET balance = 400 WHERE id = 1;
UPDATE accounts SET balance = 400 WHERE id = 2;
UPDATE accounts SET balance = 1000 WHERE id = 3;

COMMIT;