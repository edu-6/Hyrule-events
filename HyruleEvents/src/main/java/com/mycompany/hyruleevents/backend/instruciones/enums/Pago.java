/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones.enums;

/**
 *
 * @author edu
 */
public enum Pago {
    EFECTIVO("EFECTIVO"),
    TRANSFERENCIA("TRASNFERENCIA"),
    TARJETA("TARJETA");

    private final String tipoPago;

    private Pago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }
}
