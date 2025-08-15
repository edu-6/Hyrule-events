/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class EventoUpdate extends ConsultaSQL {

    private static final String INSERT_EVENTO = "INSERT INTO evento "
            + "(codigo, fecha_evento, tipo_evento, titulo_evento, ubicacion, cupo_maximo_evento,cupos_diposnibles_evento,cupos_disponibles_para_actividades) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public EventoUpdate(Connection connection) {
        super(connection);
    }
    
    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String codigo = parametros[0];
        String fecha = parametros[1];
        String tipo = parametros[2];
        String titulo = parametros[3];
        String ubicacion = parametros[4];
        String cupo = parametros[5];

        PreparedStatement ps = connection.prepareStatement(INSERT_EVENTO);
        ps.setString(1, codigo);
        ps.setString(2, fecha);
        ps.setString(3, tipo);
        ps.setString(4, titulo);
        ps.setString(5, ubicacion);
        ps.setString(6, cupo);
        ps.setString(7, cupo); //  para los cupos disponibles
        ps.setString(8, cupo); // para cupos de actividades disponibles
        ps.executeUpdate();
        ps.close();
    }
}
