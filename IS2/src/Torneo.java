import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.text.SimpleDateFormat;

public class Torneo {
    private int id;
    private Date fLimiteInsc;
    private Date fInicio;
    private List<String> inscritos;
    private List<String> participantes;
    private List<Partido> partidos;
    private JSONObject torneos;
    private JSONObject torneo;

    public int getId() {
        return id;
    }

    public Date getfLimiteInsc() {
        return fLimiteInsc;
    }

    public Date getfInicio() {
        return fInicio;
    }

    public List<String> getInscritos() {
        return inscritos;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public Torneo(int idTorneo) throws Exception {
        this.id = idTorneo;
        // cargar json de torneos
        try (FileReader fr = new FileReader("IS2/files/torneos.json");) {
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
        String dateString = (String) torneo.get("fechaInicio");
        if (!dateString.equals("")) {
            this.fInicio = formatter.parse(dateString);
        }
        dateString = (String) torneo.get("fechaLimiteInsc");
        if (!dateString.equals("")) {
            this.fLimiteInsc = formatter.parse(dateString);
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
    public void inscripcion(String jugador) throws IOException {
        inscritos.add(jugador);
        JSONArray inscritosJson = (JSONArray) torneo.get("inscritos");
        inscritosJson.add(jugador);
        
        //remplaza el array de inscritos
        torneo.put("inscritos", inscritosJson);
        
        JSONArray torneoJsonArray = (JSONArray) torneos.get("Torneos");
        torneoJsonArray.set(id, torneo);
        torneos.replace("Torneos", torneoJsonArray);
        try (FileWriter fw = new FileWriter("IS2/files/torneos.json");) {
            fw.write(torneos.toString());
        } catch (IOException e) {
            System.err.println("Error en el archivo");
        }
    }

}