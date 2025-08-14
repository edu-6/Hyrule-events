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
public class InscripcionUpdate extends ConsultaSQL {

    private static final String INSERT_INSCRIPCION = "INSERT INTO inscripcion "
            + "(correo_participante, codigo_evento, tipo_de_inscripcion)"
            + "VALUES (?, ?, ?)";

    public InscripcionUpdate(Connection connection) {
        super(connection);
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String correo_participante = parametros[0];
        String codigo_evento = parametros[1];
        String tipo_de_inscripcion = parametros[2];
        
        PreparedStatement ps = connection.prepareStatement(INSERT_INSCRIPCION);
        
        ps.setString(1, correo_participante);
        ps.setString(2, codigo_evento);
        ps.setString(3, tipo_de_inscripcion);
        
        ps.executeUpdate();
        ps.close();
    }

}
