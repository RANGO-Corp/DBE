package com.rango.alere.services.impl;

import com.rango.alere.entities.Usuario;
import com.rango.alere.repositories.UsuarioRepository;
import com.rango.alere.services.CrudService;
import com.rango.alere.services.exceptions.DeleteResourceException;
import com.rango.alere.services.exceptions.ResourceNotFoundException;
import com.rango.alere.services.exceptions.SaveResourceException;
import com.rango.alere.services.exceptions.UpdateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioService implements CrudService<Usuario, Long>, UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario getById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id: " + id));
    }

    @Override
    public Usuario save(Usuario entity) throws SaveResourceException {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new SaveResourceException("Cannot save resource: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Usuario data) throws ResourceNotFoundException, UpdateResourceException {
        try {
            final Usuario entity = getById(id);
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
    public void populate(Usuario source, Usuario target) {
        target.setTelefone(source.getTelefone());
        target.setActived(source.isActived());
        target.setBanned(source.isBanned());
        target.setConfirmed(source.isConfirmed());
        target.setEndereco(source.getEndereco());
        target.setNome(source.getNome());
    }

    public boolean existsUsuarioByEmailLike(String email) {
        return repository.existsUsuarioByEmailLike(email);
    }

    public Usuario findByEmailLike(String email) throws ResourceNotFoundException {
        return repository.findByEmailLike(email).orElseThrow(() -> new ResourceNotFoundException("Resource not found for email: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return findByEmailLike(s);
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
