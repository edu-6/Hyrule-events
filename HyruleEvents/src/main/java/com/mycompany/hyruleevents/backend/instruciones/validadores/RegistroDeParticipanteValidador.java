/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.validadores;

import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;
import com.mycompany.hyruleevents.backend.instruciones.enums.Participante;

/**
 *
 * @author edu
 */
public class RegistroDeParticipanteValidador extends ValidadorDeInstruccion {
    private static final int INDICE_TIPO_PARTICIPANTE  =1;
    public RegistroDeParticipanteValidador() {
        super(22, 4, "REGISTRO_PARTICIPANTE");
    }

    @Override
    protected void indicarParametrosObligatorios() {
        Dependencia[] tipoDeDependencia = {Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO,
            Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO};// 4 parametros all obligatorios
        
        this.tipoDeDependencia = tipoDeDependencia;
    }

    @Override
    protected void inicializarTiposDeParametro() {
        Parametro[] tipo = {Parametro.STRING, Parametro.STRING, Parametro.STRING, Parametro.STRING};
        this.tiposDeParametro = tipo;
    }

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {

        boolean valorEncontrado = false;
        for (Participante p : Participante.values()) {
            if (p.getTipoParticipante().equals(parametros[this.INDICE_TIPO_PARTICIPANTE])) {
                valorEncontrado = true;
                break;
            }
        }

        if (valorEncontrado) {
            return true;
        } else {
            logs = ">>> " + parametros[INDICE_TIPO_PARTICIPANTE] + " no es un tipo de participante";
            return false;
        }
        
    }

}
