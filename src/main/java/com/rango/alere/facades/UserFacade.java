package com.rango.alere.facades;

import com.rango.alere.controllers.dtos.UserRegisterDTO;
import com.rango.alere.entities.Endereco;
import com.rango.alere.entities.Role;
import com.rango.alere.entities.Usuario;
import com.rango.alere.services.exceptions.PasswordInvalidException;
import com.rango.alere.services.impl.EnderecoService;
import com.rango.alere.services.impl.RoleService;
import com.rango.alere.services.impl.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserFacade {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario registerUser(UserRegisterDTO registerForm) {
        log.info("Register form: " + registerForm);

        try {
            // Endereco
            Endereco endereco = Endereco
                    .builder()
                    .logradouro(registerForm.getEndereco().getLogradouro())
                    .numero(registerForm.getEndereco().getNumero())
                    .cep(registerForm.getEndereco().getCep())
                    .bairro(registerForm.getEndereco().getBairro())
                    .cidade(registerForm.getEndereco().getCidade())
                    .complemento(registerForm.getEndereco().getComplemento())
                    .estado(registerForm.getEndereco().getEstado())
                    .build();

            // Roles
            final List<Role> roles = new ArrayList<>(roleService.getDefaultRoles());
            if (registerForm.isDoador() || roleService.existsByDescricao("DOADOR")) {
                roles.add(roleService.findRoleByDescricao("DOADOR"));
            }
            if (registerForm.isReceptor() || roleService.existsByDescricao("RECEPTOR")) {
                roles.add(roleService.findRoleByDescricao("RECEPTOR"));
            }

            // Usuario
            Usuario usuario = Usuario
                    .builder()
                    .id(null)
                    .email(registerForm.getEmail())
                    .password(passwordEncoder.encode(registerForm.getPassword()))
                    .nome(registerForm.getNome() + " " + registerForm.getSobrenome())
                    .endereco(endereco)
                    .actived(true)
                    .termsAndConditions(true)
                    .telefone(registerForm.getTelefone())
                    .roles(roles)
                    .alimentos(new ArrayList<>())
                    .doacoes(new ArrayList<>())
                    .solicitacoesRecebidas(new ArrayList<>())
                    .recebimentos(new ArrayList<>())
                    .minhasSolicitacoes(new ArrayList<>())
                    .build();


            log.info("Trying register user: " + usuario);
            endereco.setUsuario(usuario);
            usuario = usuarioService.save(usuario);
            log.info("User successful registered");

            return usuario;
        } catch (Exception e) {
            log.info("Failed user register");
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails details = usuarioService.loadUserByUsername(usuario.getUsername());
        if (passwordEncoder.matches(usuario.getPassword(), details.getPassword())){
            return details;
        }
        throw new PasswordInvalidException("Senha inválida");
    }

    public boolean existsUserByEmail(String email) {
        return usuarioService.existsUsuarioByEmailLike(email);
    }

}