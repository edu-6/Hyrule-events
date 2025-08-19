/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.verificacionesDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu
 */
public class ValidarInscripcionVerificador extends VerificadorEnDB {

    private static final String SELECT_INSCRIPCION = "SELECT * FROM  inscripcion"
            + "  WHERE correo_participante = ? AND codigo_evento = ?";

    private static final String SELECT_EVENTO = "SELECT * FROM evento WHERE codigo = ?";

    private static final String SELECT_PAGO = "SELECT * FROM pago"
            + " WHERE correo_participante = ? AND  codigo_evento = ?";

    public ValidarInscripcionVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correo = parametros[0];
        String codigo_evento = parametros[1];

        try {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_INSCRIPCION);) {
                ps.setString(1, correo);
                ps.setString(2, codigo_evento);
                ResultSet resultInscripcion = ps.executeQuery();

                if (!resultInscripcion.next()) { // si no lo encuentra
                    throw new ExceptionEnDB(" No se encontró la inscripción ");
                }

                int validado = resultInscripcion.getInt("validada"); // revisar si ya está validada
                if (validado == 1) {
                    throw new ExceptionEnDB("Ya está validada ");
                }
            }
            
            try (PreparedStatement ps = connection.prepareStatement(SELECT_PAGO);) {
                ps.setString(1, correo);
                ps.setString(2, codigo_evento);
                ResultSet resultPago = ps.executeQuery();

                if (!resultPago.next()) { // si no lo encuentra
                    throw new ExceptionEnDB(" No se encontró el pago para la inscripción ");
                }
            }
            
            

            try (PreparedStatement stm = connection.prepareStatement(SELECT_EVENTO);) {
                //validando el evento
                stm.setString(1, codigo_evento);
                ResultSet result = stm.executeQuery();

                int cuposDisponibles = 0;
                if (result.next()) {
                    cuposDisponibles = result.getInt("cupos_diposnibles_evento"); // se obtienen los cupos disponibles
                }

                if (cuposDisponibles < 1) {
                    throw new ExceptionEnDB(" Ya no hay cupos para el evento! ");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
           throw new ExceptionEnDB(" Hubo un error al realizar la consulta!"); // caso extremo
        }

    }

}
