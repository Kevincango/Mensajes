/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajes_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class MensajeDAO {

    public static void crearMensajeDB(Mensaje mensaje) {
        Conection conection = new Conection();

        try (Connection db = conection.getConnection()) {
            PreparedStatement ps = null;
            try {
                String query = "insert into Mensajes (Mensaje, Autor_Mensaje) values (?,?)";

                ps = db.prepareStatement(query);
                ps = db.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setString(2, mensaje.getAutorMensaje());
                ps.executeUpdate();
                System.out.println("mensaje creado");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static ArrayList<Mensaje> leerMensajeDB() {
        Conection conection = new Conection();
        ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
        try(Connection db = conection.getConnection()) {
            String query = "SELECT * FROM Mensajes";

            PreparedStatement preparedStatement = db.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mensaje registro = new Mensaje();
                registro.setId(rs.getInt("ID_Mensaje"));
                registro.setMensaje(rs.getString("Mensaje"));
                registro.setAutorMensaje(rs.getString("Autor_Mensaje"));
                registro.setFechaMensaje(rs.getString("Fecha_Mensaje"));
                
                mensajes.add(registro);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return mensajes;
    }

    public static void borrarMensajeDB(int idMensaje) {
        
        Conection conection = new Conection();

        try (Connection db = conection.getConnection()) {
            PreparedStatement ps = null;
            
            try{
                
                String query = "delete from Mensajes where ID_Mensaje = ?";
                ps = db.prepareStatement(query);
                ps.setInt(1, idMensaje);
                int changes = ps.executeUpdate();
                if(changes != 0){
                    System.out.println("El mensaje ha sido borrado");
                }else{
                    System.out.println("No se encontraron mensajes con el id ");
                }
                
                
            }catch(SQLException e){
                System.out.println(e);
                
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }

    public static void actualizarMensajeDB(Mensaje mensaje) {
        
        Conection conection = new Conection();

        try (Connection db = conection.getConnection()) {
            PreparedStatement ps = null;
            
            try{
                String query = "update Mensajes set Mensaje = ? where ID_Mensaje = ?";
                ps = db.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setInt(2, mensaje.getId());
                ps.executeUpdate();
                System.out.println("Mensaje actualizado");
                
            }catch(SQLException e){
                System.out.println("No se pudo actualizar");
                System.out.println(e);
            }
            
        }catch(SQLException e){
            System.out.println(e);
            
        }

    }

}
