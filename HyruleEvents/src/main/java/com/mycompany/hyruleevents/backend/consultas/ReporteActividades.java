/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

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
            + "JOIN participante on  correo_electronico = correo_del_poniente WHERE codigo_evento = ?  ";

    private static final String FILTRO_TIPO = " tipo_actividad = ?";
    private static final String FILTRO_CORREO = " correo_del_poniente = ?";

    private static final String SELECT_FILTRO_TIPO = SELECT_ACTIVIDADES + " AND " + FILTRO_TIPO;
    private static final String SELECT_FILTRO_CORREO = SELECT_ACTIVIDADES + " AND " + FILTRO_CORREO;
    private static final String SELECT_AMBOS_FILTROS = SELECT_ACTIVIDADES + " AND " + FILTRO_TIPO + " AND " + FILTRO_CORREO;

    public ReporteActividades(Connection connection) {
        super(connection);
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
            generarSinFiltros();
        }else if (soloHayFiltroActividad) {
            filtrarPorTipoActividad();
        }else if (soloHayFiltroCorreo) {
            filtrarPorCorreo();
        }else if (hayAmbosFiltros) {
            aplicarTodosLosFiltros();
        }

    }
    
    private void generarSinFiltros(String codigoEVento) throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTIVIDADES)) {
            ps.setString(1, codigoEVento);
            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteParticipantes(rs, codigoEvento);
        }
        
    }
    private void filtrarPorTipoActividad() throws SQLException{
        
    }
    private void filtrarPorCorreo() throws SQLException {
        
    }
    
    private void aplicarTodosLosFiltros() throws SQLException{
        
    }

}
