/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import com.mycompany.hyruleevents.backend.EscritorDeReportes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class CertificadoMaker extends ConsultaSQL {

    private static final String SELECT_PARTICIPANTE = "SELECT *FROM participante WHERE correo_electronico = ?";
    private static final String SELECT_EVENTO = "SELECT *FROM evento WHERE codigo = ?";
    
    
    private EscritorDeReportes escritorDeReportes;

    public CertificadoMaker(EscritorDeReportes escritorDeReportes, Connection connection) {
        super(connection);
        this.escritorDeReportes = escritorDeReportes;
    }
    

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String correo = parametros[0];
        String codigoEvento = parametros[1];
        
        
        String nombreParticipante="";
        String tipoParticipante = "";
        String tituloEvento = "cosas";
        String ubicacion = "tres";
        String fechaEvento = "agua";
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PARTICIPANTE)){
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                nombreParticipante =  rs.getString("nombre");
                tipoParticipante =  rs.getString("tipo_de_participante");
            }
        }
        
        try(PreparedStatement ps = connection.prepareStatement(SELECT_EVENTO)){
            ps.setString(1, codigoEvento);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                tituloEvento =  rs.getString("titulo_evento");
                ubicacion =  rs.getString("ubicacion");
                fechaEvento =  rs.getString("fecha_evento");
            }
        }
        
        String nombreReporte = correo +"-"+codigoEvento+"-"+fechaEvento;
        escritorDeReportes.escribirCertificado(nombreReporte, nombreParticipante, tipoParticipante, tituloEvento, ubicacion, fechaEvento,correo);
        
        
    }
    
    
    

}
