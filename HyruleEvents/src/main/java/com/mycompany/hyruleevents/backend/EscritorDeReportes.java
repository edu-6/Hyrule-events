/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class EscritorDeReportes {

    private GestorDeArchivos gestor;

    public EscritorDeReportes(GestorDeArchivos gestor) {
        this.gestor = gestor;
    }

    public void escribirCertificado(String nombreReporte, String nombre, String tipo, String titulo, String ubicacion, String fecha) {
        String html = String.format(
                "<html>\n"
                + "<head><meta charset='UTF-8'><title>Constancia</title></head>\n"
                + "<body style='font-family: Arial, sans-serif;'>\n"
                + "  <div style='border:1px solid #ccc; padding:20px; max-width:600px;'>\n"
                + "    <h2>Constancia de Participación</h2>\n"
                + "    <p><b>Nombre del participante:</b> %s</p>\n"
                + "    <p><b>Tipo de participante:</b> %s</p>\n"
                + "    <p><b>Título del evento:</b> %s</p>\n"
                + "    <p><b>Ubicación:</b> %s</p>\n"
                + "    <p><b>Fecha:</b> %s</p>\n"
                + "  </div>\n"
                + "</body>\n"
                + "</html>",
                nombre, tipo, titulo, ubicacion, fecha
        );

        escribirArchivo(html, nombreReporte);
    }

    public void escribirReporteParticipantes(ResultSet rs, String tituloEvento) throws SQLException {
        String filas = agregarFilasParticipantes(rs);
        String html
                = "<html>\n"
                + "<head><meta charset='UTF-8'><title>Rreporte de participantes </title></head>\n"
                + "<body style='font-family: Arial, sans-serif;'>\n"
                + "  <div style='border:1px solid #ccc; padding:20px; max-width:600px;'>\n"
                + "    <h2> Reporte de participantes a " + tituloEvento + "</h2>\n"
                + "<table border=\"1\">\n"
                + "    <tr>\n"
                + "      <th>Correo electrónico</th>\n"
                + "      <th>Tipo participante </th>\n"
                + "      <th>Nombre </th>\n"
                + "      <th>Institucion de procedencia</th>\n"
                + "      <th>Fue validada</th>\n"
                + "    </tr>\n"
                + filas // se agregan las filas generadas
                + "  </table>\n"
                + "  </div>\n"
                + "</body>\n"
                + "</html>";

        escribirArchivo(html, tituloEvento + "Participantes");

    }

    public String agregarFilasParticipantes(ResultSet rs) throws SQLException {

        String filas = "";
        while (rs.next()) {
            System.out.println("resultado encontrado");
            String correo = rs.getString("correo_participante");
            String tipoParticipante = rs.getString("tipo_de_participante");
            String nombre = rs.getString("nombre");
            String institucionProcedencia = rs.getString("institucion_de_procedencia");
            String valida = String.valueOf(rs.getInt("validada"));

            filas += "<tr>\n"
                    + "      <td>" + correo + "</td>\n"
                    + "      <td>" + tipoParticipante + "</td>\n"
                    + "      <td>" + nombre + "</td>\n"
                    + "      <td>" + institucionProcedencia + "</td>\n"
                    + "      <td>" + valida + "</td>\n"
                    + "</tr>\n";

        }
        return filas;
    }

    private void escribirArchivo(String contenido, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(gestor.getCarpetaSalida().getAbsolutePath() + "/" + nombreArchivo + ".html")) {
            writer.write(contenido);
        } catch (IOException e) {

        }
    }

}
