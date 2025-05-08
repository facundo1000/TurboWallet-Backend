INSERT INTO usuarios (nombre, apellido, email,contrasenia, rol,fecha_registro ,fecha_actualizacion, estado) VALUES
('Juan', 'Pérez', 'juan.perez@example.com', '1234','ADMIN', '2025-04-30','2025-05-03' ,true),
('Ana', 'López', 'ana.lopez@example.com','abcd', 'USER', '2025-04-30', '2025-05-03',true);


INSERT INTO cuentas (cbu, saldo, fecha_apertura, estado,id_usuario) VALUES
('1059127711100063105336',1500000.00, '2023-01-15', true,1),
('6321787211100052764841',2450000.75, '2023-03-10', true,1),
('0813032311100080929014',8000000.50, '2022-12-01', false,2),
('7271961711100068475704',12990000.99, '2023-06-25', true,2),
('3788914011100006218660',5000000.00, '2023-02-20', true,1);

INSERT INTO tarjetas (nombre_titular,tipo, fecha_vencimiento, estado, tope_gasto, numero_tarjeta, banco, cvv,id_cuenta) VALUES
('roberto carlos','credito', '2026-07-01', true, 10000.00, '4111111111111111', 'Banco Uno', '123',1),
('marcelo gallardo','debito', '2025-12-01', true, 3000.00, '4222222222222', 'Banco Dos', '456',2),
('juan manso','credito', '2024-10-01', false, 2000.00, '4333333333333', 'Banco Tres', '789',1),
('obi juan','debito', '2026-01-01', true, 1500.00, '4444444444444', 'Banco Uno', '321',2),
('guido','credito', '2027-05-01', true, 5000.00, '4555555555555', 'Banco Cuatro', '654',1);
