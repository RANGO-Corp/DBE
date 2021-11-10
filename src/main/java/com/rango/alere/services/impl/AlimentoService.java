package com.rango.alere.services.impl;

import com.rango.alere.entities.Alimento;
import com.rango.alere.repositories.AlimentoRepository;
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
public class AlimentoService implements CrudService<Alimento, Long> {

    @Autowired
    private AlimentoRepository repository;

    @Override
    public Alimento getById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id: " + id));
    }

    @Override
    public Alimento save(Alimento entity) throws SaveResourceException {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new SaveResourceException("Cannot save resource: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, Alimento data) throws ResourceNotFoundException, UpdateResourceException {
        try {
            final Alimento entity = getById(id);
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
    public void populate(Alimento source, Alimento target) {
        target.setAtivo(source.isAtivo());
        target.setDescricao(source.getDescricao());
        target.setDataFabricacao(source.getDataFabricacao());
        target.setDataValidade(source.getDataValidade());
        target.setDisponivelAte(source.getDisponivelAte());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
        target.setPerecivel(source.isPerecivel());
        target.setReservado(source.isReservado());
        target.setReservadoAte(source.getReservadoAte());
        target.setUrlFoto(source.getUrlFoto());
        target.setTitulo(source.getTitulo());
        target.setTipo(source.getTipo());
    }
}
