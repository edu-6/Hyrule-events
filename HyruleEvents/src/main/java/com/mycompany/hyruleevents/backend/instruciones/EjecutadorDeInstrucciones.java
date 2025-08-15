/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones;

import com.mycompany.hyruleevents.backend.consultas.ActividadUpdate;
import com.mycompany.hyruleevents.backend.consultas.ConsultaSQL;
import com.mycompany.hyruleevents.backend.consultas.EventoUpdate;
import com.mycompany.hyruleevents.backend.consultas.InscripcionUpdate;
import com.mycompany.hyruleevents.backend.consultas.PagoUpdate;
import com.mycompany.hyruleevents.backend.consultas.ParticipanteUpdate;
import com.mycompany.hyruleevents.backend.consultas.ValidarInscripcion;
import com.mycompany.hyruleevents.backend.instruciones.validadores.InscripcionValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.PagoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistrarActividadValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeEventoValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.RegistroDeParticipanteValidador;
import com.mycompany.hyruleevents.backend.instruciones.validadores.ValidarInscripcionValidador;
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
    
    private ConsolaDeTexto consola;

    public EjecutadorDeInstrucciones(File archivo, int velocidad, ConsolaDeTexto consola,Connection connection) {
        this.archivo = archivo;
        this.velocidad = velocidad;
        this.consola = consola;
        this.connection = connection;
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
                this.ejecutarInstruccion(linea,numeroLinea);
                Thread.sleep(velocidad);
                linea = buffer.readLine();
            }

            consola.activarBtnSiguente();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }

    }

    private void ejecutarInstruccion(String linea, int numeroLinea) {
        String instruccion = "";
        int indice = 0;
        while (indice < linea.length() && linea.charAt(indice) != '(') {
            instruccion += linea.charAt(indice);
            indice++;
        }
        String logDeInstruccion = "";

        ValidadorDeInstruccion validador = null;
        ConsultaSQL query = null;
        String[] parametros = null;
        boolean instruccionReconocida = true;

        switch (instruccion) {
            case REGISTRO_EVENTO:
                System.out.println("Es un registro de evento");
                validador = new RegistroDeEventoValidador();
                parametros = validador.verificarInstruccion(linea); // procesar la linea
                query = new EventoUpdate(connection);
                System.out.println("Saliendo del switch");
                break;
            case REGISTRO_PARTICIPANTE:
                System.out.println("Es un registro de participante");
                validador = new RegistroDeParticipanteValidador();
                parametros = validador.verificarInstruccion(linea);
                query = new ParticipanteUpdate(connection);
                System.out.println("Saliendo del switch");
                break;
            case INSCRIPCION:
                System.out.println("es una inscripcion");
                validador = new InscripcionValidador();
                parametros = validador.verificarInstruccion(linea);
                query = new InscripcionUpdate(connection);
                System.out.println("Saliendo del switch");
                break;
            case PAGO:
                System.out.println("Registro de un pago");
                validador = new PagoValidador();
                parametros = validador.verificarInstruccion(linea);
                query = new PagoUpdate(connection);
                System.out.println("Saliendo del switch");
                break;
            case VALIDAR_INSCRIPCION:
                System.out.println("Es validación de inscripcion");
                validador = new ValidarInscripcionValidador();
                parametros = validador.verificarInstruccion(linea);
                query = new ValidarInscripcion(connection);
                System.out.println("Saliendo del switch");
                break;

            case REGISTRO_ACTIVIDAD:
                System.out.println("Es registro de actividad");
                validador = new RegistrarActividadValidador();
                parametros = validador.verificarInstruccion(linea);
                query = new ActividadUpdate(connection);
                System.out.println("Saliendo del switch");
                break;
            case ASISTENCIA:
                System.out.println("Es registro de asistencia");
                break;
            case CERTIFICADO:
                System.out.println("petición de certificado");
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

        System.out.println("Ahora toca ejecutar archivo");
        if (instruccionReconocida) {
            try {

                if (parametros == null) {
                    
                    if(validador != null){
                        logDeInstruccion = ">>>>> Error en los parametros: " + "\n" + validador.getLogs(); // obtener errores
                    }else{
                        logDeInstruccion = ">>>>> Error en los parametros: ";
                    }
                } else {
                    query.realizarConsulta(parametros);
                    logDeInstruccion = "$ $ $ $ $ $ Se ejecutó la instrucción exitosamente";
                }

            } catch (SQLException e) {
                logDeInstruccion = ">>>>>> Error al hacer la consulta:" + "\n" + e.getMessage();
            }
        } else {
            logDeInstruccion = ">>>>>>>La instrucción no fué reconocida";
        }

        actualizarConsolaLogs(linea, logDeInstruccion,numeroLinea); // actualiza con lo que pasó en la instruccion

    }

    private void actualizarConsolaLogs(String linea, String log, int numeroLinea) {
        String aviso = "======================= Ejecutando la linea: "+numeroLinea+" ===============================" + "\n" + linea + "\n";
        String titulo = "                   RESULTADO:                      " + "\n";
        String espacios = "\n" + "\n";
        String nuevoTexto = aviso + titulo + log + espacios;
        consola.setTexto(nuevoTexto);
    }

}
