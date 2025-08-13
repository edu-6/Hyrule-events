/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones;

/**
 *
 * @author edu
 */
public enum  Instruccion {
    REGISTRO_EVENTO("REGISTRO_EVENTO"),
    REGISTRO_PARTICIPANTE("REGISTRO_PARTICIPANTE"),
    INSCRIPCION("INSCRIPCION"),
    PAGO("PAGO"),
    VALIDAR_INSCRIPCION("VALIDAR_INSCRIPCION"),
    REGISTRO_ACTIVIDAD("REGISTRO_ACTIVIDAD"),
    ASISTENCIA("ASISTENCIA"),
    CERTIFICADO("CERTIFICADO"),
    REPORTE_PARTICIPANTES("REPORTE_PARTICIPANTES"),
    REPORTE_ACTIVIDADES("REPORTE_ACTIVIDADES"),;
    
    public final String instruccion;

    private Instruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getInstruccion() {
        return instruccion;
    }
    
    
    
}
