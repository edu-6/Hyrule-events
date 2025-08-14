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
public class PagoUpdate extends ConsultaSQL {

    private static final String INSERT_PAGO = "INSERT INTO pago "
            + "(correo_participante, codigo_evento, metodo_pago,"
            + "monto) "
            + "VALUES (?, ?, ?, ?)";

    public PagoUpdate(Connection connection) {
        super(connection);
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String correo = parametros[0];
        String codigoEvento = parametros[1];
        String metodoPago = parametros[2];
        String monto = parametros[3];
        
        PreparedStatement ps = connection.prepareStatement(INSERT_PAGO);
        
        ps.setString(1, correo);
        ps.setString(2, codigoEvento);
        ps.setString(3, metodoPago);
        ps.setString(4, monto);
        
        ps.executeUpdate();
        ps.close();
    }

}
