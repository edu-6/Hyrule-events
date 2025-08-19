/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.verificacionesDB.VerificadorEnDB;

/**
 *
 * @author edu
 */
public class EjecutadorDeInstruccionesFrontend {
    private GestorDeArchivos gestorDeArchivos;
    private DBConnection dbConexion;
    private ValidadorDeInstruccion validadorInstruccion;
    private VerificadorEnDB verificadorDB;
    public EjecutadorDeInstruccionesFrontend(GestorDeArchivos gestorDeArchivos, DBConnection dbConexion) {
        this.gestorDeArchivos = gestorDeArchivos;
        this.dbConexion = dbConexion;
    }
    
    public void registrarEvento(String [] parametros){
        
    }
}
