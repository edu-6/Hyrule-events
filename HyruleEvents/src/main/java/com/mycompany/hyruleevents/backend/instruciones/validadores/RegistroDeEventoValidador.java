/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.validadores;

import com.mycompany.hyruleevents.backend.instruciones.Instruccion;
import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Evento;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;

/**
 *
 * @author edu
 */
public class RegistroDeEventoValidador extends ValidadorDeInstruccion {

    private static final int INDICE_TIPO_EVENTO = 2;
    private Evento eventoEnun;

    public RegistroDeEventoValidador() {
        super(17, 6, "REGISTRO_EVENTO");
    }

    

    @Override
    protected void inicializarTiposDeParametro() {

        Parametro[] tipo = {Parametro.STRING, Parametro.STRING, Parametro.STRING, Parametro.STRING,
            Parametro.STRING, Parametro.ENTERO};

        this.tiposDeParametro = tipo;
    }

    @Override
    protected void indicarParametrosObligatorios() {
        Dependencia[] tipoDeDependencia = {Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO,
            Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO};
    }

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {
        String fecha = this.validadorFecha.fechaValida(parametros[2]);
        if (fecha == null) {
            logs = ">>> El formato de fecha debe estar en dd/mm/aa";
            return false;
        }

        boolean valorEncontrado = false;
        for (Evento e : Evento.values()) {
            if (e.getTipo().equals(parametros[this.INDICE_TIPO_EVENTO])) {
                valorEncontrado= true;
                break;
            }
        }
        
        if(valorEncontrado){
            return true;
        }else{
            logs=">>> "+ parametros[2]+" no es un tipo de evento";
            return false;
        }
    }
    
    
}
