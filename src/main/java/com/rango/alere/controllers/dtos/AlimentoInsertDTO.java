package com.rango.alere.controllers.dtos;

import com.rango.alere.controllers.dtos.validations.ValidImageFile;
import com.rango.alere.entities.enums.TipoAlimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoInsertDTO {

    @NotEmpty(message = "{alimento.titulo.empty}")
    private String titulo;

    @NotEmpty(message = "{alimento.descricao.empty}")
    private String descricao;

    private MultipartFile file;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataFabricacao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataValidade;

    private Double longitude;

    private Double latitude;

    private boolean perecivel;

    @NotNull(message = "{alimento.tipo.empty}")
    private TipoAlimento tipo;
}
