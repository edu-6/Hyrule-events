/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend;

import java.io.File;

/**
 *
 * @author edu
 */
public class GestorDeArchivos {
    private File carpetaSalida;
    private File archivoDeTexto;
    private int velocidadDeProcesamiento;
    
    
    
    public void validarArchivoDeTexto() throws ArchivoNullException{
        if(archivoDeTexto == null){
            throw new ArchivoNullException();
        }
    }
    
    public void validarCarpetaDeSalida() throws CarpetaSalidaNullException{
        if(carpetaSalida == null){
            throw new CarpetaSalidaNullException();
        }
    }

    public File getCarpetaSalida() {
        return carpetaSalida;
    }

    public File getArchivoDeTexto() {
        return archivoDeTexto;
    }

    public int getVelocidadDeProcesamiento() {
        return velocidadDeProcesamiento;
    }
    
    

    public void setCarpetaSalida(File CarpetaSalida) {
        this.carpetaSalida = CarpetaSalida;
    }

    public void setArchivoDeTexto(File ArchivoDeTexto) {
        this.archivoDeTexto = ArchivoDeTexto;
    }

    public void setVelocidadDeProcesamiento(int velocidadDeProcesamiento) {
        this.velocidadDeProcesamiento = velocidadDeProcesamiento;
    }
    
    
    
    


    
    
}
