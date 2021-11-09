package com.rango.alere.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "T_ALERI_USER")
public class Usuario {

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


    @OneToOne(mappedBy = "usuario")
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
    private List<Doacao> recebimenTos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_ALERE_USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


}
