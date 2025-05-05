--Ejecutar los DELETE en este orden (Transacción → Tarjeta → Cuenta → Administrador → Usuario) 
--para evitar errores por restricciones de integridad referencial.


INSERT INTO Usuario (nombre, apellido, contrasena, email, rol, fecha_actualizacion, estado) VALUES
('Juan', 'Pérez', '1234', 'juan.perez@example.com', 'cliente', '2025-04-30', true),
('Ana', 'López', 'abcd', 'ana.lopez@example.com', 'cliente', '2025-04-30', true),
('Carlos', 'Gómez', 'pass1', 'carlos.gomez@example.com', 'admin', '2025-04-30', true),
('Lucía', 'Martínez', 'qwerty', 'lucia.martinez@example.com', 'cliente', '2025-04-30', true),
('Pedro', 'Sánchez', 'pass123', 'pedro.sanchez@example.com', 'admin', '2025-04-30', true);

UPDATE Usuario SET nombre = 'Juan Carlos' WHERE id_usuario = 1;
UPDATE Usuario SET rol = 'admin' WHERE id_usuario = 2;
UPDATE Usuario SET email = 'lucia.m@example.com' WHERE id_usuario = 4;
UPDATE Usuario SET contrasena = 'nuevaPass123' WHERE id_usuario = 3;
UPDATE Usuario SET fecha_actualizacion = '2025-05-01' WHERE id_usuario = 5;

DELETE FROM Usuario WHERE id_usuario = 3;
DELETE FROM Usuario WHERE id_usuario = 5;
DELETE FROM Usuario WHERE email = 'lucia.m@example.com';
DELETE FROM Usuario WHERE rol = 'admin';
DELETE FROM Usuario WHERE nombre = 'Juan Carlos';

SELECT * from Usuario;


INSERT INTO Cuenta (id_usuario, saldo, fecha_apertura, estado) VALUES
(1, 1500.00, '2023-01-15', true),
(2, 2450.75, '2023-03-10', true),
(3, 800.50, '2022-12-01', false),
(4, 1299.99, '2023-06-25', true),
(5, 5000.00, '2023-02-20', true);

UPDATE Cuenta SET saldo = saldo + 500 WHERE id_cuenta = 1;
UPDATE Cuenta SET estado = 'suspendida' WHERE id_cuenta = 3;
UPDATE Cuenta SET fecha_apertura = '2024-01-01' WHERE id_cuenta = 5;
UPDATE Cuenta SET saldo = 0.00 WHERE id_cuenta = 4;
UPDATE Cuenta SET estado = 'cerrada' WHERE id_cuenta = 2;

DELETE FROM Cuenta WHERE estado = 'cerrada';
DELETE FROM Cuenta WHERE saldo = 0.00;
DELETE FROM Cuenta WHERE fecha_apertura < '2023-01-01';
DELETE FROM Cuenta WHERE id_usuario = 3;
DELETE FROM Cuenta WHERE estado = 'suspendida';

SELECT * FROM Cuenta;

INSERT INTO Tarjeta (id_cuenta, tipo, fecha_vencimiento, estado, tope_gasto, numero_tarjeta, banco, cvv) VALUES
(1, 'crédito', '2026-07-01', true, 10000.00, '4111111111111111', 'Banco Uno', '123'),
(2, 'débito', '2025-12-01', true, 3000.00, '4222222222222', 'Banco Dos', '456'),
(3, 'crédito', '2024-10-01', false, 2000.00, '4333333333333', 'Banco Tres', '789'),
(4, 'débito', '2026-01-01', true, 1500.00, '4444444444444', 'Banco Uno', '321'),
(5, 'crédito', '2027-05-01', true, 5000.00, '4555555555555', 'Banco Cuatro', '654');

UPDATE Tarjeta SET estado = 'bloqueada' WHERE id_tarjeta = 1;
UPDATE Tarjeta SET tope_gasto = 6000.00 WHERE id_tarjeta = 5;
UPDATE Tarjeta SET banco = 'Banco Central' WHERE id_tarjeta = 2;
UPDATE Tarjeta SET tipo = 'prepaga' WHERE id_tarjeta = 3;
UPDATE Tarjeta SET fecha_vencimiento = '2027-12-31' WHERE id_tarjeta = 4;

DELETE FROM Tarjeta WHERE estado = 'bloqueada';
DELETE FROM Tarjeta WHERE banco = 'Banco Tres';
DELETE FROM Tarjeta WHERE tope_gasto < 2500;
DELETE FROM Tarjeta WHERE tipo = 'prepaga';
DELETE FROM Tarjeta WHERE fecha_vencimiento < '2025-01-01';

SELECT * FROM Tarjeta;

INSERT INTO Transaccion (medio_d_pago, monto, fecha_hora, estado, cuenta_destino, cuenta_origen, tipo, descripcion) VALUES
('tarjeta', 150.00, '2025-04-01 10:00:00', true, 2, 1, 'pago', 'Compra en tienda'),
('transferencia', 500.00, '2025-04-02 11:30:00', true, 3, 2, 'transferencia', 'Pago alquiler'),
('tarjeta', 75.50, '2025-04-03 09:15:00', true, 4, 3, 'pago', 'Suscripción mensual'),
('efectivo', 200.00, '2025-04-04 14:00:00', true, 5, 4, 'retiro', 'Retiro en cajero'),
('transferencia', 1000.00, '2025-04-05 17:45:00', false, 1, 5, 'transferencia', 'Pago a proveedor');

UPDATE Transaccion SET estado = 'cancelada' WHERE id_transaccion = 3;
UPDATE Transaccion SET descripcion = 'Pago mensual modificado' WHERE id_transaccion = 1;
UPDATE Transaccion SET monto = 750.00 WHERE id_transaccion = 2;
UPDATE Transaccion SET tipo = 'compra' WHERE id_transaccion = 4;
UPDATE Transaccion SET fecha_hora = '2025-04-06 12:00:00' WHERE id_transaccion = 5;

DELETE FROM Transaccion WHERE id_transaccion = 3;  -- cancelada
DELETE FROM Transaccion WHERE monto < 100;         -- montos pequeños
DELETE FROM Transaccion WHERE tipo = 'retiro';     
DELETE FROM Transaccion WHERE cuenta_origen = 4;
DELETE FROM Transaccion WHERE estado = 'pendiente';

SELECT * FROM Transaccion;




