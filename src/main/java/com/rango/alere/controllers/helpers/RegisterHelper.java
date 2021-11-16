package com.rango.alere.controllers.helpers;

import com.rango.alere.controllers.dtos.EnderecoInsertDTO;
import com.rango.alere.controllers.dtos.UserRegisterDTO;
import com.rango.alere.entities.enums.Estado;

import java.util.List;

public class RegisterHelper {


    public static UserRegisterDTO getNewRegisterForm() {
        return UserRegisterDTO
                .builder()
                .endereco(new EnderecoInsertDTO())
                .build();
    }

    public static List<Estado> getEstados() {
        return List.of(Estado.values());
    }

}
