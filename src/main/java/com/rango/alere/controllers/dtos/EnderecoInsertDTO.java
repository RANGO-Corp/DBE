package com.rango.alere.controllers.dtos;

import com.rango.alere.entities.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoInsertDTO {

    @NotEmpty(message = "{endereco.logradouro.empty}")
    @Size(max = 100, message = "{endereco.logradouro.size}")
    private String logradouro;

    @NotEmpty(message = "{endereco.numero.empty}")
    @Size(max = 5, message = "{endereco.numero.size}")
    private String numero;

    @NotEmpty(message = "{endereco.cidade.empty}")
    @Size(max = 80, message = "{endereco.cidade.size}")
    private String cidade;

    @NotEmpty(message = "{endereco.bairro.empty}")
    @Size(max = 40, message = "{endereco.bairro.size}")
    private String bairro;

    @NotEmpty(message = "{endereco.cep.empty}")
    @Size(max = 8, message = "{endereco.cep.size}")
    private String cep;

    @Size(max = 100, message = "{endereco.complemento.size}")
    private String complemento;

    @NotNull(message = "{endereco.estado.empty}")
    private Estado estado;

}
