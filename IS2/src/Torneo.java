import java.util.*;
import java.io.*;

public class Torneo {
    private int id;
    private Date fechaLimite;
    private Date fechaInicio;
    private List<Jugador> inscritos;
    private List<Jugador> participantes; 
    public int getId() {
        return id;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public List<Jugador> getJugadores() {
        return inscritos;
    }

    public void setParticipantes(List<Jugador> participantes){
        this.participantes = participantes;
    }

    public Torneo(Date fInicio, Date fLimite){
        this.fechaInicio = fInicio;
        this.fechaLimite = fLimite;
        inscritos = new ArrayList<>();
        participantes = new ArrayList<>(16);
    }

    public void inscripcion(Jugador jugador){
        inscritos.add(jugador);
    }


}