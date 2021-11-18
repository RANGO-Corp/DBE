package com.rango.alere.entities.enums;

public enum TipoAlimento {
    ORGANICO ("Orgânico"),
    INDUSTRIALIZADO ("Industrializado");

    private final String name;

    TipoAlimento(String n) {
        name = n;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String getName(){
        return name;
    }
}
