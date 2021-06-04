/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajes_app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kevin
 */
public class MensajeService {

    public static void crearMensaje() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu mensaje\n");
        String mensaje = sc.nextLine();
        System.out.println("Ingresa tu nombre");
        String nombre = sc.nextLine();

        Mensaje registro = new Mensaje();
        registro.setMensaje(mensaje);
        registro.setAutorMensaje(nombre);
        MensajeDAO.crearMensajeDB(registro);

    }

    public static void listarMensaje() {

        System.out.println("Historial de mensajes");
        ArrayList<Mensaje> mensajes = MensajeDAO.leerMensajeDB();

        mensajes.stream().forEach((mensaje) -> {

            System.out.println("ID: " + mensaje.getId());
            System.out.println("Mensaje: " + mensaje.getMensaje());
            System.out.println("Author: " + mensaje.getAutorMensaje());
            System.out.println("Fecha: " + mensaje.getFechaMensaje());
        });

    }

    public static void borrarMensaje() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el id del mensaje a eliminar");
        int option = sc.nextInt();
        
        MensajeDAO.borrarMensajeDB(option);

    }

    public static void editarMensaje() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nuevo mensaje");
        String mensaje = sc.nextLine();
        System.out.println("Escribe el id del mensaje ha actualizar");
        int idMensaje = sc.nextInt();
        
        Mensaje actualizacion = new Mensaje();
        actualizacion.setId(idMensaje);
        actualizacion.setMensaje(mensaje);
        MensajeDAO.actualizarMensajeDB(actualizacion);

    }

}
