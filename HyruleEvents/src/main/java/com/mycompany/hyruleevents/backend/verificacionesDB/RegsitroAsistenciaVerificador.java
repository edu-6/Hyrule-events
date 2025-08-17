/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.verificacionesDB;

import java.sql.Connection;

/**
 *
 * @author edu
 */
public class RegsitroAsistenciaVerificador extends VerificadorEnDB {

    public RegsitroAsistenciaVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {

    }

}
