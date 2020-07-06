CREATE SCHEMA product_attributes;

CREATE TABLE product_attributes.product_category(
    id INT NOT NULL PRIMARY KEY,
    parent_id INT FOREIGN KEY REFERENCES product_attributes.product_category(id),
    name VARCHAR(50),
    description VARCHAR(200),
    created_at DATETIME2 NOT NULL,
    modified_at DATETIME2 NOT NULL
);

CREATE TABLE product_attributes.text_category(
    id INT PRIMARY KEY,
    product_cat_id INT NOT NULL FOREIGN KEY REFERENCES product_attributes.product_category(id),
    name VARCHAR(50),
    description VARCHAR(200),
    created_at DATETIME2 NOT NULL,
    modified_at DATETIME2 NOT NULL
);

CREATE TABLE product_attributes.text_property(
    id INT PRIMARY KEY,
    text_cat_id INT NOT NULL FOREIGN KEY REFERENCES product_attributes.text_category(id),
    name VARCHAR(100),
    description VARCHAR(200),
    created_at DATETIME2 NOT NULL,
    modified_at DATETIME2 NOT NULL
);

CREATE TABLE product_attributes.text_value(
    id INT PRIMARY KEY,
    text_prop_id INT NOT NULL FOREIGN KEY REFERENCES product_attributes.text_property(id),
    name VARCHAR(100),
    description VARCHAR(200),
    created_at DATETIME2 NOT NULL,
    modified_at DATETIME2 NOT NULL
);

CREATE TABLE product_attributes.text_prop_group(
    id INT PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(200),
    created_at DATETIME2 NOT NULL,
    modified_at DATETIME2 NOT NULL
);

CREATE TABLE product_attributes.textprops_group_mapping(
    id INT PRIMARY KEY,
    group_id INT NOT NULL FOREIGN KEY REFERENCES product_attributes.text_prop_group(id),
    text_prop_id INT NOT NULL FOREIGN KEY REFERENCES product_attributes.text_property(id),
    created_at DATETIME2 NOT NULL,
    modified_at DATETIME2 NOT NULL
);