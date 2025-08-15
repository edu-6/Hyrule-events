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
public class ValidarInscripcion extends ConsultaSQL {

    private static final String VALIDAR_INSCRIPCION = "UPDATE inscripcion "
            + "SET validada = 1 WHERE correo_participante = ? AND codigo_evento = ?";
    
    public ValidarInscripcion(Connection connection) {
        super(connection);
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String correo = parametros[0];
        String codigoEvento = parametros[1];
        
        PreparedStatement ps = connection.prepareStatement(VALIDAR_INSCRIPCION);
        ps.setString(1, correo);
        ps.setString(2, codigoEvento);
        
        ps.executeUpdate();
        ps.close();
    }

}
