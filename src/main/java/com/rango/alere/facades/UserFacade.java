package com.rango.alere.facades;

import com.rango.alere.controllers.dtos.UserRegisterDTO;
import com.rango.alere.entities.*;
import com.rango.alere.entities.enums.Status;
import com.rango.alere.services.exceptions.PasswordInvalidException;
import com.rango.alere.services.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserFacade {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DoacaoService doacaoService;

    @Autowired
    private AlimentoService alimentoService;

    @Autowired
    private SolicitacaoService solicitacaoService;

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
            if (registerForm.isDoador() && roleService.existsByDescricao("DOADOR")) {
                roles.add(roleService.findRoleByDescricao("DOADOR"));
            }
            if (registerForm.isReceptor() && roleService.existsByDescricao("RECEPTOR")) {
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

    public UserDetails autenticate(Usuario usuario) {
        UserDetails details = usuarioService.loadUserByUsername(usuario.getUsername());
        if (passwordEncoder.matches(usuario.getPassword(), details.getPassword())) {
            return details;
        }
        throw new PasswordInvalidException("Senha inv??lida");
    }

    public boolean existsUserByEmail(String email) {
        return usuarioService.existsUsuarioByEmailLike(email);
    }

    public Usuario getCurrentUser() {
            String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return (Objects.nonNull(username) && usuarioService.existsUsuarioByEmailLike(username))? usuarioService.findByEmailLike(username):null;
    }

    public Solicitacao createSolicitacao(Usuario from, String msg , Alimento alimento) {
        try {
            Solicitacao solicitacao = new Solicitacao(null, msg, Status.AGUARDANDO, false, from, alimento.getCadastradoPor(), alimento, null);
            return solicitacaoService.save(solicitacao);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    public Solicitacao responderSolicitacao(Solicitacao solicitacao, Status status) {
        if (Objects.isNull(solicitacao) || Objects.isNull(status) || status.equals(Status.AGUARDANDO)) {
            throw new IllegalArgumentException();
        }
        solicitacao.setStatus(status);
        solicitacao.setRespondida(true);
        solicitacaoService.save(solicitacao);

        if (status.equals(Status.APROVADO)) {
            Alimento alimento = solicitacao.getAlimento();
            alimento.setAtivo(false);
            alimento.setReservado(true);
            alimento.setReservadoAte(LocalDateTime.now().plusDays(10));
            alimentoService.save(alimento);
            Doacao doacao = new Doacao(null, solicitacao, false, solicitacao.getPara(), solicitacao.getDe());
            doacaoService.save(doacao);
        }
        return solicitacao;
    }

}