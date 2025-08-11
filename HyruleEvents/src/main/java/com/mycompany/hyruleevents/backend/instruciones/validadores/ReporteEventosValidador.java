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

public class ReporteEventosValidador extends ValidadorDeInstruccion {

    public ReporteEventosValidador(int primerCaracterRelevante, int cantidadDeParametros, String nombreDeInstruccion) {
        super(16, 5, "REPORTE_EVENTOS");
    }

    @Override
    protected void indicarParametrosObligatorios() {

        Dependencia[] tipoDeDependencia = {Dependencia.OPCIONAL, Dependencia.OPCIONAL,
        Dependencia.OPCIONAL, Dependencia.OPCIONAL, Dependencia.OPCIONAL};

        this.tipoDeDependencia = tipoDeDependencia;
    }

    @Override
    protected void inicializarTiposDeParametro() {
        Parametro[] tipo = {Parametro.STRING, Parametro.STRING, Parametro.STRING,
        Parametro.ENTERO, Parametro.ENTERO};
        this.tiposDeParametro = tipo;
    }
    

    @Override
    protected boolean parametrosEspecialesValidos(String[] parametros) {
        String tipoEvento = parametros[0];
        String fechaInicio = parametros[1];
        String fechaFin = parametros[2];
        
        String cupoMinimo = parametros[3];
        String cupoMaximo = parametros[4];
        
        
        boolean hayFiltroEvento = !tipoEvento.isBlank();  //si no está vacio
        boolean hayFiltroFecha = (!fechaInicio.isBlank() && !fechaFin.isBlank());
        boolean hayFiltroCupo = (!cupoMinimo.isBlank() && !cupoMaximo.isBlank() );
        
        if((hayFiltroEvento || hayFiltroFecha) || hayFiltroCupo){
            return true;
        }else{
            logs =">>> Debe usar al menos un filtro: Tipo,Fecha,Cupo";
            return false;
        }
    }

}
