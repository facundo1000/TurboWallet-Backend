package org.alkemy.alkywallet.controllers.dto;

public record TransferenciaDto(String tipoTransferencia,
                               String motivo,
                               String nombreDestinatario,
                               String bancoDestino,
                               String cuentaDestinatario,
                               String cuentaOrigen) {
}
