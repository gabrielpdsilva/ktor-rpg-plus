-- === NEED TO UPDATE QUERIES ===

USE master
GO

-- === Creations ===
CREATE DATABASE rpg_plus
GO

USE rpg_plus
GO

CREATE TABLE category(
	id		INT	NOT NULL IDENTITY,
	name	VARCHAR(255) NOT NULL,
	PRIMARY KEY(id)
)
GO

CREATE TABLE name(
	id			INT NOT NULL IDENTITY,
	type		VARCHAR(255) NOT NULL CHECK (type IN('First Name', 'Middle Name', 'Last Name')),
	name		VARCHAR(255) NOT NULL,
	category_id INT NOT NULL,
	FOREIGN KEY(category_id) REFERENCES category(id),
)
GO

-- === Inserts ===
INSERT INTO category(name) VALUES
('medieval'),
('cyberpunk'),
('steampunk')
GO

INSERT INTO name(name, type, category_id) VALUES
('Richard', 'First Name', 1),
('Elizabeth', 'First Name', 1),
('Mary', 'First Name', 1),
('Turin', 'First Name', 1),
('The Great', 'Last Name', 1),
('Lion Heart', 'Last Name', 1)
GO

-- === Views ===
CREATE VIEW dbo.get_new_id
AS
SELECT NEWID() AS [new_id]
GO

-- === Functions ===
CREATE FUNCTION fn_get_random_name_by_category(@category_id INT)
RETURNS VARCHAR(255)
AS BEGIN

	DECLARE @first_name VARCHAR(255)
	DECLARE @last_name VARCHAR(255)
	DECLARE @complete_name VARCHAR(255)

	SET @first_name = (
		SELECT TOP 1 n.name
		FROM name n
		WHERE n.category_id = @category_id AND n.type = 'First Name'
		ORDER BY (SELECT [new_id] FROM get_new_id)
	)

	SET @last_name = (
		SELECT TOP 1 n.name
		FROM name n
		WHERE n.category_id = @category_id AND n.type = 'Last Name'
		ORDER BY (SELECT [new_id] FROM get_new_id)
	)

	SET @complete_name = @first_name + ' ' + @last_name
	RETURN @complete_name
END
GO

SELECT dbo.fn_get_random_name_by_category(1) AS random_name

-- === Exposed table inserts ===
INSERT INTO categories(name) VALUES
('medieval'),
('cyberpunk'),
('steampunk')
GO

INSERT INTO name_types(type) VALUES
('First Name'),
('Middle Name'),
('Last Name')
GO

INSERT INTO names(name, type_id, category_id) VALUES
('Richard', 1, 1),
('Elizabeth', 1, 1),
('Mary', 1, 1),
('Turin', 1, 1),
('The Great', 3, 1),
('Lion Heart', 3, 1)
GO