package com.is2;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Torneo {
    private int id;
    private Date fFinInsc;
    private Date fInicioTorneo;
    private List<String> inscritos;
    private List<String> participantes;
    private List<Partido> partidos;
    private List<String> posiciones;
    private JSONObject torneos;
    private JSONObject torneo;

    public int getId() {
        return id;
    }

    public Date getfFinInsc() {
        return fFinInsc;
    }

    public Date getfInicioTorneo() {
        return fInicioTorneo;
    }

    public List<String> getInscritos() {
        return inscritos;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public List<String> getPosiciones() {
        return this.posiciones;
    }

    @SuppressWarnings("unchecked")
    public void setfFinInsc(String fInicioInsc) throws ParseException, IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        if (!fInicioInsc.equals("")) {
            this.fInicioTorneo = formatter.parse(fInicioInsc);
        }
        torneo.put("fechaInicioInscripcion", fInicioInsc);
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
    }

    @SuppressWarnings("unchecked")
    public void setfInicioTorneo(String fInicioTorneo) throws ParseException, IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        if (!fFinInsc.equals("")) {
            this.fInicioTorneo = formatter.parse(fInicioTorneo);
        }
        torneo.put("fechaInicioTorneo", fInicioTorneo);
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
    }

    @SuppressWarnings("unchecked")
    public void setPartidos(List<Partido> partidos) throws IOException {
        /* modificacion local */
        this.partidos = partidos;
        /* modificacion en la base de datos */
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        JSONArray partidosJSON = (JSONArray) torneo.get("partidos");
        partidosJSON.clear();
        for (int i = 0; i < partidos.size(); i++) {
            Partido partido = partidos.get(i);
            JSONObject partidoJSON = new JSONObject();
            partidoJSON.put("id", partido.getId());
            partidoJSON.put("Jugador 1", partido.getJugador1());
            partidoJSON.put("Jugador 2", partido.getJugador2());
            partidoJSON.put("Juegos Jugador 1", partido.getJuegosJugador1());
            partidoJSON.put("Juegos Jugador 2", partido.getJuegosJugador2());
            partidoJSON.put("ronda", partido.getRonda());
            partidoJSON.put("ganador", partido.getGanador());
            partidoJSON.put("fecha", partido.getFecha());
            partidosJSON.add(partidoJSON);
        }
        torneo.put("partidos", partidosJSON);
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
    }

    @SuppressWarnings("unchecked")
    public void modifyPartido(Partido partido) throws IOException {
        /* modificacion en la base de datos */
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        JSONArray partidosJSON = (JSONArray) torneo.get("partidos");

        boolean encontrado = false;
        for (int i = 0; i < partidosJSON.size() && !encontrado; i++) {
            JSONObject partidoJSON = (JSONObject) partidosJSON.get(i);
            String id = (String) partidoJSON.get("id");
            encontrado = id.equals(partido.getId());
            if (encontrado) {
                partidoJSON.put("Jugador 1", partido.getJugador1());
                partidoJSON.put("Jugador 2", partido.getJugador2());
                partidoJSON.put("Juegos Jugador 1", partido.getJuegosJugador1());
                partidoJSON.put("Juegos Jugador 2", partido.getJuegosJugador2());
                partidoJSON.put("ronda", partido.getRonda());
                partidoJSON.put("ganador", partido.getGanador());
                partidoJSON.put("fecha", partido.getFecha());
                partidosJSON.set(i, partidoJSON);
                partidos.set(i, partido);
            }
        }

        torneo.put("partidos", partidosJSON);
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
    }

    @SuppressWarnings("unchecked")
    public void setParticipantes(List<String> participantes) throws IOException {
        /* modificacion local */
        this.participantes = participantes;
        /* modificacion en la base de datos */
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        JSONArray participantesJSON = (JSONArray) torneo.get("participantes");
        participantesJSON.clear();
        for (String participante : participantes) {
            participantesJSON.add(participante);
        }
        torneo.put("participantes", participantesJSON);
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
    }

    @SuppressWarnings("unchecked")
    public void setPosiciones(List<String> posiciones) throws IOException {
        /* modificacion local */
        this.posiciones = posiciones;
        /* modificacion en la base de datos */
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        JSONArray posicionesJSON = (JSONArray) torneo.get("posiciones");
        posicionesJSON.clear();
        for (String jugadores : posiciones) {
            posicionesJSON.add(jugadores);
        }
        torneo.put("posiciones", posicionesJSON);
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
    }

    /* Jugador se inscribe en el torneo */
    @SuppressWarnings("unchecked")
    public boolean inscripcion(String username) throws IOException {
        inscritos.add(username);
        JSONArray inscritosJson = (JSONArray) torneo.get("inscritos");
        inscritosJson.add(username);

        // remplaza el array de inscritos
        torneo.put("inscritos", inscritosJson);

        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        FileWriter fw = new FileWriter("files/torneos.json");
        fw.write(torneos.toString());
        fw.close();
        return true;
    }

    public void agregarPosicion(int ronda, String username) {
        if (ronda == -1) {
            posiciones.set(0, username);
            return;
        } else if (ronda == 0) {
            setPosicion(8, 16, username);
        } else if (ronda == 1) {
            setPosicion(4, 8, username);
        } else if (ronda == 2) {
            setPosicion(2, 4, username);
        } else if (ronda == 3) {
            posiciones.set(1, username);
        }
    }

    public int setsGanados(String username) {
        int sets1 = 0;
        for (Partido partido : partidos) {
            if (partido.getJugador1().equals(username)) {
                sets1 += calcularSets(partido.getJuegosJugador1(), partido.getJuegosJugador2());
            } else if (partido.getJugador2().equals(username)) {
                sets1 += calcularSets(partido.getJuegosJugador2(), partido.getJuegosJugador1());
            }
        }
        return sets1;
    }

    public int juegosGanados(String username) {
        int juegos1 = 0;
        for (Partido partido : partidos) {
            if (partido.getJugador1().equals(username)) {
                juegos1 += calcularJuegos(partido.getJuegosJugador1());
            } else if (partido.getJugador2().equals(username)) {
                juegos1 += calcularJuegos(partido.getJuegosJugador2());
            }
        }
        return juegos1;
    }

    public int juegosPerdidos(String username) {
        int perdidos1 = 0;
        for (Partido partido : partidos) {
            if (partido.getJugador1().equals(username)) {
                perdidos1 += calcularJuegos(partido.getJuegosJugador2());
            } else if (partido.getJugador2().equals(username)) {
                perdidos1 += calcularJuegos(partido.getJuegosJugador1());
            }
        }
        return perdidos1;
    }

    /* Constructor de la clase Torneos */
    public Torneo(int idTorneo) throws ParseException {
        this.id = idTorneo;
        // cargar json de torneos
        try (FileReader fr = new FileReader("files/torneos.json");) {
            JSONParser parser = new JSONParser();
            torneos = (JSONObject) parser.parse(fr);
        } catch (Exception e) {
            System.err.println("Error al abrir el archivo de torneos");
        }
        JSONArray torneosArray = (JSONArray) torneos.get("Torneos");
        // buscar el torneo en el array de torneos
        boolean encontrado = false;
        for (int i = 0; i < 4 && !encontrado; i++) {
            torneo = (JSONObject) torneosArray.get(i);
            encontrado = (int) (long) torneo.get("id") == this.id;
        }
        // cargar fechas de torneos.json
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = (String) torneo.get("fechaInicioTorneo");
        if (!dateString.equals("")) {
            this.fInicioTorneo = formatter.parse(dateString);
        }
        dateString = (String) torneo.get("fechaInicioInscripcion");
        if (!dateString.equals("")) {
            this.fFinInsc = formatter.parse(dateString);
        }

        // cargar los usuarios inscritos y participantes
        cargarIncritos();
        cargarParticipantes();

        // añadir partidos
        cargarPartidos();

        // cargar posiciones
        cargarPosiciones();

    }

    private void cargarIncritos() {
        inscritos = new ArrayList<>();
        participantes = new ArrayList<>(16);
        JSONArray inscritosJson = (JSONArray) torneo.get("inscritos");
        for (int i = 0; i < inscritosJson.size(); i++) {
            inscritos.add((String) inscritosJson.get(i));
        }
    }

    private void cargarParticipantes() {
        participantes = new ArrayList<>(16);
        JSONArray participantesJson = (JSONArray) torneo.get("participantes");
        for (int i = 0; i < participantesJson.size(); i++) {
            participantes.add((String) participantesJson.get(i));
        }
    }

    private void cargarPartidos() throws ParseException {
        partidos = new ArrayList<Partido>(15);
        JSONArray partidosJSON = (JSONArray) torneo.get("partidos");
        for (int i = 0; i < partidosJSON.size(); i++) {
            JSONObject partidoJSON = (JSONObject) partidosJSON.get(i);
            String id = (String) partidoJSON.get("id");
            String jugador1 = (String) partidoJSON.get("Jugador 1");
            String jugador2 = (String) partidoJSON.get("Jugador 2");
            int[] sets1 = (int[]) partidoJSON.get("Juegos Jugador 1");
            int[] sets2 = (int[]) partidoJSON.get("Juegos Jugador 2");
            int ronda = (int) partidoJSON.get("ronda");
            String ganador = (String) partidoJSON.get("ganador");
            // cargar fecha
            Date fecha = null;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = (String) partidoJSON.get("fecha");
            if (!dateString.equals("")) {
                fecha = formatter.parse(dateString);
            }
            Partido partido = new Partido(id, jugador1, jugador2, sets1, sets2, ronda, ganador, fecha);
            partidos.add(partido);
        }
    }

    private void cargarPosiciones() {
        posiciones = new ArrayList<>(16);
        JSONArray posicionesJson = (JSONArray) torneo.get("posiciones");
        for (int i = 0; i < posicionesJson.size(); i++) {
            posiciones.add((String) posicionesJson.get(i));
        }
    }

    private void setPosicion(int inicio, int fin, String username1) {
        if (posiciones.get(inicio) == null || posiciones.get(inicio).isEmpty()) {
            posiciones.set(inicio, username1);
            return;
        }

        boolean puesto = false;
        boolean end = false;
        int i = inicio;
        String add = username1;
        for (; i < fin && !end; i++) {
            if (!puesto) {
                if (posiciones.get(i) == null || posiciones.get(i).isEmpty()) {
                    posiciones.set(i, username1);
                    end = true;
                } else {
                    String username2 = posiciones.get(i);
                    boolean greater = compare(username1, username2);
                    if (greater) {
                        add = posiciones.set(i, username1);
                    }
                }
            } else {
                add = posiciones.set(i, add);
            }

        }
        if (i == fin && !puesto) {
            posiciones.set(fin - 1, username1);
        }
    }

    private boolean compare(String username1, String username2) {
        int sets1 = setsGanados(username1);
        int sets2 = setsGanados(username2);
        int juegos1 = juegosGanados(username1);
        int juegos2 = juegosGanados(username2);
        int perdidos1 = juegosPerdidos(username1);
        int perdidos2 = juegosPerdidos(username2);
        boolean greater = false;
        if (sets1 != sets2)
            greater = sets1 > sets2;
        else if (juegos1 != juegos2)
            greater = juegos1 > juegos2;
        else if (perdidos1 != perdidos2)
            greater = perdidos1 < perdidos2;
        else {
            Random rand = new Random();
            int random = rand.nextInt(1);
            greater = random == 1;
        }
        return greater;
    }

    private int calcularJuegos(int[] juegosJugador) {
        int res = 0;
        for (int i = 0; i < juegosJugador.length; i++) {
            res += juegosJugador[i];
        }
        return res;
    }

    private int calcularSets(int[] juegosJugador1, int[] juegosJugador2) {
        int res = 0;
        for (int i = 0; i < juegosJugador1.length; i++) {
            if (juegosJugador1[i] > juegosJugador2[i]) {
                res++;
            }
        }
        return res;
    }
}