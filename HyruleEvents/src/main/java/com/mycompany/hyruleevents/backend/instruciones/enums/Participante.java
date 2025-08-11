/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.enums;

/**
 *
 * @author edu
 */
public enum Participante {
    ESTUDIANTE("ESTUDIANTE"),
    PROFESIONAL("PROFESIONAL"),
    INVITADO("INVITADO");
    
    private final String tipoParticipante;
    Participante(String tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }
    public String getTipoParticipante(){
        return this.tipoParticipante;
    }
}
