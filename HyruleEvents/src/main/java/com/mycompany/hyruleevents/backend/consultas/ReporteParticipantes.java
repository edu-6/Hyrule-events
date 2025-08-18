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
public class ReporteParticipantes extends ConsultaSQL {

    private static final String SELECT_INSCRIPCION = 
    "SELECT * " +
    "FROM inscripcion " +
    "JOIN participante ON participante.correo_electronico = inscripcion.correo_participante " +
    "WHERE inscripcion.codigo_evento = ?";

    private static final String FILTRO_TIPO = " participante.tipo_de_participante = ?";

    private static final String FILTRO_PROCENDENCIA = " participante.institucion_de_procedencia = ?";

    private static final String SELECT_INSCRIPCION_TIPO = SELECT_INSCRIPCION + " AND " + FILTRO_TIPO;// por tipo
    private static final String SELECT_INSCRIPCION_PROCEDENCIA = SELECT_INSCRIPCION + " AND " + FILTRO_PROCENDENCIA; // por procedencia
    private static final String SELECT_INSCRIPCION_AMBOS_FILTROS = SELECT_INSCRIPCION + " AND " + FILTRO_TIPO + " AND " + FILTRO_PROCENDENCIA; // todos los filtros

    private EscritorDeReportes escritor;

    public ReporteParticipantes(EscritorDeReportes escritorDeReportes, Connection connection) {
        super(connection);
        this.escritor = escritorDeReportes;
    }

    @Override
    public void realizarConsulta(String[] parametros) throws SQLException {
        String codigoEvento = parametros[0];
        String tipoParticipante = parametros[1];
        String institucionProcedencia = parametros[2];

        boolean noHayFiltro = tipoParticipante.isBlank() && institucionProcedencia.isBlank();
        boolean soloHayFiltroParticipante = !tipoParticipante.isBlank() && institucionProcedencia.isBlank();
        boolean soloHayFiltroProcedencia = tipoParticipante.isBlank() && !institucionProcedencia.isBlank();
        boolean hayTodosLosFiltros = !tipoParticipante.isBlank() && !institucionProcedencia.isBlank();

        if (noHayFiltro) { // si no hay ningun filtro hacer normal
            generarSinFiltros(codigoEvento);
        } else if (soloHayFiltroParticipante) { // si hay filtro de participante
            filtrarPorTipo(codigoEvento, tipoParticipante);
        } else if (soloHayFiltroProcedencia) { // si hay filtro de  procedencia
            filtarPorProcedencia(codigoEvento, institucionProcedencia);
        } else if (hayTodosLosFiltros) { // si hay dos filtros
            aplicarTodosLosFiltros(codigoEvento, tipoParticipante, institucionProcedencia);
        }

        // solo el insert cambia, todos los atributos son los mismos solo el inset cambia!
    }

    private void generarSinFiltros(String codigoEvento) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_INSCRIPCION)) {
            ps.setString(1, codigoEvento);
            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteParticipantes(rs, codigoEvento);
        }
    }

    private void filtrarPorTipo(String codigoEvento, String tipoParticipante) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_INSCRIPCION_TIPO)) {
            ps.setString(1, codigoEvento);
            ps.setString(2, tipoParticipante);

            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteParticipantes(rs, codigoEvento);
        }

    }

    private void filtarPorProcedencia(String codigoEvento, String procedencia) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_INSCRIPCION_PROCEDENCIA)) {
            ps.setString(1, codigoEvento);
            ps.setString(2, procedencia);

            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteParticipantes(rs, codigoEvento);
        }

    }

    private void aplicarTodosLosFiltros(String codigoEvento, String tipoParticipante, String procedencia) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_INSCRIPCION_AMBOS_FILTROS)) {
            ps.setString(1, codigoEvento);
            ps.setString(2, tipoParticipante);
            ps.setString(3, procedencia);

            ResultSet rs = ps.executeQuery();
            escritor.escribirReporteParticipantes(rs, codigoEvento);
        }
    }

}
