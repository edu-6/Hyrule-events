/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author edu
 */
public class EjecutadorDeInstrucciones implements Runnable {

    private File archivo;
    private int velocidad;

    public EjecutadorDeInstrucciones(File archivo, int velocidad) {
        this.archivo = archivo;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {

        try (FileReader reader = new FileReader(archivo);) {
            BufferedReader buffer = new BufferedReader(reader);
            String linea = buffer.readLine();

            while (linea != null) {
                this.ejecutarInstruccion(linea);
                Thread.sleep(velocidad);
                linea = buffer.readLine();
            }
            System.out.println("Terminado exitosamente");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }

    private void ejecutarInstruccion(String linea) {
        String instruccion = "";
        int indice = 0;
        while (linea.charAt(indice) != '(') {
            instruccion += linea.charAt(indice);
            indice++;
        }

        switch (instruccion) {
            case "REGISTRO_EVENTO":
                System.out.println("es un evento");
                break;
            case "REGISTRO_PARTICIPANTE":
                System.out.println("Es un registro de participante");
                break;
            case "INSCRIPCION":
                System.out.println("es una inscripcion");

                break;
            case "PAGO":
                System.out.println("Registro de un pago");
                break;
            case "VALIDAR_INSCRIPCION":
                System.out.println("Es validación de inscripcion");
                break;
                
            case "REGISTRO_ACTIVIDAD":
                System.out.println("Es registro de actividad");
                break;
            case "ASISTENCIA":
                System.out.println("Es registro de asistencia");
                break;
            case "CERTIFICADO":
                System.out.println("petición de certificado");
                break;
            case "REPORTE_PARTICIPANTES":
                System.out.println("reporte de participantes");
                break;
            case "REPORTE_ACTIVIDADES":
                System.out.println("reporte de actividades");
                break;
            case "REPORTE_EVENTOS":
                System.out.println("reporte de eventos");
                break;
            default:
                System.out.println("Operación no reconocida");
        }
    }

}
