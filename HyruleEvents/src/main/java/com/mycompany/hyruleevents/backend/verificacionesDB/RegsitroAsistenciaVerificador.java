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
public class RegsitroAsistenciaVerificador extends VerificadorEnDB {

    private static final String SELECT_ASISTENCIA = "SELECT * FROM asistencia"
            + " WHERE correo_del_participante = ? AND codigo_actividad = ?";

    private static final String SELECT_ACTIVIDAD = "SELECT * FROM actividad WHERE codigo = ?";

    public RegsitroAsistenciaVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correoParticipante = parametros[0];
        String codigoActividad = parametros[1];

        try {

            try (PreparedStatement ps = connection.prepareStatement(SELECT_ASISTENCIA)) {
                ps.setString(1, correoParticipante);
                ps.setString(2, codigoActividad);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    throw new ExceptionEnDB("Ya se registró esta asistencia");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTIVIDAD)) {
                ps.setString(1, codigoActividad);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) { // Si no la encuentra 
                    throw new ExceptionEnDB("No existe la actividad con codigo: " + codigoActividad);
                }
            }
            
            try (PreparedStatement ps = connection.prepareStatement(SELECT_PARTICIPANTE)) {
                ps.setString(1, correoParticipante);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) { // Si no la encuentra 
                    throw new ExceptionEnDB("No existe el participante con correo: " + correoParticipante);
                }
            }

        } catch (SQLException e) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!"); // caso extremo
        }
    }

}
