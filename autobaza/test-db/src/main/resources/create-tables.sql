DROP TABLE IF EXISTS car;

CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT,
    model VARCHAR(40) NULL ,
    number VARCHAR(40) NULL,
    load_capacity  INT NULL,
    car_characteristics  VARCHAR(40) NULL,
    driver VARCHAR(255) NOT NULL,
    PRIMARY KEY (car_id),
);