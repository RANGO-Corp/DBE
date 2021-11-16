package com.rango.alere.entities.enums;

import com.rango.alere.entities.Endereco;

public enum Estado {
    ACRE ("Acre"),
    ALAGOAS ("Alagoas"),
    AMAPA ("Amapá"),
    AMAZONAS ("Amazonas"),
    BAHIA ("Bahia"),
    CEARA ("Ceará"),
    ESPIRITO_SANTO ("Espirito Santo"),
    GOIAS ("Goiás"),
    MARANHAO ("Maranhão"),
    MATO_GROSSO ("Mato Grosso"),
    MATO_GROSSO_DO_SUL ("Mato Grosso do Sul"),
    MINAS_GERAIS ("Minas Gerais"),
    PARA ("Pará"),
    PARAIBA ("Paraíba"),
    PARANA ("Paraná"),
    PERNAMBUCO ("Pernambuco"),
    PIAUI ("Piauí"),
    RIO_DE_JANEIRO ("Rio de Janeiro"),
    RIO_GRANDE_DO_SUL ("Rio Grande do Sul"),
    RONDONIA ("Rondônia"),
    RORAIMA ("Roraima"),
    SANTA_CATARINA ("Santa Catarina"),
    SAO_PAULO ("São Paulo"),
    SERGIPE ("Sergipe"),
    TOCANTINS ("Tocantins"),
    DISTRITO_FEDERAL ("Distrito Federal");

    private final String name;

    Estado(String n) {
        name = n;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String getName(){
        return name;
    }
}
