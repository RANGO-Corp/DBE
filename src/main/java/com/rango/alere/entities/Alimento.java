package com.rango.alere.entities;

import com.rango.alere.entities.enums.TipoAlimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_ALERE_ALIMENTOS")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_titulo", length = 60, nullable = false)
    private String titulo;

    @Column(name = "ds_descricao", nullable = false, columnDefinition = "text")
    private String descricao;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "ds_disponivel_ate")
    private LocalDateTime disponivelAte;

    @Column(name = "dt_reservado_ate")
    private LocalDateTime reservadoAte;

    @Column(name = "dt_fabricacao")
    private LocalDateTime dataFabricacao;

    @Column(name = "dt_validade")
    private LocalDateTime dataValidade;

    @Column(name = "nr_longitude", precision = 12, scale = 6)
    private Double longitude;

    @Column(name = "nr_latitude", precision = 12, scale = 6)
    private Double latitude;

    @Column(name = "is_perecivel")
    private boolean perecivel;

    @Column(name = "is_actived")
    private boolean ativo;

    @Column(name = "is_reserved")
    private boolean reservado;

    @Column(name = "ds_tipo")
    @Enumerated(value = EnumType.STRING)
    private TipoAlimento tipo;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "cadastrado_por")
    private Usuario cadastradoPor;

    @OneToMany(mappedBy = "alimento")
    private List<Solicitacao> solicitacoes = new ArrayList<>();


    public Alimento(Long id, String titulo, String descricao, String image, LocalDateTime disponivelAte, LocalDateTime reservadoAte, LocalDateTime dataFabricacao, LocalDateTime dataValidade, Double longitude, Double latitude, boolean perecivel, boolean ativo, boolean reservado, TipoAlimento tipo, Usuario cadastradoPor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.image = image;
        this.disponivelAte = disponivelAte;
        this.reservadoAte = reservadoAte;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.longitude = longitude;
        this.latitude = latitude;
        this.perecivel = perecivel;
        this.ativo = ativo;
        this.reservado = reservado;
        this.tipo = tipo;
        this.cadastradoPor = cadastradoPor;
    }

}
