package com.rango.alere.services.impl;

import com.rango.alere.entities.Solicitacao;
import com.rango.alere.repositories.SolicitacaoRepository;
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
public class SolicitacaoService implements CrudService<Solicitacao, Long> {

    @Autowired
    private SolicitacaoRepository repository;

    @Override
    public Solicitacao getById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id: " + id));
    }

    @Override
    public Solicitacao save(Solicitacao entity) throws SaveResourceException {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new SaveResourceException("Cannot save resource: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Solicitacao data) throws ResourceNotFoundException, UpdateResourceException {
        try {
            final Solicitacao entity = getById(id);
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
    public void populate(Solicitacao source, Solicitacao target) {
        target.setAlimento(source.getAlimento());
        target.setDe(source.getDe());
        target.setPara(source.getPara());
        target.setDoacao(source.getDoacao());
        target.setMensagem(source.getMensagem());
        target.setRespondida(source.isRespondida());
        target.setStatus(source.getStatus());
    }
}
