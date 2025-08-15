/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class AsistenciaUpdate extends ConsultaSQL {

    private static final String INSERT_ASISTENCIA = "INSERT INTO asistencia "
            + "(correo_del_participante, codigo_actividad) "
            + "VALUES (?, ?)";
    public AsistenciaUpdate(Connection connection) {
        super(connection);
    }

    @Override

    public void realizarConsulta(String[] parametros) throws SQLException {
        String correoParticipante = parametros[0];
        String codigoActividad = parametros[1];
        
         PreparedStatement ps = connection.prepareStatement(INSERT_ASISTENCIA);
        ps.setString(1, correoParticipante);
        ps.setString(2, codigoActividad);

        ps.executeUpdate();
        ps.close();

    }

}
