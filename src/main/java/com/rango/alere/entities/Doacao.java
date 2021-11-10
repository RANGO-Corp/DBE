package com.rango.alere.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_ALERE_DOACAO")
public class Doacao {

    @Id
    @Column(name = "id_doacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    @Column(name = "is_realizado")
    private boolean realizada;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Usuario doador;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;


    public Doacao(Long id, Solicitacao solicitacao, boolean realizada, Usuario doador, Usuario receptor) {
        this.id = id;
        this.solicitacao = solicitacao;
        this.realizada = realizada;
        this.doador = doador;
        this.receptor = receptor;
    }
}
