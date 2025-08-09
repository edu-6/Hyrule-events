/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hyruleevents.fronted;

import com.mycompany.hyruleevents.backend.CreadorDeTablas;
import com.mycompany.hyruleevents.backend.DBConnection;
import com.mycompany.hyruleevents.backend.EjecutadorDeInstrucciones;
import com.mycompany.hyruleevents.backend.GestorDeArchivos;
import java.awt.GridLayout;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author edu
 */
public class HyruleInterfaz extends javax.swing.JFrame {
    // frontend
    private LoginPanel loginPanel;
    private JPanel panelActual;
    
    //Backend
    private GestorDeArchivos gestorDeArchivos;
    private DBConnection dbConexion;
    
    /**
     * Creates new form HyruleInterfaz
     */
    public HyruleInterfaz() {
        initComponents();
        //Frontend
        this.setLocationRelativeTo(null);
        this.fondoPanel.setLayout(new GridLayout());
        this.loginPanel = new LoginPanel(this);
        this.panelActual = loginPanel;
        this.añadirPanel(loginPanel);
        
        //Backend
        this.gestorDeArchivos = new GestorDeArchivos();
    }
    
    public void crearTablas(){
        CreadorDeTablas creador = new CreadorDeTablas();
        creador.crearTablas(dbConexion.getConnection());
    }
    
    public void mostrarCargadorDeArchivPanel(){
        crearTablas();
        limpiarFrame();
        CargaDeArchivoPanel cargaDeArchivo = new CargaDeArchivoPanel(this);
        añadirPanel(cargaDeArchivo);
    }
    
    
    public void ejecutarArchivoDeTexto(){
        
        File archivo = gestorDeArchivos.getArchivoDeTexto();
        int velocidad = gestorDeArchivos.getVelocidadDeProcesamiento();
        EjecutadorDeInstrucciones ejecutador = new EjecutadorDeInstrucciones(archivo,velocidad);
        Thread hilo = new Thread(ejecutador);
        hilo.start();
    }
    
    private void limpiarFrame(){
        fondoPanel.remove(panelActual);   
        fondoPanel.repaint();
    }
    
    private void añadirPanel(JPanel panel){
        limpiarFrame();
        fondoPanel.add(panel);
        fondoPanel.revalidate();
        fondoPanel.repaint();
        this.pack();
        revalidate();
        repaint();
        this.panelActual = panel;
    }

    public GestorDeArchivos getGestorDeArchivos() {
        return gestorDeArchivos;
    }
     public void setConexion(DBConnection conexion) {
        this.dbConexion = conexion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout fondoPanelLayout = new javax.swing.GroupLayout(fondoPanel);
        fondoPanel.setLayout(fondoPanelLayout);
        fondoPanelLayout.setHorizontalGroup(
            fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );
        fondoPanelLayout.setVerticalGroup(
            fondoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fondoPanel;
    // End of variables declaration//GEN-END:variables
}
