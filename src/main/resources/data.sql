INSERT INTO buses (plate,quantity_seat) VALUES ('ABC526', 40);
INSERT INTO buses (plate,quantity_seat) VALUES ('XYZ158', 40);
INSERT INTO buses (plate,quantity_seat) VALUES ('AXH165', 40);
INSERT INTO buses (plate,quantity_seat) VALUES ('FJB635', 40);
INSERT INTO buses (plate,quantity_seat) VALUES ('BVR365', 40);

INSERT INTO departments (name) VALUES ('Lima');
INSERT INTO departments (name) VALUES ('Ayacucho');
INSERT INTO departments (name) VALUES ('Cusco');
INSERT INTO departments (name) VALUES ('Madre de Dios');
INSERT INTO departments (name) VALUES ('Tacna');
INSERT INTO departments (name) VALUES ('Piura');
INSERT INTO departments (name) VALUES ('Arequipa');
INSERT INTO departments (name) VALUES ('Puno');

INSERT INTO seats(floor,bus_id,available,number) VALUES (1,1,true,1);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,1,true,2);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,1,true,3);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,1,true,4);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,1,true,5);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,2,true,1);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,2,true,2);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,2,true,3);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,2,true,4);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,2,true,5);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,3,true,1);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,3,true,2);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,3,true,3);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,3,true,4);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,3,true,5);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,4,true,1);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,4,true,2);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,4,true,3);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,4,true,4);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,4,true,5);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,5,true,1);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,5,true,2);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,5,true,3);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,5,true,4);
INSERT INTO seats(floor,bus_id,available,number) VALUES (1,5,true,5);

INSERT INTO employees (name,last_name,dni,birthday,email, cell_phone) VALUES ('José', 'Lovon Vega','74417486','1998-02-17','joslui1720082@gmail.com', 982087241);
INSERT INTO employees (name,last_name,dni,birthday,email, cell_phone) VALUES ('Miguel Angel', 'Calderon','74417222','1998-03-20','miguelcalderon@gmail.com', 998523641);
INSERT INTO employees (name,last_name,dni,birthday,email, cell_phone) VALUES ('Alex', 'Meza','744174309','1998-08-02','alexmeza@gmail.com', 963647894);


INSERT INTO clients (name,last_name,dni,birthday,email, cell_phone) VALUES ('Maria', 'Lopez','77418486','1988-10-15','marialo88@gmail.com',923619866);
INSERT INTO clients (name,last_name,dni,birthday,email, cell_phone) VALUES ('Pedro', 'Fierro Ramos','78152366','1990-01-27','pedrofierro27@gmail.com',982087241);
INSERT INTO clients (name,last_name,dni,birthday,email, cell_phone) VALUES ('Juan', 'Pacheco','79856214','1999-05-16','pacheco05123@gmail.com',993652784);

INSERT INTO trips (start_date,final_date,price,bus_id,dpt_departure_id,dpt_arrival_id,employee_id) VALUES ('2019-12-30 12:38:00','2019-12-30 20:38:00', 48.00,1, 1,2,3);
INSERT INTO trips (start_date,final_date,price,bus_id,dpt_departure_id,dpt_arrival_id,employee_id) VALUES ('2019-12-10 07:00:00','2019-12-11 13:38:00', 100.00,2, 1,3,3);
INSERT INTO trips (start_date,final_date,price,bus_id,dpt_departure_id,dpt_arrival_id,employee_id) VALUES ('2019-10-26 12:38:00','2019-10-28 06:38:00', 50.00,3, 2,1,3);

INSERT INTO coupons(description, discount, expire_date, release_date,special_code, trip_id,status,client_id) VALUES ('Cupon Navideño', 50, '1-12-2019','31-12-2019', '123456', 1,false,1);
INSERT INTO coupons(description, discount, expire_date, release_date,special_code, trip_id,status,client_id) VALUES ('Cupon Navideño', 50, '1-12-2019','31-12-2019',  'ABC123', 2,false,2);
INSERT INTO coupons(description, discount, expire_date, release_date,special_code, trip_id,status,client_id) VALUES ('Cupon Navideño', 50, '1-12-2019','31-12-2019', 'DEF456', 3,false,3);



