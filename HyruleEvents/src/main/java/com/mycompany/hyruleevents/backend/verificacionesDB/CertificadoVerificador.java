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
public class CertificadoVerificador extends VerificadorEnDB {

    private static String SELECT_ACTIVIDAD_VALIDA = "SELECT *FROM asistencia"
            + " join actividad on asistencia.codigo_evento = actividad.codigo_evento"
            + " where asistencia.codigo_evento = ? AND asistencia.correo_del_participante = ?";

    public CertificadoVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String correo = parametros[0];
        String codigoEvento = parametros[1];

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTIVIDAD_VALIDA)) {
            ps.setString(1, correo);
            ps.setString(2, codigoEvento);

            ResultSet resultSet = ps.executeQuery();
            if(!resultSet.next()){
                throw new ExceptionEnDB("El participante no asistió a ninguna actividad!");
            }
        } catch (SQLException ex) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!");
        }

    }
}
