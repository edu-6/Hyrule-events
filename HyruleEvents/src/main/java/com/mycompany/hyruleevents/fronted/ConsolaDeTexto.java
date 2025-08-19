/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.hyruleevents.fronted;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author edu
 */
public class ConsolaDeTexto extends javax.swing.JPanel {

    private JTextArea consola;
    private JScrollPane consolaPane;
    private JButton botonContinuar;
    private HyruleInterfaz ventanaPrincipal;

    public ConsolaDeTexto(HyruleInterfaz ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(1000, 600));
        consola = new JTextArea();
        consola.setFont(new Font("Monospaced", Font.PLAIN, 20));
        consola.setBackground(Color.lightGray);
        consola.setForeground(Color.darkGray);
        consola.setEditable(false);
        botonContinuar = new JButton("Continuar");
        botonContinuar.setBackground(Color.ORANGE);
        this.add(botonContinuar, BorderLayout.NORTH);
        this.botonContinuar.setEnabled(false);

        consolaPane = new JScrollPane(consola, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(consolaPane, BorderLayout.CENTER);

        botonContinuar.addActionListener(e -> {
            solicitarCambioDeFrame();
        });
    }

    public void setTexto(String texto) {
        String textoAnterior = consola.getText();
        consola.setText(textoAnterior + "\n" + texto);
    }

    public void activarBtnSiguente() {
        this.botonContinuar.setEnabled(true);
    }

    public void ponerTitulo(String nombreArchivo) {
        String titulo = "======================= NOMBRE DEL ARCHIVO: " + nombreArchivo + "=======================";
        String espacio = "\n" + "\n" + "\n" + "\n";
        consola.setText(titulo + espacio);
    }
    
    private void solicitarCambioDeFrame(){
        this.ventanaPrincipal.mostrarFormulariosDeConsultas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBase = new javax.swing.JPanel();

        javax.swing.GroupLayout panelBaseLayout = new javax.swing.GroupLayout(panelBase);
        panelBase.setLayout(panelBaseLayout);
        panelBaseLayout.setHorizontalGroup(
            panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelBaseLayout.setVerticalGroup(
            panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelBase;
    // End of variables declaration//GEN-END:variables
}
