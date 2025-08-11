/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones;

/**
 *
 * @author edu
 */
public class ValidadorDeFecha {

    /**
     * Veririca que el string sea en formato de fecha f/f/f
     * y luego devuelve el string en formato aa/mm/dd si está correcto
     * @param cadena
     * @return 
     */
    public String fechaValida(String cadena) {
        String [] partesFecha = new String [3];
        int indice = 0;
        int cantidadBarras=0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != '/') {
                partesFecha[indice]+=cadena.charAt(i);
            } else {
                cantidadBarras++;
                indice++;
            }
        }
        
        if(cantidadBarras == 3){
            return partesFecha[2]+"/"+partesFecha[1]+"/"+partesFecha[0];
        }
        return null;
    }
    
}
