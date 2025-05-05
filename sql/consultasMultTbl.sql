--MOSTRAR EL SALDO DE LA CUENTA DE LOS USUARIOS

SELECT nombre, apellido, saldo, fecha_actualizacion
FROM Usuario
INNER JOIN Cuenta ON usuario.id_usuario = cuenta.id_usuario;

--MOSTRAR NOMBRE Y APELLIDO DEL USUARIO QUE CUMPLA AMBAS CONDICIONES (rol admin y cuenta activa)

SELECT nombre, apellido
FROM Usuario 
INNER JOIN cuenta ON Usuario.id_usuario = Cuenta.id_usuario 
WHERE (Cuenta.estado = true AND Usuario.rol = 'admin');

--MOSTRAR NOMBRE, APELLIDO Y TOPE DE GASTO DE USUARIOS CUYA TARJETA SEA DE CRÉDITO Y ESTÉ ACTIVA

SELECT Usuario.nombre, Usuario.apellido, Tarjeta.tope_gasto
FROM Usuario
  INNER JOIN Cuenta 
  ON Usuario.id_usuario = Cuenta.id_usuario
  INNER JOIN Tarjeta
  ON Cuenta.id_cuenta = Tarjeta.id_cuenta
  WHERE (Tarjeta.estado = true AND Tarjeta.tipo = 'crédito');


