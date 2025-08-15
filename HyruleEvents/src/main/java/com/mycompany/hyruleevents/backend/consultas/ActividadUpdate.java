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
public class ActividadUpdate extends ConsultaSQL {
    private static final String INSERT_ACTIVIDAD = "INSERT INTO actividad "
            + "(codigo, codigo_evento, tipo_actividad, titulo_actividad, correo_del_poniente, hora_inicio,hora_fin,cupo_maximo_actividad,cupos_disponibles_actividad) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
    
    private static final String UPDATE_EVENTO = "UPDATE evento "+""
            + "  SET cupos_disponibles_para_actividades = cupos_disponibles_para_actividades - ? WHERE codigo = ?";
    
    public ActividadUpdate(Connection connection) {
        super(connection);
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String codigo = parametros[0];
        String codigoEvento = parametros[1];
        String tipoActividad = parametros[2];
        String tituloActividad = parametros[3];
        String correoPoniente = parametros[4];
        String horaInicio = parametros[5];
        String horaFin = parametros[6];
        String cupoMaximo = parametros[7];
        
        
        PreparedStatement ps = connection.prepareStatement(INSERT_ACTIVIDAD);
        ps.setString(1, codigo);
        ps.setString(2, codigoEvento);
        ps.setString(3, tipoActividad);
        ps.setString(4, tituloActividad);
        ps.setString(5, correoPoniente);
        ps.setString(6, horaInicio);
        ps.setString(7, horaFin);
        ps.setString(8, cupoMaximo);
        ps.setString(9, cupoMaximo);
        
        ps.executeUpdate();
        ps.close();
        
        // restar  cupos disponibles para actividades al evento que contiene la actividad
        PreparedStatement ps2 = connection.prepareStatement(UPDATE_EVENTO);
        ps2.setInt(1, Integer.parseInt(cupoMaximo));
        ps2.setString(2, codigoEvento);
        
        ps2.executeUpdate();
        ps2.close();
        
        
    }

}
