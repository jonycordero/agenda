CREATE TABLE Task (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  completed BOOLEAN NOT NULL
);

INSERT INTO Task (title, description, completed) VALUES ('Comprar víveres', 'Comprar leche, pan y huevos', false);
INSERT INTO Task (title, description, completed) VALUES ('Hacer ejercicio', 'Correr 30 minutos en el parque', false);
INSERT INTO Task (title, description, completed) VALUES ('Leer un libro', 'Terminar de leer el capítulo 5', false);