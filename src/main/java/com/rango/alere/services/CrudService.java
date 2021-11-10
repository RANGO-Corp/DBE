package com.rango.alere.services;

import com.rango.alere.services.exceptions.DeleteResourceException;
import com.rango.alere.services.exceptions.SaveResourceException;
import com.rango.alere.services.exceptions.ResourceNotFoundException;
import com.rango.alere.services.exceptions.UpdateResourceException;

public interface CrudService<T, K> {

    T getById(K id) throws ResourceNotFoundException;

    T save(T entity) throws SaveResourceException;

    void update(K id, T data) throws ResourceNotFoundException, UpdateResourceException;

    void delete(K id) throws ResourceNotFoundException, DeleteResourceException;

    void populate(T source, T target);

}
