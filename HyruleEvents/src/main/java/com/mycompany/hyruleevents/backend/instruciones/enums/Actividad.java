/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.enums;

/**
 *
 * @author edu
 */
public enum Actividad {
    CHARLA("CHARLA"),
    TALLER("TALLER"),
    DEBATE("DEBATE"),
    OTRA("OTRA"),;
    
    private final String TIPO_ACTIVIDAD;

    private Actividad(String tipo ) {
        this.TIPO_ACTIVIDAD  = tipo;
    }
    
    public String getTipoActividad(){
        return this.TIPO_ACTIVIDAD;
    }
    
    
}
