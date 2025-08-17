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
public class EventoVerificador extends VerificadorEnDB {

    public EventoVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String codigo = parametros[0];
        try {
            ResultSet result = this.buscarEvento(codigo);
            if(result.next()){ // si lo encuentra duplicado
                throw new ExceptionEnDB("El evento con codigo " + codigo + " ya existe!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!"); // caso extremo
        }
    }

}
