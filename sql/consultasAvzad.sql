--Mostrar usuarios cuyo nombre comienza con 'A' y tienen saldo mayor a 5000
SELECT nombre, apellido, saldo
FROM Usuario
INNER JOIN Cuenta ON Usuario.id_usuario = Cuenta.id_usuario
WHERE nombre LIKE 'A%' AND saldo > 5000;

--Mostrar usuarios con cuentas cuyo saldo esté entre 1000 y 5000
SELECT nombre, apellido, saldo
FROM Usuario
INNER JOIN Cuenta ON Usuario.id_usuario = Cuenta.id_usuario
WHERE saldo BETWEEN 1000 AND 5000;

--Mostrar usuarios con rol 'cliente' que tengan una tarjeta de tipo débito o prepaga
SELECT DISTINCT Usuario.nombre, Usuario.apellido
FROM Usuario
INNER JOIN Cuenta ON Usuario.id_usuario = Cuenta.id_usuario
INNER JOIN Tarjeta ON Cuenta.id_cuenta = Tarjeta.id_cuenta
WHERE Usuario.rol = 'cliente' AND Tarjeta.tipo IN ('débito', 'prepaga');

--Mostrar usuarios que no tienen un email registrado
SELECT nombre, apellido
FROM Usuario
WHERE email IS NULL;

--Mostrar usuarios que no sean administradores y tengan saldo menor a 1000
SELECT nombre, apellido, saldo
FROM Usuario
INNER JOIN Cuenta ON Usuario.id_usuario = Cuenta.id_usuario
WHERE Usuario.rol != 'admin' AND saldo > 1000;

--Mostrar nombre y apellido de usuarios que tienen más de una cuenta
SELECT nombre, apellido, COUNT(Cuenta.id_cuenta) AS cantidad_cuentas
FROM Usuario
INNER JOIN Cuenta ON Usuario.id_usuario = Cuenta.id_usuario
GROUP BY Usuario.id_usuario
HAVING COUNT(Cuenta.id_cuenta) > 1;

--Mostrar nombre y apellido de usuarios con fecha de vencimiento superiores a 2023 y activas
SELECT DISTINCT Usuario.nombre, Usuario.apellido
FROM Usuario
INNER JOIN Cuenta ON Usuario.id_usuario = Cuenta.id_usuario
INNER JOIN Tarjeta ON Cuenta.id_cuenta = Tarjeta.id_cuenta
WHERE Tarjeta.estado = true AND Tarjeta.fecha_vencimiento > 2023;
