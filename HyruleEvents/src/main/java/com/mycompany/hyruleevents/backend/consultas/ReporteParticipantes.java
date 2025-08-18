/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.consultas;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ReporteParticipantes extends ConsultaSQL {

    private static final String SELECT_INSCRIPCION = "SELECT * FROM inscripcion"
            + " WHERE codigo_evento = ?"
            + " JOIN participante on inscripcion.correo_participante = participante.correo_electronico";

    private static final String FILTRO_TIPO = " AND participante.tipo_de_participante = ?";

    private static final String FILTRO_PROCENDENCIA = " AND participante.institucion_de_procedencia = ?";

    private static final String SELECT_INSCRIPCION_TIPO = SELECT_INSCRIPCION + FILTRO_TIPO;// por tipo
    private static final String SELECT_INSCRIPCION_PROCEDENCIA = SELECT_INSCRIPCION + FILTRO_PROCENDENCIA; // por procedencia
    private static final String SELECT_INSCRIPCION_AMBOS_FILTROS = SELECT_INSCRIPCION + FILTRO_TIPO + FILTRO_PROCENDENCIA; // todos los filtros

    public ReporteParticipantes(Connection connection) {
        super(connection);
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

        } else if (soloHayFiltroParticipante) { // si hay filtro de participante

        } else if (soloHayFiltroProcedencia) { // si hay filtro de  procedencia

        } else if (hayTodosLosFiltros) { // si hay dos filtros

        }
        
        
        // solo el insert cambia, todos los atributos son los mismos solo el inset cambia!
    }
    
    
}
