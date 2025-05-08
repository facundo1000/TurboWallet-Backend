package org.alkemy.alkywallet.services;

import java.util.List;

public interface CrudService<T> {

    List<T> obtenerTodos();

    List<T> obtenerPorEstado();

    T obtenerPorId(Long id);

    T crear(T t);

    T actualizar(T t, Long id);

    void eliminar(Long id);
}
