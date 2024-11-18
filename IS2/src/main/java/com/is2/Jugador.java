package com.is2;

import java.util.*;
import java.io.*;

public class Jugador extends User {
    private String telefono;
    private String nombre;
    private String apellidos;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public boolean setUsername(String nUsuario) {
        boolean usrLibre = false;
        if (usrLibre = checkUsername(nUsuario)) {
            String lineaActual = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                    + this.telefono + ";" + this.email + ";";
            String lineaNueva = nUsuario + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                    + this.telefono + ";" + this.email + ";";
            File modFile = new File("files/usuarios.txt");
            ModificarFichero.modificar(modFile, lineaActual, lineaNueva);
            this.username = nUsuario;
        }
        return usrLibre;
    }

    public void setPassword(String password) {
        String lineaActual = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        String lineaNueva = this.username + ";" + password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        File modFile = new File("files/usuarios.txt");
        ModificarFichero.modificar(modFile, lineaActual, lineaNueva);
        this.password = password;
    }

    public void setNombre(String nombre) {
        String lineaActual = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        String lineaNueva = this.username + ";" + this.password + ";" + nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        File modFile = new File("files/usuarios.txt");
        ModificarFichero.modificar(modFile, lineaActual, lineaNueva);
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        String lineaActual = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        String lineaNueva = this.username + ";" + this.password + ";" + this.nombre + ";" + apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        File modFile = new File("files/usuarios.txt");
        ModificarFichero.modificar(modFile, lineaActual, lineaNueva);
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        String lineaActual = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        String lineaNueva = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + telefono + ";" + this.email + ";";
        File modFile = new File("files/usuarios.txt");
        ModificarFichero.modificar(modFile, lineaActual, lineaNueva);
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        String lineaActual = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + this.email + ";";
        String lineaNueva = this.username + ";" + this.password + ";" + this.nombre + ";" + this.apellidos + ";"
                + this.telefono + ";" + email + ";";
        File modFile = new File("files/usuarios.txt");
        ModificarFichero.modificar(modFile, lineaActual, lineaNueva);
        this.email = email;
    }

    public Jugador() {

    }

    /* Sigin en la aplicacion, no lo puede hacer un admin */
    public boolean signin(String nombre, String apellidos, String telefono, String mail, String nUsuario,
            String password) {
                if(nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || mail.isEmpty() || nUsuario.isEmpty() || password.isEmpty()){
                    return false;
                }
        /* Comprueba si que no sea admin o que no exista el username */
        if (admUser.equals(nUsuario) && admPwd.equals(password) || checkUsername(nUsuario)) {
            return false;
        } else {
            return addUser(nUsuario, password, nombre, apellidos, telefono, mail);
        }
    }

    public boolean addUser(String nUsuario, String password, String nombre, String apellidos, String telefono,
            String email) {
        File archivo = new File("files/usuarios.txt");
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
            // Verificar si el archivo ya existe o no
            if (!archivo.exists()) {
                archivo.createNewFile(); // si no lo existe loc crea
            }

            // Escribir las variables en el archivo
            escritor.write(
                    nUsuario + ";" + password + ";" + nombre + ";" + apellidos + ";" + telefono + ";" + email + ";");
            escritor.newLine();
            escritor.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
        return true;
    }

    /* Devuelve los torneos en los que ha participado el jugador */
    public ArrayList<Torneo> getTorneosJugados() {
        return null;
    }

    /* Metodo que devuelve los torneos en los que está inscrito */
    public ArrayList<Torneo> getTorneosInscrito() {
        return null;
    }

    /* Devuelve los torneos en los que va a jugar */
    public ArrayList<Torneo> getTorneosAJugar() {
        return null;
    }

    /* Vizualizar los torneos obtenidos en un torneo */
    public int getPuntosTorneo(Torneo torneo) {
        return 0;
    }

    /* Ver la posicion que quedo en un torneo */
    public int getPosicionTorneo(Torneo torneo) {
        return 0;
    }

}