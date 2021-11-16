package com.rango.alere.controllers.dtos;

import com.rango.alere.controllers.dtos.validations.TermAndConditionsIsTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "{usuario.nome.empty}")
    private String nome;

    @NotBlank(message = "{usuario.sobrenome.empty}")
    private String sobrenome;

    @Email(message = "{usuario.email.valid}")
    @NotBlank(message = "{usuario.email.empty}")
    private String email;

    @Size(min = 8, max = 32, message = "{usuario.password.size}")
    private String password;

    @Size(min = 8, max = 32, message = "{usuario.password.size}")
    private String confirmPassword;

    @NotBlank(message = "{usuario.telefone.empty}")
    private String telefone;

    private boolean doador;

    private boolean receptor;

    @TermAndConditionsIsTrue(message = "{usuario.termsAndConditions.accept}")
    private boolean termsAndConditions;

    @Valid
    private EnderecoInsertDTO endereco;
}
