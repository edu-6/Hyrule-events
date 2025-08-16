/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author edu
 */
public class EscritorDeReportes {

    private GestorDeArchivos gestor;

    public EscritorDeReportes(GestorDeArchivos gestor) {
        this.gestor = gestor;
    }

    public void escribirCertificado(String nombreReporte,String nombre, String tipo, String titulo, String ubicacion, String fecha) {
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
        
        try(FileWriter writer = new FileWriter(gestor.getCarpetaSalida().getAbsolutePath()+"/"+nombreReporte+".html")){
            writer.write(html);
        }catch(IOException e){
            
        }
    }

}
