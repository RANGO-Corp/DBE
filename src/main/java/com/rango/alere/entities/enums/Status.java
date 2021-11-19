package com.rango.alere.entities.enums;

public enum Status {
    APROVADO ("Aprovado"),
    AGUARDANDO ("Aguardando"),
    RECUSADO ("Recusado");

    private final String name;

    Status(String n) {
        name = n;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String getName(){
        return name;
    }
}
