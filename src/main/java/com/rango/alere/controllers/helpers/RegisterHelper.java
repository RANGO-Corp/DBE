package com.rango.alere.controllers.helpers;

import com.rango.alere.controllers.dtos.AlimentoInsertDTO;
import com.rango.alere.controllers.dtos.EnderecoInsertDTO;
import com.rango.alere.controllers.dtos.UserRegisterDTO;
import com.rango.alere.entities.enums.Estado;
import com.rango.alere.entities.enums.TipoAlimento;

import java.util.List;

public class RegisterHelper {


    public static UserRegisterDTO getNewUserRegisterForm() {
        return UserRegisterDTO
                .builder()
                .endereco(new EnderecoInsertDTO())
                .build();
    }

    public static AlimentoInsertDTO getNewAlimentoRegisterForm() {
        return new AlimentoInsertDTO();
    }


    public static List<Estado> getEstados() {
        return List.of(Estado.values());
    }

    public static List<TipoAlimento> getTiposAlimentos() {
        return List.of(TipoAlimento.values());
    }


}
