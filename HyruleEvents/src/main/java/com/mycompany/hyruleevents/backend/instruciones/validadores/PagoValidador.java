/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.validadores;

import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Pago;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;

/**
 *
 * @author edu
 */
public class PagoValidador extends ValidadorDeInstruccion {
    private static final int INDICE_TIPO_PAGO = 3;
    public PagoValidador() {
        super(5, 4, "PAGO");
    }

    @Override
    protected void indicarParametrosObligatorios() {
        Dependencia[] tipoDeDependencia = {Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO,
            Dependencia.OBLIGATORIO, Dependencia.OBLIGATORIO};

        this.tipoDeDependencia = tipoDeDependencia;
    }

    @Override
    protected void inicializarTiposDeParametro() {
        Parametro[] tipo = {Parametro.STRING, Parametro.STRING, Parametro.STRING, Parametro.DECIMAL};
        this.tiposDeParametro = tipo;
    }

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {
        String tipo = parametros[INDICE_TIPO_PAGO];
        boolean valorEncontrado = false;
        for (Pago p : Pago.values()) {
            if (p.getTipoPago().equals(tipo)) {
                valorEncontrado = true;
                break;
            }
        }

        if (valorEncontrado) {
            return true;
        } else {
            logs = ">>> " + tipo + " no es un tipo de pago";
            return false;
        }
    }
    
}
