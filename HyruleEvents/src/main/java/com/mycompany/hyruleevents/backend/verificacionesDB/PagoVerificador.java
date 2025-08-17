/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.verificacionesDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class PagoVerificador extends VerificadorEnDB {

    private static final String SELECT_PAGO = "SELECT * FROM pago"
            + " WHERE correo_participante = ? AND codigo_evento = ?";

    public PagoVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correoParticipante = parametros[0];
        String codigoEvento = parametros[1];
        String monto = parametros[3];

        try (PreparedStatement stm = connection.prepareStatement(SELECT_PAGO)) {
            stm.setString(1, correoParticipante);
            stm.setString(2, codigoEvento);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                throw new ExceptionEnDB(" Ya existe el pago!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
        
        
        try ( ResultSet resultParticipante = this.buscarParticipante(correoParticipante);){
            if (!resultParticipante.next()) { // si no lo encuentra
                throw new ExceptionEnDB(" No se encontró el participante en los registros");
            }

            ResultSet resultEvento = this.buscarEvento(codigoEvento);
            if (!resultEvento.next()) {// si no encuentra el evento
                throw new ExceptionEnDB(" No se encontró el evento con codigo " + codigoEvento);
            }

            double costoEvento = resultEvento.getDouble("costo"); // obteniendo costo del evento
            double cantidadPagada = Double.parseDouble(monto);

            if (cantidadPagada < costoEvento) {
                throw new ExceptionEnDB("El monto es menor al precio del evento");
            } else if (cantidadPagada > costoEvento) {
                throw new ExceptionEnDB("El monto es mayor al precio del evento");
            }

        } catch (SQLException e) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }
    }

}
