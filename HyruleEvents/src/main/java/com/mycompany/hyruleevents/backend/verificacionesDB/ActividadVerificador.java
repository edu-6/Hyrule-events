/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.verificacionesDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ActividadVerificador extends VerificadorEnDB {
    
    private static final String SELECT_ACTIVIDAD = "SELECT * FROM actividad WHERE codigo = ?";
    private static final String SELECT_EVENTO = "SELECT * FROM evento WHERE codigo = ?";
    private static final String SELECT_PARTICIPANTE = "SELECT * FROM participante WHERE correo_electronico = ?";

    public ActividadVerificador(Connection connection) {
        super(connection);
    }

    @Override
    public void verificarValidezConsulta(String[] parametros) throws ExceptionEnDB {
        String codigoActividad = parametros[0];
        String codigoEvento = parametros[1];
        String correoParticipante = parametros[4];
        String cupoMaximo = parametros[7];
        
        int cuposNecesariosActividad = Integer.valueOf(cupoMaximo);
        int cuposParaActividades = 0; // los cupos que tiene el evento para una actividad
        
        
        try(PreparedStatement ps = connection.prepareStatement(SELECT_ACTIVIDAD)) {
            ps.setString(1, codigoActividad);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                throw new ExceptionEnDB("Ya existe la actividad");
            }
        } catch (SQLException e) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!"); // caso extremo
        }
        
        
        try(PreparedStatement ps = connection.prepareStatement(SELECT_EVENTO)) {
            ps.setString(1, codigoEvento);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new ExceptionEnDB("No se encontró el evento con codigo"+ codigoEvento);
            }else{
                cuposParaActividades = rs.getInt("cupos_diposnibles_evento"); // obtener los cupos
            }
            
            if(cuposParaActividades < cuposNecesariosActividad){
                throw new ExceptionEnDB("Los cupos de la actividad superan "
                        +"\n"+ "a los cupos disponibles para actividades en el evento");
            }
        } catch (SQLException e) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!"); // caso extremo
        }
        
        // para verificar el ponente
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PARTICIPANTE)) {
            String tipoDeParticipante;
            ps.setString(1, correoParticipante);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new ExceptionEnDB("No se encontró el participante con correo"+ correoParticipante);
            }else{
                tipoDeParticipante = rs.getString("tipo_de_participante"); // obtener los cupos
            }
            
            if(String.valueOf(tipoDeParticipante).equals("ASISTENTE")){
                throw new ExceptionEnDB("Un asistente no puede impartir una actividad! ");
                       
            }
        } catch (SQLException e) {
            throw new ExceptionEnDB(" Hubo un error al realizar la consulta!"); // caso extremo
        }
        
        
        
    }

}
