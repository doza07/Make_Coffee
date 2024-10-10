--Ингридиенты
CREATE TABLE ingredients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

INSERT INTO ingredients (name, quantity) VALUES
('Water', 1000),  -- кол в мл
('Coffee beans', 1000),  -- кол в гр
('Milk', 3000);  -- кол в мл

--Рецепты
CREATE TABLE recipes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO recipes (name) VALUES
('Espresso'),
('Americano'),
('Cappuccino');

-- Составная для ингридиентов к рецептам
CREATE TABLE recipe_ingredients (
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    amount INT NOT NULL,
    PRIMARY KEY (recipe_id, ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)
);

--Espresso
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount) VALUES
(1, 1, 30),  -- 30 мл воды
(1, 2, 15);  -- 15 граммов кофе

--Americano
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount) VALUES
(2, 1, 100),  -- 100 мл воды
(2, 2, 15);   -- 15 граммов кофе

--Cappuccino
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount) VALUES
(3, 1, 30),   -- 30 мл воды
(3, 2, 15),   -- 15 граммов кофе
(3, 3, 100);  -- 100 мл молока

-- Кофе(для записи заказов)
CREATE TABLE coffee (
    id SERIAL PRIMARY KEY,
    recipe_id INT NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes(id)
);