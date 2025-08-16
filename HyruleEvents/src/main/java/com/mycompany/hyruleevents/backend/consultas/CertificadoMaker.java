/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class CertificadoMaker extends ConsultaSQL {

     private static final String INSERT_ASISTENCIA = "SELECT  *FROM asistencia "
            + "(correo_del_participante, codigo_actividad) "
            + "VALUES (?, ?)";
    
    public CertificadoMaker(Connection connection) {
        super(connection);
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String correo = parametros[0];
        String codigoEvento = parametros[0];
        
        

    }

}
