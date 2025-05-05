-- indice clusterizado del id_transaccion para la tabla Transaccion
-- sqlite3 no permite crear un indice clusterizado en una tabla que ya tiene datos, 
-- por defecto crea las tablas con un indice clusterizado en la columna id
CREATE CLUSTERED INDEX idx_transaccion ON Transaccion(id_transaccion);

-- indice no clusterizado de la columna 'tipo' en la tabla 'Transaccion'
CREATE INDEX idx_tipo ON Transaccion(tipo);

-- indice no clusterizado de la columna 'email' en la tabla 'Usuario'
CREATE INDEX idx_email ON Usuario(email);

CREATE CLUSTERED INDEX idx_id_tarjeta ON Tarjeta(id_tarjeta);

CREATE CLUSTERED INDEX idx_apellido_usuario ON Usuario(apellido);
