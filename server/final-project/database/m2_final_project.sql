-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS recipe CASCADE;
DROP TABLE IF EXISTS ingredient CASCADE;
DROP TABLE IF EXISTS recipe_ingredient;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE course (
    course_id serial,
    name varchar(20) NOT NULL,
    blurb varchar(100),
    CONSTRAINT PK_course PRIMARY KEY(course_id),
    CONSTRAINT UQ_course UNIQUE(name)
);

CREATE TABLE recipe (
    recipe_id serial,
    name varchar(50) NOT NULL,
    description varchar(100),
    directions varchar(200) NOT NULL,
    course_id int,
    CONSTRAINT PK_recipe PRIMARY KEY(recipe_id),
    CONSTRAINT FK_course_id FOREIGN KEY(course_id) REFERENCES course(course_id)
);

CREATE TABLE ingredient (
    ingredient_id serial,
    name varchar(20) NOT NULL,
    preparation varchar(100),
    quantity int NOT NULL,
    units varchar(20) NOT NULL,
    CONSTRAINT PK_ingredient PRIMARY KEY(ingredient_id)
);

CREATE TABLE recipe_ingredient (
    recipe_id int,
    ingredient_id int,
    CONSTRAINT PK_recipe_ingredient PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT FK_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT FK_ingredient_id FOREIGN KEY(ingredient_id) REFERENCES ingredient(ingredient_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

INSERT INTO course (name, blurb)
VALUES ('Appetizers', 'Something about appetizers');

INSERT INTO course (name, blurb)
VALUES ('Entrees', 'Something about entrees');

INSERT INTO course (name, blurb)
VALUES ('Desserts', 'Something about desserts');


INSERT INTO recipe (name, description, directions, course_id)
VALUES ('Egg Salad', 'A salad made of eggs', 'Add all of your ingredients and serve in well ventilated area', 1);

INSERT INTO recipe (name, description, directions, course_id)
VALUES ('Ice Cream', 'Delicious frozen cream', 'Dont bother making it, just buy it from the store.', 3);

INSERT INTO recipe (name, description, directions, course_id)
VALUES ('Steak', 'Hunk of meat', 'Put steak on flame, take steam off flame, eat with hands', 2);

INSERT INTO recipe (name, description, directions, course_id)
VALUES ('Crudite Platter', 'An assortment of raw vegetables', 'Arrange your vegetables neatly on a plate or wooden board and serve', 1);

INSERT INTO recipe (name, description, directions, course_id)
VALUES ('Baked Chicken', 'Chicken meat that has been baked', 'Season your chicken with spices, put in dutch oven, and bake at a temperature for an amount of time', 2);


INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Strip steak', 'Seasoned if you wish', 1, 'whole');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Egg', 'Hard Boiled and peeled', 6, 'whole');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Mayonnaise', 'None', 1, 'Cups');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Relish', 'None', 2, 'Tablespoons');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Cream', 'None', 4, 'Cups');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Sugar', 'Sifted', 2, 'Cups');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Vanilla', 'Shaken, not stirred', 1, 'Teaspoons');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Celery', 'Roughly chopped', 3, 'Stalks');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Carrots', 'Julienned', 2, 'Cups');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Cucumber', 'Cut into thin slices', 1, 'Cucumber');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Chicken Breast', 'Raw', 4, 'Breasts');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Salt', 'Coarsely ground', 1, 'Tablespoon');

INSERT INTO ingredient (name, preparation, quantity, units)
VALUES ('Pepper', 'Coarsely ground', 1, 'Tablespoon');


INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (3,1);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (1,2);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (1,3);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (1,4);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (2,5);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (2,6);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (2,7);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (4,8);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (4,9);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (4,10);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (5,11);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (5,12);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES (5,13);

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

COMMIT TRANSACTION;
