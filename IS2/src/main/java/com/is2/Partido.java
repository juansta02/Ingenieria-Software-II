package com.is2;

import java.util.Date;

public class Partido implements Comparable<Partido> {
    private String id;
    private String jugador1;
    private String jugador2;
    private Date fecha;
    private int[] juegosJugador1;
    private int[] juegosJugador2;
    private int ronda;
    private String ganador;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public int[] getJuegosJugador1() {
        return juegosJugador1;
    }

    public void setJuegosJugador1(int[] juegosJugador1) {
        this.juegosJugador1 = juegosJugador1;
    }

    public int[] getJuegosJugador2() {
        return juegosJugador2;
    }

    public void setJuegosJugador2(int[] juegosJugador2) {
        this.juegosJugador2 = juegosJugador2;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Partido(String id, String jugador1, String jugador2, int[] juegos1, int[] juegos2, int ronda, String ganador,
            Date fecha) {
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.juegosJugador1 = juegos1;
        this.juegosJugador2 = juegos2;
        this.ronda = ronda;
        this.ganador = ganador;
        this.fecha = fecha;
    }


    public String[] calcularGanador(){
        /* pos(0) ganador, pos(1) perdedor */
        String[] jugadores = new String[2]; 
        int ganados1 = 0;
        int ganados2 = 0;
        for (int i = 0; i < juegosJugador1.length; i++) {
            if(juegosJugador1[i]>juegosJugador2[i]){
                ganados1++;
            }
            else{
                ganados2++;
            }
        }
        if(ganados1>ganados2){
            ganador = jugador1;
            jugadores[0] = jugador1;
            jugadores[1] = jugador2;
        }
        else{
            ganador = jugador2;
            jugadores[0] = jugador2;
            jugadores[1] = jugador1;
        }
        return jugadores;
    }

    @Override
    public int compareTo(Partido otro) {
        if (this.ronda != otro.ronda) {
            return Integer.compare(this.ronda, otro.ronda);
        }
        return this.fecha.compareTo(otro.fecha);
    }

    @Override
    public String toString() {
        return String.format("Partido{id=%s, ronda=%d, jugador1='%s', jugador2='%s', ganador='%s'}",
                id, ronda, jugador1, jugador2, ganador);

    }
}