package com.is2;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Jugador extends User {
    private String telefono;
    private String nombre;
    private String apellidos;
    private String email;
    private Map<String, Integer[]> torneosParticipados = new HashMap<>();
    private int[] inscripciones; /* contiene el id a los torneos inscritos */
    private int[] participaciones; /* contiene el id a los torneos que participa */

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

    @SuppressWarnings("unchecked")
    public boolean setUsername(String nUsuario) {
        boolean result = false;
        JSONObject jugadoresJSON = cargarJSON();
        if (jugadoresJSON == null) {
            return false;
        }
        if (!checkUsername(nUsuario)) {
            JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");
            for (Object obj : usuarios) {
                JSONObject user = (JSONObject) obj;
                if (user.get("username").equals(this.username)) {
                    user.put("username", nUsuario);
                    guardarJSON(jugadoresJSON);
                    this.username = nUsuario;
                }
            }
            result = true;
        }
        return result;
    }

    public void setPassword(String password) {
        modificarCampoJSON("password", password);
        this.password = password;
    }

    public void setNombre(String nombre) {
        modificarCampoJSON("nombre", nombre);
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        modificarCampoJSON("apellidos", apellidos);
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        modificarCampoJSON("telefono", telefono);
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        modificarCampoJSON("email", email);
        this.email = email;
    }

    @SuppressWarnings("unchecked")
    public void setTorneosParticipados(Map<String, Integer[]> jugados) {
        this.torneosParticipados = jugados;
        JSONObject jugadoresJSON = cargarJSON();
        if (jugadoresJSON == null)
            return;

        JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");

        /* Modificar el usuario */
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size() && !encontrado; i++) {
            JSONObject user = (JSONObject) usuarios.get(i);
            if (user.get("username").equals(this.username)) {
                encontrado = true;
                JSONArray jugadosArray = (JSONArray) user.get("Jugados");
                jugadosArray.clear();
                Set<Entry<String, Integer[]>> jugadosSet = jugados.entrySet();

                /* Cargar los torneos jugados en el JSON */
                for (Entry<String, Integer[]> torneo : jugadosSet) {
                    String fecha = torneo.getKey();
                    Integer[] values = torneo.getValue();
                    JSONObject torneoJSON = new JSONObject();
                    torneoJSON.put("fecha", fecha);
                    torneoJSON.put("id", values[0]);
                    torneoJSON.put("puntos", values[1]);
                    torneoJSON.put("posicion", values[2]);
                    torneoJSON.put("Sets", values[3]);
                    torneoJSON.put("Juegos ganados", values[4]);
                    torneoJSON.put("Juegos perdidos", values[5]);
                    jugadosArray.add(torneoJSON);
                }
                user.put("Jugados", jugadosArray);
                usuarios.set(i, user);
            }
        }
        jugadoresJSON.put("Usuarios", usuarios);
        guardarJSON(jugadoresJSON);
    }

    @SuppressWarnings("unchecked")
    public void addTorneoParticipado(Entry<String, Integer[]> torneo) {
        JSONObject jugadoresJSON = cargarJSON();
        if (jugadoresJSON == null)
            return;

        JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");

        /* Modificar el usuario */
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size() && !encontrado; i++) {
            JSONObject user = (JSONObject) usuarios.get(i);
            if (user.get("username").equals(this.username)) {
                encontrado = true;
                JSONArray jugadosArray = (JSONArray) user.get("Jugados");

                /* Agrega el torneo */
                String fecha = torneo.getKey();
                Integer[] values = torneo.getValue();
                JSONObject torneoJSON = new JSONObject();
                torneoJSON.put("fecha", fecha);
                torneoJSON.put("id", values[0]);
                torneoJSON.put("puntos", values[1]);
                torneoJSON.put("posicion", values[2]);
                torneoJSON.put("Sets", values[3]);
                torneoJSON.put("Juegos ganados", values[4]);
                torneoJSON.put("Juegos perdidos", values[5]);
                jugadosArray.add(torneoJSON);

                /* actualizar usuario actual y array de usuarios */
                user.put("Jugados", jugadosArray);
                usuarios.set(i, user);
            }
        }
        /* actualizar objeto JSON */
        jugadoresJSON.put("Usuarios", usuarios);
        guardarJSON(jugadoresJSON);
    }

    @SuppressWarnings("unchecked")
    private void modificarCampoJSON(String campo, String valor) {
        JSONObject jugadoresJSON = cargarJSON();
        if (jugadoresJSON == null)
            return;

        JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");
        for (Object obj : usuarios) {
            JSONObject user = (JSONObject) obj;
            if (user.get("username").equals(this.username)) {
                user.put(campo, valor);
                guardarJSON(jugadoresJSON);
                break;
            }
        }
    }

    public Jugador() {

    }
    @SuppressWarnings("unchecked")
    public boolean signin(String nombre, String apellidos, String telefono, String mail, String nUsuario,
            String password) {
        if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || mail.isEmpty() || nUsuario.isEmpty()
                || password.isEmpty()) {
            return false;
        }
        /* Comprueba si que no sea admin o que no exista el username */
        if (admUser.equals(nUsuario) && admPwd.equals(password) || checkUsername(nUsuario)) {
            return false;
        } else {
            return addUser(nUsuario, password, nombre, apellidos, telefono, mail);
        }
    }
    @SuppressWarnings("unchecked")
    public boolean addUser(String nUsuario, String password, String nombre, String apellidos, String telefono,
            String email) {
        if(checkUsername(nUsuario)){
            return false;
        }
        JSONObject jugadores = cargarJSON();
        JSONArray usuariosArray = (JSONArray) jugadores.get("Usuarios");

        // Crear el nuevo usuario
        JSONObject nuevoUsuario = new JSONObject();
        nuevoUsuario.put("username", nUsuario);
        nuevoUsuario.put("password", password);
        nuevoUsuario.put("nombre", nombre);
        nuevoUsuario.put("apellidos", apellidos);
        nuevoUsuario.put("telefono", telefono);
        nuevoUsuario.put("email", email);
        nuevoUsuario.put("ranking", 0);
        nuevoUsuario.put("puntos totales", 0);
        nuevoUsuario.put("Sets totales", 0);
        nuevoUsuario.put("Juegos ganados", 0);
        nuevoUsuario.put("Juegos perdidos", 0);

        // Crear listas vacías para Jugados, Inscripciones y Participaciones
        nuevoUsuario.put("Jugados", new JSONArray());
        nuevoUsuario.put("Inscripciones", new JSONArray());
        nuevoUsuario.put("Participaciones", new JSONArray());

        // Añadir el nuevo usuario al array
        usuariosArray.add(nuevoUsuario);
        jugadores.put("Usuarios",usuariosArray);
        guardarJSON(jugadores);
        return true;
    }

    public void loadStats() {
        JSONObject estadisticas = null;
        try (FileReader fr = new FileReader("files/estadisticas.json");) {
            JSONParser parser = new JSONParser();
            estadisticas = (JSONObject) parser.parse(fr);
        } catch (Exception e) {
            System.err.println("Error al abrir el archivo de torneos");
        }
        JSONArray usuarios = (JSONArray) estadisticas.get("Usuarios");
        boolean encontrado = false;
        JSONObject user = null;
        for (int i = 0; i < usuarios.size() && encontrado; i++) {
            user = (JSONObject) usuarios.get(1);
            String username = (String) user.get("username");
            encontrado = this.username == username;
        }

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

}