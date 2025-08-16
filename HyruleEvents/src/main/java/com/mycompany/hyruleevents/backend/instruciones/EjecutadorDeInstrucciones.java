/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones;

import com.mycompany.hyruleevents.backend.EscritorDeReportes;
import com.mycompany.hyruleevents.backend.GestorDeArchivos;
import com.mycompany.hyruleevents.backend.consultas.ActividadUpdate;
import com.mycompany.hyruleevents.backend.consultas.AsistenciaUpdate;
import com.mycompany.hyruleevents.backend.consultas.CertificadoMaker;
import com.mycompany.hyruleevents.backend.consultas.ConsultaSQL;
import com.mycompany.hyruleevents.backend.consultas.EventoUpdate;
import com.mycompany.hyruleevents.backend.consultas.InscripcionUpdate;
import com.mycompany.hyruleevents.backend.consultas.PagoUpdate;
import com.mycompany.hyruleevents.backend.consultas.ParticipanteUpdate;
import com.mycompany.hyruleevents.backend.consultas.ValidarInscripcion;
import com.mycompany.hyruleevents.backend.instruciones.validadores.CertificadoValidacion;
import com.mycompany.hyruleevents.backend.instruciones.validadores.InscripcionValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.PagoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistrarActividadValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistrarAsistenciaValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeEventoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeParticipanteValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.ValidarInscripcionValidador;
import com.mycompany.hyruleevents.backend.verificacionesDB.CertificadoVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.EventoVerificador;
import com.mycompany.hyruleevents.backend.verificacionesDB.ExceptionEnDB;
import com.mycompany.hyruleevents.backend.verificacionesDB.VerificadorEnDB;
import com.mycompany.hyruleevents.fronted.ConsolaDeTexto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class EjecutadorDeInstrucciones implements Runnable {

    private static final String REGISTRO_EVENTO = "REGISTRO_EVENTO";
    private static final String REGISTRO_PARTICIPANTE = "REGISTRO_PARTICIPANTE";

    private static final String INSCRIPCION = "INSCRIPCION";
    private static final String PAGO = "PAGO";
    private static final String VALIDAR_INSCRIPCION = "VALIDAR_INSCRIPCION";
    private static final String REGISTRO_ACTIVIDAD = "REGISTRO_ACTIVIDAD";
    private static final String ASISTENCIA = "ASISTENCIA";
    private static final String CERTIFICADO = "CERTIFICADO";
    private static final String REPORTE_PARTICIPANTES = "REPORTE_PARTICIPANTES";
    private static final String REPORTE_ACTIVIDADES = "REPORTE_ACTIVIDADES";
    private static final String REPORTE_EVENTOS = "REPORTE_EVENTOS";

    private File archivo;
    private int velocidad;
    private Connection connection;
    private String logDeInstruccion = "";
    private ConsolaDeTexto consola;
    private GestorDeArchivos gestorArchivos;
    private EscritorDeReportes escritorDeReportes;
    
    public EjecutadorDeInstrucciones(GestorDeArchivos gestorArchivos, int velocidad, ConsolaDeTexto consola, Connection connection) {
        this.gestorArchivos = gestorArchivos;
        this.archivo = gestorArchivos.getArchivoDeTexto();
        this.velocidad = velocidad;
        this.consola = consola;
        this.connection = connection;
        this.escritorDeReportes =  new EscritorDeReportes(gestorArchivos);
    }

    @Override
    public void run() {

        try (FileReader reader = new FileReader(archivo);) {
            BufferedReader buffer = new BufferedReader(reader);
            String linea = buffer.readLine();
            int numeroLinea = 0;

            consola.ponerTitulo(archivo.getName());

            while (linea != null) {
                numeroLinea++;
                try {
                    logDeInstruccion = ""; // limpiar la linea
                    this.ejecutarInstruccion(linea, numeroLinea);
                } catch (InstruccionException e) {
                    logDeInstruccion = e.getMessage();
                }
                
                actualizarConsolaLogs(linea, logDeInstruccion, numeroLinea); // actualiza con lo que pasó en la instruccion
                
                Thread.sleep(velocidad);
                linea = buffer.readLine();
            }

            consola.activarBtnSiguente();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }

    }

    private void ejecutarInstruccion(String linea, int numeroLinea) throws InstruccionException {
        String instruccion = "";
        int indice = 0;
        while (indice < linea.length() && linea.charAt(indice) != '(') {
            instruccion += linea.charAt(indice);
            indice++;
        }
         

        ValidadorDeInstruccion validadorParametros = null;
        VerificadorEnDB verificadorDB = null; // verificador de integridad en a db
        ConsultaSQL query = null;  // ejecutador de consulta
        String[] parametros = null;
        boolean instruccionReconocida = true;

        switch (instruccion) {
            case REGISTRO_EVENTO:
                validadorParametros = new RegistroDeEventoValidador();
                verificadorDB = new EventoVerificador(connection);
                query = new EventoUpdate(connection);
                break;
            case REGISTRO_PARTICIPANTE:
                validadorParametros = new RegistroDeParticipanteValidador();
                query = new ParticipanteUpdate(connection);
                break;
            case INSCRIPCION:
                validadorParametros = new InscripcionValidador();
                query = new InscripcionUpdate(connection);
                break;
            case PAGO:
                validadorParametros = new PagoValidador();
                query = new PagoUpdate(connection);
                break;
            case VALIDAR_INSCRIPCION:
                validadorParametros = new ValidarInscripcionValidador();
                query = new ValidarInscripcion(connection);
                break;

            case REGISTRO_ACTIVIDAD:
                validadorParametros = new RegistrarActividadValidador();
                query = new ActividadUpdate(connection);
                break;
            case ASISTENCIA:
                validadorParametros = new RegistrarAsistenciaValidador();
                query = new AsistenciaUpdate(connection);
                break;
            case CERTIFICADO:
                validadorParametros = new CertificadoValidacion();
                verificadorDB = new CertificadoVerificador(connection);
                query = new CertificadoMaker(escritorDeReportes, connection);
                break;
            case REPORTE_PARTICIPANTES:
                System.out.println("reporte de participantes");
                break;
            case REPORTE_ACTIVIDADES:
                System.out.println("reporte de actividades");
                break;
            case REPORTE_EVENTOS:
                System.out.println("reporte de eventos");
                break;
            default:
                instruccionReconocida = false;
                break;
        }

        // esta linea así se queda porque si no encuentra instruccion pues no hay verificador
        if (validadorParametros != null) {
            parametros = validadorParametros.verificarInstruccion(linea);
        }

        if (!instruccionReconocida) {
            throw new InstruccionException(">>>>>>>La instrucción no fué reconocida");
        }

        if (parametros == null) {
            String mensaje;
            if(validadorParametros != null){
                 mensaje= ">>>>> Error en los parametros: " + "\n" + validadorParametros.getLogs();
            }else{
                mensaje = ">>>>> Error en los parametros";
            }
            throw new InstruccionException(mensaje); // lanzar exception
        }
        
        
        // ejecutar la instrucción
        try {
            //validar en DataBase
            //añadir ecetpion en database 
            if(verificadorDB != null){
                verificadorDB.verificarValidezConsulta(parametros);
            }
            query.realizarConsulta(parametros); // ejecutar la database;
            logDeInstruccion = "$ $ $ $ $ $ Se ejecutó la instrucción exitosamente";
        }catch(ExceptionEnDB e){
            throw new InstruccionException(">>>>>> Error al verificar en la base de datos:" + "\n" + e.getMessage());
        }catch (SQLException e) { // añadir exception en database;
             throw new InstruccionException(">>>>>> Error al hacer la consulta:" + "\n" + e.getMessage());
        }
    }

    private void actualizarConsolaLogs(String linea, String log, int numeroLinea) {
        String aviso = "======================= Ejecutando la linea: " + numeroLinea + " ===============================" + "\n" + linea + "\n";
        String titulo = "                   RESULTADO:                      " + "\n";
        String espacios = "\n" + "\n";
        String nuevoTexto = aviso + titulo + log + espacios;
        consola.setTexto(nuevoTexto);
    }

}
