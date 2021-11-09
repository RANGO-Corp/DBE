package com.rango.alere.entities;

import com.rango.alere.entities.enums.Estado;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_ALERE_ENDERECO")
public class Endereco {

    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_logradouro", length = 100)
    private String logradouro;

    @Column(name = "ds_numero", length = 5)
    private String numero;

    @Column(name = "ds_bairro", length = 40)
    private String bairro;

    @Column(name = "ds_cep", length = 8)
    private String cep;

    @Column(name = "ds_cidade", length = 80)
    private String cidade;

    @Column(name = "ds_estado")
    @Enumerated(value = EnumType.STRING)
    private Estado estado;

    @Column(name = "ds_complemento", length = 100)
    private String complemento;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;

}