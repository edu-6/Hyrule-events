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
public class ReporteActividades extends ConsultaSQL {

    private static final String SELECT_ACTIVIDADES = "SELECT * FROM actividad"
            + " JOIN participante on  correo_electronico = correo_del_poniente WHERE codigo_evento = ?  ";

    private static final String FILTRO_TIPO = " tipo_actividad = ?";
    private static final String FILTRO_CORREO = " correo_del_poniente = ?";

    private static final String SELECT_FILTRO_TIPO = SELECT_ACTIVIDADES + " AND " + FILTRO_TIPO;
    private static final String SELECT_FILTRO_CORREO = SELECT_ACTIVIDADES + " AND " + FILTRO_CORREO;
    private static final String SELECT_AMBOS_FILTROS = SELECT_ACTIVIDADES + " AND " + FILTRO_TIPO + " AND " + FILTRO_CORREO;

    
    private EscritorDeReportes escritor;
    public ReporteActividades(EscritorDeReportes escritor,Connection connection) {
        super(connection);
        this.escritor = escritor;
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String codigoEvento = parametros[0];
        String tipoActividad = parametros[1];
        String correoPoniente = parametros[2];

        boolean noHayFiltro = tipoActividad.isBlank() && correoPoniente.isBlank();
        boolean soloHayFiltroActividad = !tipoActividad.isBlank() && correoPoniente.isBlank();
        boolean soloHayFiltroCorreo = tipoActividad.isBlank() && !correoPoniente.isBlank();
        boolean hayAmbosFiltros = !tipoActividad.isBlank() && !correoPoniente.isBlank();

        if (noHayFiltro) {
            generarSinFiltros(codigoEvento);
        }else if (soloHayFiltroActividad) {
            filtrarPorTipoActividad(codigoEvento, tipoActividad);
        }else if (soloHayFiltroCorreo) {
            filtrarPorCorreo(codigoEvento, correoPoniente);
        }else if (hayAmbosFiltros) {
            aplicarTodosLosFiltros(codigoEvento, tipoActividad, correoPoniente);
        }

    }
    
    private void generarSinFiltros(String codigoEvento) throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTIVIDADES)) {
            ps.setString(1, codigoEvento);
            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteActividades(rs,codigoEvento);
        }
        
    }
    private void filtrarPorTipoActividad(String codigoEvento, String tipoActividad) throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FILTRO_TIPO)) {
            ps.setString(1, codigoEvento);
            ps.setString(2, tipoActividad);
            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteActividades(rs, codigoEvento);
        }
        
    }
    private void filtrarPorCorreo(String codigoEvento, String correoPoniente) throws SQLException {
         try (PreparedStatement ps = connection.prepareStatement(SELECT_FILTRO_CORREO)) {
            ps.setString(1, codigoEvento);
            ps.setString(2, correoPoniente);
            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteActividades(rs,codigoEvento);
        }
    }
    
    private void aplicarTodosLosFiltros(String codigoEvento, String tipoActividad, String correoPoniente) throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_AMBOS_FILTROS)) {
            ps.setString(1, codigoEvento);
            ps.setString(2, tipoActividad);
            ps.setString(3, correoPoniente);
            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteActividades(rs,codigoEvento);
        }
    }

}
