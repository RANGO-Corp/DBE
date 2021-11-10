package com.rango.alere.services.impl;

import com.rango.alere.entities.Role;
import com.rango.alere.repositories.RoleRepository;
import com.rango.alere.services.CrudService;
import com.rango.alere.services.exceptions.DeleteResourceException;
import com.rango.alere.services.exceptions.ResourceNotFoundException;
import com.rango.alere.services.exceptions.SaveResourceException;
import com.rango.alere.services.exceptions.UpdateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleService implements CrudService<Role, Long> {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role getById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id: " + id));
    }

    @Override
    public Role save(Role entity) throws SaveResourceException {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new SaveResourceException("Cannot save resource: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Role data) throws ResourceNotFoundException, UpdateResourceException {
        try {
            final Role entity = getById(id);
            populate(data, entity);
            save(entity);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new UpdateResourceException("Cannot update resource: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, DeleteResourceException {
        try {
            repository.delete(getById(id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DeleteResourceException("Cannot delete resource: " + e.getMessage());
        }
    }

    @Override
    public void populate(Role source, Role target) {
        target.setDescricao(source.getDescricao());
        target.setDefault(source.isDefault());
    }

    public boolean existsByDescricao(String descricao) {
        return repository.existsByDescricaoIsLike(descricao);
    }

    public Role findRoleByDescricao(String descricao) throws ResourceNotFoundException {
        return repository.getRoleByDescricaoIsLike(descricao).orElseThrow(() -> new ResourceNotFoundException("Resource not found for descricao: " + descricao));
    }

}
