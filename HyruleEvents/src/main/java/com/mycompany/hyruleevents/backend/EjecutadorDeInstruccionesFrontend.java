/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import com.mycompany.hyruleevents.backend.consultas.ConsultaSQL;
import com.mycompany.hyruleevents.backend.consultas.EventoUpdate;
import com.mycompany.hyruleevents.backend.consultas.ParticipanteUpdate;
import com.mycompany.hyruleevents.backend.instruciones.InstruccionException;
import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeEventoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeParticipanteValidador;
import com.mycompany.hyruleevents.backend.verificacionesDB.EventoVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.ExceptionEnDB;
import com.mycompany.hyruleevents.backend.verificacionesDB.ParticipanteVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.VerificadorEnDB;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class EjecutadorDeInstruccionesFrontend {

    private GestorDeArchivos gestorDeArchivos;
    private DBConnection dbConexion;
    private Connection connection;
    private ValidadorDeInstruccion validadorParametros;
    private VerificadorEnDB verificadorDB;
    private ConsultaSQL query;

    public EjecutadorDeInstruccionesFrontend(GestorDeArchivos gestorDeArchivos, DBConnection dbConexion) {
        this.gestorDeArchivos = gestorDeArchivos;
        this.dbConexion = dbConexion;
        this.connection = dbConexion.getConnection();
    }

    public void registrarEvento(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new RegistroDeEventoValidador();
        verificadorDB = new EventoVerificador(connection);
        query = new EventoUpdate(connection);
        ejecutarConsulta(parametros);

    }
    
    public void registrarParticipante(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new RegistroDeParticipanteValidador();
        verificadorDB = new ParticipanteVerificador (connection);
        query = new ParticipanteUpdate(connection);
        ejecutarConsulta(parametros);

    }

    private void ejecutarConsulta(String[] parametros) throws InstruccionException, ExceptionEnDB, SQLException {
        validadorParametros.verificaParametrosFrontend(parametros);
        verificadorDB.verificarValidezConsulta(parametros);
        query.realizarConsulta(parametros);
    }
}
