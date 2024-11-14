import java.util.Date;


public class App {
    private final static int FALL = 0;
    private final static int WINTER = 1;
    private final static int SPRING = 2;
    private final static int SUMMER = 3;

    public static void main(String[] args) throws Exception {
        Torneo[] torneos = new Torneo[4];
        torneos[SUMMER] =  new Torneo(0);
        torneos[FALL] =  new Torneo(1);
        torneos[WINTER] =  new Torneo(2);
        torneos[SPRING] =  new Torneo(3);
        torneos[SUMMER].inscripcion("usuario1");
        
    }
}