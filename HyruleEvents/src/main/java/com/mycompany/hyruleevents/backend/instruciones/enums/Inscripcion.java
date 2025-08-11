/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.enums;

/**
 *
 * @author edu
 */
public enum Inscripcion {
    ASISTENTE("ASISTENTE"),
    CONFERENCISTA("CONFERENCISTA"),
    TALLERISTA("TALLERISTA"),
    OTRO("OTRO");
    
    private final String TIPO_INSCRIPCION;

    private Inscripcion(String TIPO_INSCRIPCION) {
        this.TIPO_INSCRIPCION = TIPO_INSCRIPCION;
    }

    public String getTIPO_INSCRIPCION() {
        return TIPO_INSCRIPCION;
    }
    
    

}
