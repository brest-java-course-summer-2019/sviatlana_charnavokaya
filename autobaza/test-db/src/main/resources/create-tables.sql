DROP TABLE IF EXISTS car;

CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT,
    car_model VARCHAR(40) NULL ,
    car_number VARCHAR(40) NULL,
    load_capacity  INT NULL,
    car_characteristics  VARCHAR(40) NULL,
    car_driver VARCHAR(255) NULL,
    PRIMARY KEY (car_id),
);