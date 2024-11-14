import java.util.*;
import java.io.*;

public class Jugador extends User {
    int telefono;

    
    public Jugador (String nUsuario, String password, int telefono) {
        login (nUsuario, password);
        this.telefono = telefono;
    }

    public String getnUsuario() {
        return nUsuario;
    }

    public String getPassword() {
        return password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setnUsuario(String nUsuario) {
        String lineaActual = this.nUsuario + ";" + this.password + ";" + this.telefono + ";";
        String lineaNueva = nUsuario + ";" + this.password + ";" + this.telefono + ";";
        File modFile = new File("usuarios.txt");
        GestionFichero.modificar(modFile, lineaActual, lineaNueva);
        this.nUsuario = nUsuario;
    }

    public void setPassword(String password) {
        String lineaActual = this.nUsuario + ";" + this.password + ";" + this.telefono + ";";
        String lineaNueva = this.nUsuario + ";" + password + ";" + this.telefono + ";";
        File modFile = new File("usuarios.txt");
        GestionFichero.modificar(modFile, lineaActual, lineaNueva);
        this.password = password;
    }

    public void setTelefono(int telefono) {
        String lineaActual = this.nUsuario + ";" + this.password + ";" + this.telefono + ";";
        String lineaNueva = this.nUsuario + ";" + this.password + ";" + telefono + ";";
        File modFile = new File("usuarios.txt");
        GestionFichero.modificar(modFile, lineaActual, lineaNueva);
        this.telefono = telefono;
    }

    public ArrayList<Torneo> getTorneosJugados() { //Metodo que devuelve los torneos en los que ha participado el jugador
        return null;
    }
    
    
    public ArrayList<Torneo> getTorneosInscrito() { //Metodo que devuelve los torneos en los que está inscrito el jugador
        return null;
    }

    public ArrayList<Torneo> getTorneosAJugar() { //Metodo que devuelve los torneos que finalmente va a jugar el jugador
        return null;
    }

    public int getPuntosTorneo(Torneo torneo) { //puede visualizar los puntos obtenidos en un torneo
        return 0;
    }

    public int getPosicionTorneo(Torneo torneo) { //puede visualizar la posición en la que ha quedado en un torneo
        return 0;
    }

}