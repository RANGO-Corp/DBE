package com.rango.alere.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ALERI_USER")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_user", length = 120, nullable = false)
    private String nome;

    @Column(name = "ds_email", length = 160, nullable = false, unique = true)
    private String email;

    @Column(name = "ds_password", nullable = false)
    private String password;

    @Column(name = "ds_telefone", nullable = false)
    private String telefone;

    @Column(name = "is_confirmed")
    private boolean confirmed;

    @Column(name = "is_banned")
    private boolean banned;

    @Column(name = "is_actived")
    private boolean actived;

    @Column(name = "terms_and_condicions")
    private boolean termsAndConditions;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;



    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "cadastradoPor")
    private List<Alimento> alimentos = new ArrayList<>();

    @OneToMany(mappedBy = "de")
    private List<Solicitacao> minhasSolicitacoes = new ArrayList<>();

    @OneToMany(mappedBy = "para")
    private List<Solicitacao> solicitacoesRecebidas = new ArrayList<>();

    @OneToMany(mappedBy = "doador")
    private List<Doacao> doacoes = new ArrayList<>();

    @OneToMany(mappedBy = "receptor")
    private List<Doacao> recebimentos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_ALERE_USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    public Usuario(Long id, String nome, String email, String password, String telefone, boolean confirmed, boolean banned, boolean actived, boolean termsAndConditions, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.confirmed = confirmed;
        this.banned = banned;
        this.actived = actived;
        this.termsAndConditions = termsAndConditions;
        this.endereco = endereco;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.actived;
    }
}
