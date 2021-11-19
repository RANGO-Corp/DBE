package com.rango.alere.facades;

import com.rango.alere.controllers.dtos.AlimentoInsertDTO;
import com.rango.alere.entities.Alimento;
import com.rango.alere.entities.Usuario;
import com.rango.alere.repositories.AlimentoRepository;
import com.rango.alere.services.impl.AlimentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Objects;

@Slf4j
@Service
public class AlimentoFacade {

    @Autowired
    private AlimentoService alimentoService;

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private UserFacade userFacade;

    @Transactional
    public Alimento registerAlimento(AlimentoInsertDTO alimentoDTO, Usuario usuario) {
        log.info("RegisterAlimento Payload: " + alimentoDTO);
        try {
            final Alimento alimento = new Alimento();
            alimento.setTitulo(alimentoDTO.getTitulo());
            alimento.setDescricao(alimentoDTO.getDescricao());
            try {
                if (Objects.nonNull(alimentoDTO.getFile())) {
                    alimento.setImage(Base64.getEncoder().encodeToString(alimentoDTO.getFile().getBytes()));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            alimento.setPerecivel(alimentoDTO.isPerecivel());
            alimento.setLongitude(alimentoDTO.getLongitude());
            alimento.setLatitude(alimentoDTO.getLatitude());
            alimento.setDataValidade(alimentoDTO.getDataValidade());
            alimento.setDataFabricacao(alimentoDTO.getDataFabricacao());
            alimento.setTipo(alimentoDTO.getTipo());
            alimento.setAtivo(true);
            log.info("Trying registe alimento: " + alimento);
            alimento.setCadastradoPor(usuario);
            log.info("Sucessful Alimento registered");
            return alimentoService.save(alimento);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public Page<Alimento> getAlimentosPage(Pageable pageable) {
        return alimentoRepository.findAllByAtivoIsTrue(pageable);
    }
}
