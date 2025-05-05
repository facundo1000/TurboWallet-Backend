--sintaxis de sqlite3s
-- Tabla Usuario
CREATE TABLE Usuario (
    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    apellido TEXT,
    contrasena TEXT,
    email TEXT UNIQUE,
    rol TEXT,
    fecha_registro TEXT DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TEXT,
    estado boolean

);

-- Tabla Cuenta
CREATE TABLE Cuenta (
    id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER,
    saldo REAL,
    fecha_apertura TEXT,
    estado boolean,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Tabla Tarjeta
CREATE TABLE Tarjeta (
    id_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT,
    id_cuenta INTEGER,
    tipo TEXT,
    fecha_vencimiento TEXT,
    estado boolean,
    tope_gasto REAL,
    numero_tarjeta TEXT,
    banco TEXT,
    cvv TEXT,
    FOREIGN KEY (id_cuenta) REFERENCES Cuenta(id_cuenta)
);

-- Tabla Transaccion
CREATE TABLE Transaccion (
    id_transaccion INTEGER PRIMARY KEY AUTOINCREMENT,
    medio_d_pago TEXT,
    monto REAL,
    fecha_hora TEXT,
    estado boolean,
    cuenta_destino INTEGER,
    cuenta_origen INTEGER,
    tipo TEXT,
    descripcion TEXT,
    FOREIGN KEY (cuenta_destino) REFERENCES Cuenta(id_cuenta),
    FOREIGN KEY (cuenta_origen) REFERENCES Cuenta(id_cuenta)
);



