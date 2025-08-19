/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import com.mycompany.hyruleevents.backend.consultas.ActividadUpdate;
import com.mycompany.hyruleevents.backend.consultas.AsistenciaUpdate;
import com.mycompany.hyruleevents.backend.consultas.CertificadoMaker;
import com.mycompany.hyruleevents.backend.consultas.ConsultaSQL;
import com.mycompany.hyruleevents.backend.consultas.EventoUpdate;
import com.mycompany.hyruleevents.backend.consultas.InscripcionUpdate;
import com.mycompany.hyruleevents.backend.consultas.PagoUpdate;
import com.mycompany.hyruleevents.backend.consultas.ParticipanteUpdate;
import com.mycompany.hyruleevents.backend.consultas.ValidarInscripcion;
import com.mycompany.hyruleevents.backend.instruciones.InstruccionException;
import com.mycompany.hyruleevents.backend.instruciones.ValidadorDeInstruccion;
import com.mycompany.hyruleevents.backend.instruciones.validadores.CertificadoValidacion;
import com.mycompany.hyruleevents.backend.instruciones.validadores.InscripcionValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.PagoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistrarActividadValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistrarAsistenciaValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeEventoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeParticipanteValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.ValidarInscripcionValidador;
import com.mycompany.hyruleevents.backend.verificacionesDB.ActividadVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.CertificadoVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.EventoVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.ExceptionEnDB;
import com.mycompany.hyruleevents.backend.verificacionesDB.InscripcionVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.PagoVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.ParticipanteVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.RegsitroAsistenciaVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.ValidarInscripcionVerificador;
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
    private EscritorDeReportes escritorReportes;

    public EjecutadorDeInstruccionesFrontend(GestorDeArchivos gestorDeArchivos, DBConnection dbConexion) {
        this.gestorDeArchivos = gestorDeArchivos;
        this.dbConexion = dbConexion;
        this.connection = dbConexion.getConnection();
        
        this.escritorReportes = new EscritorDeReportes(gestorDeArchivos);
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
    
    public void registrarInscripcion(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new InscripcionValidador();
        verificadorDB = new InscripcionVerificador (connection);
        query = new InscripcionUpdate(connection);
        ejecutarConsulta(parametros);

    }
    
    public void registrarPago(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new PagoValidador();
        verificadorDB = new PagoVerificador (connection);
        query = new PagoUpdate(connection);
        ejecutarConsulta(parametros);

    }
    
    
    public void validarInscripcion(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new ValidarInscripcionValidador();
        verificadorDB = new ValidarInscripcionVerificador (connection);
        query = new ValidarInscripcion(connection);
        ejecutarConsulta(parametros);

    }
    
    public void registrarActividad(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new RegistrarActividadValidador();
        verificadorDB = new ActividadVerificador (connection);
        query = new ActividadUpdate(connection);
        ejecutarConsulta(parametros);

    }
    
     public void registrarAsistencia(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new RegistrarAsistenciaValidador();
        verificadorDB = new RegsitroAsistenciaVerificador (connection);
        query = new AsistenciaUpdate(connection);
        ejecutarConsulta(parametros);

    }
     
     public void generarCertificado(String[] parametros) throws InstruccionException, SQLException, ExceptionEnDB {
        validadorParametros = new CertificadoValidacion();
        verificadorDB = new CertificadoVerificador (connection);
        query = new CertificadoMaker(escritorReportes,connection);
        ejecutarConsulta(parametros);

    }

    private void ejecutarConsulta(String[] parametros) throws InstruccionException, ExceptionEnDB, SQLException {
        validadorParametros.verificaParametrosFrontend(parametros);
        verificadorDB.verificarValidezConsulta(parametros);
        query.realizarConsulta(parametros);
    }
}
