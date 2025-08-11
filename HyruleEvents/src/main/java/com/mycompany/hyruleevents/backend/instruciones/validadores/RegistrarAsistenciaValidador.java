/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.validadores;

import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;

/**
 *
 * @author edu
 */
public class RegistrarAsistenciaValidador extends ValidadorDeInstruccion{

    public RegistrarAsistenciaValidador(int primerCaracterRelevante, int cantidadDeParametros, String nombreDeInstruccion) {
        super(11, 2, "ASISTENCIA");
    }

    @Override
    protected void indicarParametrosObligatorios() {
        Dependencia[] tipoDeDependencia = {Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO};
        this.tipoDeDependencia = tipoDeDependencia;
    }

    @Override
    protected void inicializarTiposDeParametro() {
        Parametro[] tipo = {Parametro.STRING, Parametro.STRING};
        this.tiposDeParametro = tipo;

    }

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {
        return true;
    }
    
}
