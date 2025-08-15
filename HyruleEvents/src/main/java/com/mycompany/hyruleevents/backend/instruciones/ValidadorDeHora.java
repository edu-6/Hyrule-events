/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author edu
 */
public class ValidadorDeHora {
    
    public boolean validarHoras(String horaInicio, String horaFin ){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("H:mm"); // Formato de 24 horas sin segundos
        try {
            LocalTime.parse(horaInicio, formato);
            LocalTime.parse(horaFin, formato);
            return true; 
        } catch (DateTimeParseException e) {
            return false; 
        }
    }
}
