/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.verificacionesDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class PagoVerificador extends VerificadorEnDB {

    public PagoVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correoParticipante = parametros [0];
        String codigoEvento = parametros[1];
        String monto = parametros[3];
        try {
            ResultSet resultParticipante = this.buscarParticipante(correoParticipante);
            if(!resultParticipante.next()){ // si no lo encuentra
                throw new ExceptionEnDB(" No se encontró el participante en los registros");
            }
            
            ResultSet resultEvento = this.buscarEvento(codigoEvento);
            if(!resultEvento.next()){// si no encuentra el evento
                throw new ExceptionEnDB(" No se encontró el evento con codigo "+ codigoEvento);
            }
            
            double costoEvento = resultEvento.getDouble("costo"); // obteniendo costo del evento
            double cantidadPagada = Double.parseDouble(monto);
            
            if(cantidadPagada < costoEvento){
                 throw new ExceptionEnDB("El monto es menor al precio del evento");
            }else if(cantidadPagada > costoEvento){
                throw new ExceptionEnDB("El monto es mayor al precio del evento");
            }
        } catch (SQLException e) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        } catch (NumberFormatException e){
            e.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
    }
    
}
