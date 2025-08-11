/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.validadores;

import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Inscripcion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;

/**
 *
 * @author edu
 */
public class InscripcionValidador extends ValidadorDeInstruccion {

    private static final int INDICE_TIPO_INSCRIPCION = 2;

    public InscripcionValidador() {
        super(12, 3, "INSCRIPCION");
    }

    @Override
    protected void indicarParametrosObligatorios() {
        Dependencia[] tipoDeDependencia = {Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO,
            Dependencia.OBLIGATORIO};// 3 parametros all obligatorios

        this.tipoDeDependencia = tipoDeDependencia;
    }

    @Override
    protected void inicializarTiposDeParametro() {
        Parametro[] tipo = {Parametro.STRING, Parametro.STRING, Parametro.STRING};
        this.tiposDeParametro = tipo;
    }

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {
        String tipoInscripcion = parametros[INDICE_TIPO_INSCRIPCION];

        boolean valorEncontrado = false;
        for (Inscripcion i : Inscripcion.values()) {
            if (i.getTIPO_INSCRIPCION().equals(tipoInscripcion)) {
                valorEncontrado = true;
                break;
            }
        }

        if (valorEncontrado) {
            return true;
        } else {
            logs = ">>> " + tipoInscripcion + " no es un valor para " + nombreDeInstruccion;
            return false;
        }
    }

}
