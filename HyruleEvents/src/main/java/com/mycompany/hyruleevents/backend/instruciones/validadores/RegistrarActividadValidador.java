/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.validadores;

import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Actividad;
import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;

/**
 *
 * @author edu
 */
public class RegistrarActividadValidador extends ValidadorDeInstruccion {

    private static final int INDICE_TIPO_ACTIVIDAD = 2;
    public RegistrarActividadValidador(int primerCaracterRelevante, int cantidadDeParametros, String nombreDeInstruccion) {
        super(19, 8, "REGISTRO_ACTIVIDAD");
    }

    @Override
    protected void indicarParametrosObligatorios() {

        Dependencia[] tipoDeDependencia = {Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO,
            Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO,
            Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO};

        this.tipoDeDependencia = tipoDeDependencia;

    }

    @Override
    protected void inicializarTiposDeParametro() {
        Parametro[] tipo = {Parametro.STRING, Parametro.STRING, Parametro.STRING, Parametro.STRING,
            Parametro.STRING, Parametro.STRING, Parametro.STRING, Parametro.ENTERO};

        this.tiposDeParametro = tipo;
    }

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {
        String tipoActividad = parametros[INDICE_TIPO_ACTIVIDAD];
        boolean valorEncontrado = false;
        for (Actividad e : Actividad.values()) {
            if (e.getTipoActividad().equals(tipoActividad)) {
                valorEncontrado = true;
                break;
            }
        }

        if (valorEncontrado) {
            return true;
        } else {
            logs = ">>> " +tipoActividad + " no es un tipo de actividad";  
            return false;
        }
    }

}
