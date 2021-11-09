package com.rango.alere.entities;

import com.rango.alere.entities.enums.Status;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "T_ALERI_SOLICITACAO")
public class Solicitacao {

    @Id
    @Column(name = "id_solicitacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_mensagem")
    private String mensagem;

    @Column(name = "ds_status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "is_respondida")
    private boolean respondida;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "solicitador_id")
    private Usuario de;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Usuario para;

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

    @OneToOne(mappedBy = "solicitacao")
    private Doacao doacao;
}
