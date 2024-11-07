public class Partido {
    private String id;
    private Jugador jugador1;
    private Jugador jugador2;
    private int[] juegosJugador1;
    private int[] juegosJugador2;
    private int setJugador1;
    private int setJugador2;
    private Jugador ganador;
    
    public Partido(){
        
    }


////Getter////
    public String getID(){
        return id;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public int[] getJuegosJugador1() {
        return juegosJugador1;
    }

    public int[] getJuegosJugador2() {
        return juegosJugador2;
    }
    public int getSetsJugador1() {
        return setJugador1;
    }
    public int getSetsJugador2() {
        return setJugador2;
    }
    ////Setter////
    public void setId(String id) {
        this.id = id;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public void setJuegosJugador1(int[] juegosJugador1) {
        this.juegosJugador1 = juegosJugador1;
    }

    public void setJuegosJugador2(int[] juegosJugador2) {
        this.juegosJugador2 = juegosJugador2;
    }
    public void setSetsJugador1(int setJugador1) {
        this.setJugador1 = setJugador1;
    }
    public void setSetsJugador2(int setJugador2) {
        this.setJugador2 = setJugador2;
    }
    
    
    
    
    
}