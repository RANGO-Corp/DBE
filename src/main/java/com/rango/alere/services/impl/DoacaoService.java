package com.rango.alere.services.impl;

import com.rango.alere.entities.Doacao;
import com.rango.alere.repositories.DoacaoRepository;
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
public class DoacaoService implements CrudService<Doacao, Long> {

    @Autowired
    private DoacaoRepository repository;

    @Override
    public Doacao getById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id: " + id));
    }

    @Override
    public Doacao save(Doacao entity) throws SaveResourceException {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new SaveResourceException("Cannot save resource: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Doacao data) throws ResourceNotFoundException, UpdateResourceException {
        try {
            final Doacao entity = getById(id);
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
    public void populate(Doacao source, Doacao target) {
        target.setRealizada(source.isRealizada());
        target.setSolicitacao(source.getSolicitacao());
        target.setDoador(source.getDoador());
        target.setReceptor(source.getReceptor());
    }
}
