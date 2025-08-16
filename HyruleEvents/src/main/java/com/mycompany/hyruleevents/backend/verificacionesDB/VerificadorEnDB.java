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
public abstract class VerificadorEnDB {
    protected Connection connection;

    public VerificadorEnDB(Connection connection) {
        this.connection = connection;
    }
    
    public abstract void verificarValidezConsulta(String [] parametros) throws ExceptionEnDB;
}
