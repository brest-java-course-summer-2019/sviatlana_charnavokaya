INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Mercedes', '11-44 AA-1', 10, 'тент', 'Попов');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Volvo', '99-88 AM-1', 15, 'реф', 'Соловьев');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('MAZ', '02-03 AB-1', 20, 'негабарит', 'Волков');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Volkswagen', '22-23 AB-1', 20, 'негабарит', 'Сидоров');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Scania', '23-24 AC-1', 5, 'реф', 'Кириллов');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Mercedes', '24-25 AK-1', 10, 'реф', 'Лосев');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Volvo', '33-34 AM-1', 20, 'негабарит', 'Сергеев');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('Volvo', '34-35 AP-1', 20, 'тент', 'Ленин');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('MAZ', '40-41 AA-1', 10, 'тент', 'Носов');
INSERT INTO car (car_model, car_number, load_capacity, car_characteristics, car_driver) VALUES ('MAZ', '09-95 AM-1', 20, 'негабарит', 'Волков');

INSERT INTO trip_status (trip_status_name) VALUES ('принят');
INSERT INTO trip_status (trip_status_name) VALUES ('в пути');
INSERT INTO trip_status (trip_status_name) VALUES ('закрыт');

INSERT INTO trip (date_trip, car_id, distance, trip_status_id) VALUES ('2019-08-01', 2, 150, 3);
INSERT INTO trip (date_trip, car_id, distance, trip_status_id) VALUES ('2019-08-02', 6, 1500, 3);
INSERT INTO trip (date_trip, car_id, distance, trip_status_id) VALUES ('2019-08-02', 1, 1000, 3);
INSERT INTO trip (date_trip, car_id, distance, trip_status_id) VALUES ('2019-08-06', 6, 650, 3);