DROP TABLE IF EXISTS car;
CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT,
    car_model VARCHAR(40) NULL ,
    car_number VARCHAR(40) NULL,
    load_capacity  INT NULL,
    car_characteristics  VARCHAR(40) NULL,
    car_driver VARCHAR(255) NULL,
    PRIMARY KEY (car_id)
);

DROP TABLE IF EXISTS trip_status;
CREATE TABLE trip_status (
    trip_status_id INT NOT NULL AUTO_INCREMENT,
    trip_status_name VARCHAR(40) NULL,
    PRIMARY KEY (trip_status_id)
);

DROP TABLE IF EXISTS trip;
CREATE TABLE trip (
    trip_id INT NOT NULL  AUTO_INCREMENT,
    date_trip DATE NULL ,
    car_id INT NULL ,
    distance INT NULL ,
    trip_status_id INT NULL ,
    PRIMARY KEY (trip_id),
    FOREIGN KEY (car_id) REFERENCES car (car_id),
    FOREIGN KEY (trip_status_id) REFERENCES trip_status (trip_status_id)
);