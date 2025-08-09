/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author edu
 */
public class CreadorDeTablas {

    public void crearTablas(Connection conexion) {
        crearParticipante(conexion);
        crearEvento(conexion);
        crearActividad(conexion);
        crearPago(conexion);
        crearInscripcion(conexion);
        crearAsistencia(conexion);

    }

    private void crearParticipante(Connection conexion) {
        try {
            Statement stmTablaParticipante = conexion.createStatement();
            stmTablaParticipante.executeUpdate("CREATE TABLE IF NOT EXISTS participante ("
                    + "nombre VARCHAR(44) NOT NULL,"
                    + "correo_electronico VARCHAR(20) NOT NULL,"
                    + "institucion_de_procedencia VARCHAR(149) NOT NULL,"
                    + "tipo_de_participante VARCHAR(10) NOT NULL,"
                    + "CONSTRAINT pk_participante PRIMARY KEY (correo_electronico)"
                    + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void crearEvento(Connection conexion) {
        try {
            Statement stmTablaEvento = conexion.createStatement();
            stmTablaEvento.executeUpdate("CREATE TABLE IF NOT EXISTS evento ("
                    + "    titulo_evento VARCHAR(20) NOT NULL,"
                    + "    tipo_evento VARCHAR(10) NOT NULL,"
                    + "    fecha_evento DATE NOT NULL,"
                    + "    codigo VARCHAR(10) NOT NULL,"
                    + "    cupo_maximo_evento INTEGER NOT NULL,"
                    + "    ubicacion VARCHAR(149) NOT NULL,"
                    + "    CONSTRAINT pk_evento PRIMARY KEY (codigo)"
                    + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void crearActividad(Connection conexion) {
        try {
            Statement stmTablaActividad = conexion.createStatement();
            stmTablaActividad.executeUpdate("CREATE TABLE IF NOT EXISTS actividad ("
                    + "    titulo_actividad VARCHAR(20) NOT NULL,"
                    + "    tipo_actividad VARCHAR(10) NOT NULL,"
                    + "    codigo_evento VARCHAR(10) NOT NULL,"
                    + "    codigo VARCHAR(10) NOT NULL,"
                    + "    cupo_maximo_actividad INTEGER NOT NULL,"
                    + "    hora_inicio TIME NOT NULL,"
                    + "    hora_fin TIME NOT NULL,"
                    + "    correo_del_poniente VARCHAR(25) NOT NULL,"
                    + "    CONSTRAINT pk_actividad PRIMARY KEY (codigo),"
                    + "    CONSTRAINT fk_actividad_correo_del_poniente FOREIGN KEY (correo_del_poniente) REFERENCES participante (correo_electronico),"
                    + "    CONSTRAINT fk_actividad_codigo_evento FOREIGN KEY (codigo_evento) REFERENCES evento (codigo)"
                    + ")");
        } catch (SQLException e) {
        }
    }

    private void crearPago(Connection conexion) {
        try {
            Statement stmTablaPago = conexion.createStatement();
            stmTablaPago.executeUpdate("CREATE TABLE IF NOT EXISTS pago ("
                    + "    correo_participante VARCHAR(20) NOT NULL,"
                    + "    codigo_evento VARCHAR(10) NOT NULL,"
                    + "    metodo_pago VARCHAR(7) NOT NULL,"
                    + "    monto INTEGER NOT NULL,"
                    + "    CONSTRAINT pk_pago PRIMARY KEY (correo_participante, codigo_evento),"
                    + "    CONSTRAINT fk_pago_correo_participante FOREIGN KEY (correo_participante) REFERENCES participante (correo_electronico),"
                    + "    CONSTRAINT fk_pago_codigo_evento FOREIGN KEY (codigo_evento) REFERENCES evento (codigo)"
                    + ")");
        } catch (SQLException e) {
        }
    }

    private void crearInscripcion(Connection conexion) {
        try {
            Statement stmTablaInscripcion = conexion.createStatement();
            stmTablaInscripcion.executeUpdate("CREATE TABLE IF NOT EXISTS inscripcion ("
                    + "    correo_participante VARCHAR(20) NOT NULL,"
                    + "    codigo_evento VARCHAR(10) NOT NULL,"
                    + "    tipo_de_inscripcion VARCHAR(7) NOT NULL,"
                    + "    CONSTRAINT pk_inscripcion PRIMARY KEY (correo_participante, codigo_evento),"
                    + "    CONSTRAINT fk_inscripcion_correo_participante FOREIGN KEY (correo_participante) REFERENCES participante (correo_electronico),"
                    + "    CONSTRAINT fk_inscripcion_codigo_evento FOREIGN KEY (codigo_evento) REFERENCES evento (codigo)"
                    + ")");
        } catch (SQLException e) {
        }
    }

    private void crearAsistencia(Connection conexion) {
        try {
            Statement stmTablaAsistencia = conexion.createStatement();
            stmTablaAsistencia.executeUpdate("CREATE TABLE IF NOT EXISTS asistencia("
                    + "    correo_del_participante VARCHAR(30) NOT NULL,"
                    + "    codigo_actividad VARCHAR(20) NOT NULL,"
                    + "    CONSTRAINT pk_asistencia PRIMARY KEY (correo_del_participante, codigo_actividad),"
                    + "    CONSTRAINT fk_asistencia_correo_participante FOREIGN KEY (correo_del_participante) REFERENCES participante (correo_electronico),"
                    + "    CONSTRAINT fk_asistencia_codigo_actividad FOREIGN KEY (codigo_actividad) REFERENCES actividad (codigo)"
                    + ")");
        } catch (SQLException e) {
        }
    }

}
