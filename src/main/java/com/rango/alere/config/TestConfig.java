package com.rango.alere.config;

import com.rango.alere.controllers.dtos.AlimentoInsertDTO;
import com.rango.alere.controllers.dtos.EnderecoInsertDTO;
import com.rango.alere.controllers.dtos.UserRegisterDTO;
import com.rango.alere.entities.Alimento;
import com.rango.alere.entities.Usuario;
import com.rango.alere.entities.enums.Estado;
import com.rango.alere.entities.enums.TipoAlimento;
import com.rango.alere.facades.AlimentoFacade;
import com.rango.alere.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile("teste")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private AlimentoFacade alimentoFacade;

    @Override
    public void run(String... args) throws Exception {

        EnderecoInsertDTO endDto = new EnderecoInsertDTO("TESTE", "07", "EMBU-GUAÇU", "CENTRO", "00000000", null, Estado.SAO_PAULO);

        UserRegisterDTO dto = new UserRegisterDTO("DOADOR", "TESTE", "doador@gmail.com", "teste123", "11999999999", true, false, true, endDto);
        UserRegisterDTO dto2 = new UserRegisterDTO("RECEPTOR", "TESTE", "receptor@gmail.com", "teste123", "11999999999", false, true, true, endDto);
        UserRegisterDTO dto3 = new UserRegisterDTO("USER", "TESTE", "user@gmail.com", "teste123", "11999999999", true, true, true, endDto);

        Usuario user1 = userFacade.registerUser(dto);
        Usuario user2 = userFacade.registerUser(dto2);
        Usuario user3 = userFacade.registerUser(dto3);

        AlimentoInsertDTO aliDto = new AlimentoInsertDTO("Alimento 1", "Alimento 1", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto1 = new AlimentoInsertDTO("Alimento 2", "Alimento 2", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto2 = new AlimentoInsertDTO("Alimento 3", "Alimento 3", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto3 = new AlimentoInsertDTO("Alimento 4", "Alimento 4", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto4 = new AlimentoInsertDTO("Alimento 5", "Alimento 5", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto5 = new AlimentoInsertDTO("Alimento 6", "Alimento 6", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto6 = new AlimentoInsertDTO("Alimento 7", "Alimento 7", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto7 = new AlimentoInsertDTO("Alimento 8", "Alimento 8", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto8 = new AlimentoInsertDTO("Alimento 9", "Alimento 9", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto9 = new AlimentoInsertDTO("Alimento 10", "Alimento 10", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto10 = new AlimentoInsertDTO("Alimento 11", "Alimento 11", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto11 = new AlimentoInsertDTO("Alimento 12", "Alimento 12", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto12 = new AlimentoInsertDTO("Alimento 13", "Alimento 13", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);
        AlimentoInsertDTO aliDto13 = new AlimentoInsertDTO("Alimento 14", "Alimento 14", null, LocalDateTime.now(), null, null, null, true, TipoAlimento.ORGANICO);

        Alimento ali = alimentoFacade.registerAlimento(aliDto, user1);
        alimentoFacade.registerAlimento(aliDto1, user1);
        alimentoFacade.registerAlimento(aliDto2, user1);
        alimentoFacade.registerAlimento(aliDto3, user1);
        alimentoFacade.registerAlimento(aliDto4, user3);
        alimentoFacade.registerAlimento(aliDto5, user3);
        alimentoFacade.registerAlimento(aliDto6, user3);
        alimentoFacade.registerAlimento(aliDto7, user3);
        alimentoFacade.registerAlimento(aliDto8, user3);
        alimentoFacade.registerAlimento(aliDto9, user3);
        alimentoFacade.registerAlimento(aliDto10, user3);
        alimentoFacade.registerAlimento(aliDto11, user3);
        alimentoFacade.registerAlimento(aliDto12, user3);
        alimentoFacade.registerAlimento(aliDto13, user3);

        userFacade.createSolicitacao(user3, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ali);

    }
}
