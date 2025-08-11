/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.enums;

/**
 *
 * @author edu
 */
public enum Evento {
    CHARLA("CHARLA"),
    CONGRESO("CONGRESO"),
    TALLER("TALLER"),
    DEBATE("DEBATE");

    private final String tipoEvento;

    Evento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTipo() {
        return tipoEvento;
    }

}
