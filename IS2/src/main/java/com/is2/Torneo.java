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
    private Date fInicioInsc;
    private Date fInicioTorneo;
    private List<String> inscritos;
    private List<String> participantes;
    private List<Partido> partidos;
    private JSONObject torneos;
    private JSONObject torneo;

    public int getId() {
        return id;
    }

    public Date getfInicioInsc() {
        return fInicioInsc;
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

    @SuppressWarnings("unchecked")
    public void setfInicioInsc(String fInicioInsc) throws ParseException, IOException {
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
        if (!fInicioInsc.equals("")) {
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

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

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
            this.fInicioInsc = formatter.parse(dateString);
        }

        // cargar los usuarios inscritos y participantes
        inscritos = new ArrayList<>();
        participantes = new ArrayList<>(16);
        JSONArray inscritosJson = (JSONArray) torneo.get("inscritos");
        for (int i = 0; i < inscritosJson.size(); i++) {
            inscritos.add((String) inscritosJson.get(i));
        }
        JSONArray participantesJson = (JSONArray) torneo.get("participantes");
        for (int i = 0; i < participantesJson.size(); i++) {
            participantes.add((String) participantesJson.get(i));
        }

        // añadir partidos
        JSONArray partidosJson = (JSONArray) torneo.get("partidos");
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

}