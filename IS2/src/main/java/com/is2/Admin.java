package com.is2;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Admin extends User {

    /* iniciar un torneo, eligiendo los 16 participantes según el ranking */
    public void seleccionInscritos(Torneo torneo) throws IOException {
        List<String> inscritos = torneo.getInscritos();
        /* Si hay 16 inscritos o menos, los cargamos y finalizamos funcion */
        if (inscritos.size() <= 16) {
            torneo.setParticipantes(new ArrayList<>(inscritos));
            return;
        }

        /*
         * Hay mas de 16 inscritos, por lo que se itera la lista para ir actualizandola
         */
        List<String> participantes = new ArrayList<>(16);
        JSONObject jugadoresJSON = cargarJSON();

        JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");
        for (int i = 0; i < inscritos.size(); i++) {
            String username = inscritos.get(i);
            JSONObject user = encontrarJugador(usuarios, username);
            int ranking = (int) user.get("ranking");
            addParticipante(participantes, username, ranking, usuarios);
        }
        torneo.setParticipantes(participantes);
        actualizarJugadores(participantes, torneo, jugadoresJSON);
    }

    /* Emparjemaiento de partidos iniciales */
    public void realizarEmparejamientos(Torneo torneo) throws IOException {
        List<String> participantes = new ArrayList<>(torneo.getParticipantes());
        List<Partido> partidos = new ArrayList<>(torneo.getPartidos());
        Random random = new Random();
        Collections.shuffle(partidos, random);

        int j = 0;
        for (int i = 0; i < participantes.size(); i += 2) {
            if (i + 1 < participantes.size()) {
                Partido partido = partidos.get(j);
                String jugador1 = participantes.get(i);
                String jugador2 = participantes.get(i + 1);
                partido.setJugador1(jugador1);
                partido.setJugador2(jugador2);
            } else {
                Partido partido = partidos.get(5);
                String jugador1 = participantes.get(i);
                partido.setJugador1(jugador1);
            }
            j++;
        }
        torneo.setPartidos(partidos);
    }

    /* Calcular ganado en un partido */
    public void calcularGanador(Torneo torneo, int ronda, int nPartido) throws IOException {
        List<Partido> partidos = torneo.getPartidos();
        List<String> posiciones = torneo.getPosiciones();
        if (ronda == 0) {
            Partido partido = partidos.get(nPartido);
            String[] jugadores = partido.calcularGanador();
            String ganador = jugadores[0];
            Partido partidoSig = partidos.get(nPartido / 2 + 8);
            partidoSig.setJugador1(ganador);
            String perdedor = jugadores[1];
            torneo.agregarPosicion(ronda, perdedor);
        } else if (ronda == 1) {
            Partido partido = partidos.get(nPartido + 8);
            String[] jugadores = partido.calcularGanador();
            String ganador = jugadores[0];
            Partido partidoSig = partidos.get(nPartido / 2 + 12);
            partidoSig.setJugador1(ganador);
            String perdedor = jugadores[1];
            torneo.agregarPosicion(ronda, perdedor);
        } else if (ronda == 2) {
            Partido partido = partidos.get(nPartido + 12);
            String[] jugadores = partido.calcularGanador();
            String ganador = jugadores[0];
            Partido partidoSig = partidos.get(nPartido / 2 + 14);
            partidoSig.setJugador1(ganador);
            String perdedor = jugadores[1];
            torneo.agregarPosicion(ronda, perdedor);
        } else if (ronda == 3) {
            Partido partido = partidos.get(nPartido + 14);
            String[] jugadores = partido.calcularGanador();
            String ganador = jugadores[0];
            String perdedor = jugadores[1];
            torneo.agregarPosicion(ronda, perdedor);
        }
        torneo.setPartidos(partidos);
    }

    /* Finalizar un torneo, modificando el ranking */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void finalizarTorneo(Torneo torneo) {
        List<String> posiciones = torneo.getPosiciones();
        JSONObject jugadoresJSON = cargarJSON();
        JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");
        List<Entry<String, Integer>> rankingList = new ArrayList<>();

        for (int i = 0; i < posiciones.size() && i < 4; i++) {
            String username = posiciones.get(i);
            JSONObject user = encontrarJugador(usuarios, username);
            JSONArray jugados = (JSONArray) user.get("Jugados");
            JSONObject torneoJSON = new JSONObject();
            torneoJSON.put("fecha", torneo.getfInicioTorneo());
            torneoJSON.put("id", torneo.getId());
            int puntos = 2000 - 50 * i;
            torneoJSON.put("puntos", puntos);
            torneoJSON.put("posicion", i + 1);
            int sets = torneo.setsGanados(username);
            torneoJSON.put("Sets", sets);
            int juegosGanados = torneo.setsGanados(username);
            torneoJSON.put("Juegos ganados", juegosGanados);
            int juegosPerdidos = torneo.juegosPerdidos(username);
            torneoJSON.put("Juegos perdidos", juegosPerdidos);
            jugados.add(torneoJSON);
            user.put("Jugados", jugados);

            puntos += ((Long) user.get("puntos totales")).intValue();
            rankingList.add(new AbstractMap.SimpleEntry(username, puntos));
            user.put("puntos totales", puntos);
            sets += ((Long) user.get("Sets totales")).intValue();
            user.put("Sets totales", sets);
            juegosGanados += ((Long) user.get("Juegos ganados")).intValue();
            user.put("Juegos ganados", juegosGanados);
            juegosPerdidos += ((Long) user.get("Juegos perdidos")).intValue();
            user.put("Juegos perdidos", juegosPerdidos);
            actualizarJugador(usuarios, user);
        }
        if (posiciones.size() > 4) {
            for (int i = 4; i < posiciones.size(); i++) {
                String username = posiciones.get(i);
                JSONObject user = encontrarJugador(usuarios, username);
                JSONArray jugados = (JSONArray) user.get("Jugados");
                JSONObject torneoJSON = new JSONObject();
                torneoJSON.put("fecha", torneo.getfInicioTorneo());
                torneoJSON.put("id", torneo.getId());
                int puntos = 500 - 25 * (i - 4);
                torneoJSON.put("puntos", puntos);
                torneoJSON.put("posicion", i + 1);
                int sets = torneo.setsGanados(username);
                torneoJSON.put("Sets", sets);
                int juegosGanados = torneo.setsGanados(username);
                torneoJSON.put("Juegos ganados", juegosGanados);
                int juegosPerdidos = torneo.juegosPerdidos(username);
                torneoJSON.put("Juegos perdidos", juegosPerdidos);
                jugados.add(torneoJSON);
                user.put("Jugados", jugados);

                puntos += ((Long) user.get("puntos totales")).intValue();
                rankingList.add(new AbstractMap.SimpleEntry(username, puntos));
                user.put("puntos totales", puntos);
                sets += ((Long) user.get("Sets totales")).intValue();
                user.put("Sets totales", sets);
                juegosGanados += ((Long) user.get("Juegos ganados")).intValue();
                user.put("Juegos ganados", juegosGanados);
                juegosPerdidos += ((Long) user.get("Juegos perdidos")).intValue();
                user.put("Juegos perdidos", juegosPerdidos);
                actualizarJugador(usuarios, user);
            }
        }
        jugadoresJSON.put("Usuarios", usuarios);
        guardarJSON(jugadoresJSON);
        Ranking ranking = new Ranking();
        ranking.setRanking(rankingList);
    }

    /*---------------------------CONSTRUCTOR---------------------------*/
    public Admin() {

    }

    /*---------------------------Metodos Auxiliares---------------------------*/

    /* Añade el participante en la lista del torneo, ordenandolo de top */
    private boolean addParticipante(List<String> participantes, String username, int ranking, JSONArray usuarios) {
        String userIn = "";
        int nUsuarios = participantes.size();
        if (nUsuarios == 0) {
            participantes.add(username);
            return true;
        }
        boolean added = false;
        /* se compara si hay uno o mas */
        for (int i = 0; i < nUsuarios; i++) {
            if (!added) {
                userIn = participantes.get(i);
                JSONObject userInJSON = encontrarJugador(usuarios, userIn);
                int rakningIn = ((Long) userInJSON.get("ranking")).intValue();
                if (ranking < rakningIn) {
                    added = true;
                    userIn = participantes.set(i, username);
                } else if (ranking == rakningIn) {
                    Random random = new Random();
                    int random_int = random.nextInt(1);
                    added = random_int == 1;
                    if (added) {
                        userIn = participantes.set(i, username);
                    }
                }
            } else {
                userIn = participantes.set(i, userIn);
            }
        }
        if (nUsuarios < 16) {
            userIn = added ? userIn : username;
            participantes.add(userIn);
            return true;
        }
        return added;
    }

    @SuppressWarnings("unchecked")
    private void actualizarJugadores(List<String> participantes, Torneo torneo, JSONObject jugadoresJSON) {
        JSONArray usuarios = (JSONArray) jugadoresJSON.get("Usuarios");

        for (String username : participantes) {
            boolean encontrado = false;
            for (int i = 0; i < usuarios.size() && !encontrado; i++) {
                JSONObject user = (JSONObject) usuarios.get(i);
                encontrado = username.equals((String) user.get("username"));
                if (encontrado) {
                    JSONArray participaciones = (JSONArray) user.get("Participaciones");
                    participaciones.add(torneo.toString());
                    user.put("Inscripciones", participaciones);
                    usuarios.set(i, user);
                }
            }
        }
        jugadoresJSON.put("Usuarios", usuarios);
        guardarJSON(jugadoresJSON);
    }

    private JSONObject encontrarJugador(JSONArray usuarios, String username) {
        boolean encontrado = false;
        JSONObject user = null;
        for (int i = 0; i < usuarios.size() && !encontrado; i++) {
            user = (JSONObject) usuarios.get(i);
            encontrado = username.equals(user.get("username"));
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    private void actualizarJugador(JSONArray usuarios, JSONObject user) {
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size() && !encontrado; i++) {
            JSONObject userin = (JSONObject) usuarios.get(i);
            encontrado = user.get("username").equals(userin.get("username"));
            if (encontrado) {
                usuarios.set(i, user);
            }
        }
    }

}
