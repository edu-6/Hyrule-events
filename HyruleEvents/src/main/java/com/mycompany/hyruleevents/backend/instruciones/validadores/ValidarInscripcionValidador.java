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
public class ValidarInscripcionValidador extends ValidadorDeInstruccion {

    public ValidarInscripcionValidador(int primerCaracterRelevante, int cantidadDeParametros, String nombreDeInstruccion) {
        super(20, 2, "VALIDAR_INSCRIPCION");
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
