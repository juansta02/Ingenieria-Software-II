import java.util.*;
import java.io.*;

public class User {
    protected String nUsuario;
    protected String password;
    private static String admUser = "root";
    private static String admPwd = "admin";
    boolean esAdmin;

    public User() {
    }

    /*
     * Hace login en la aplicacion, a menos que se un admin. Si no existe un usuario
     * da error
     */
    public boolean login(String nUsuario, String password) {
        /* Comprobar si el hace login es un admin */
        boolean login = false;
        if (admUser.equals(nUsuario) && admPwd.equals(password)) {
            esAdmin = true;
            nUsuario = admUser;
            password = admPwd;
            /* Comporbar si existe el user, en cuyo caso se inicia sesion */
        } else if (checkUser(nUsuario, password)) {
            this.nUsuario = nUsuario;
            this.password = password;
            login = true;
        }
        return login;
    }

    private boolean checkUser(String nUsuario2, String password2) { //comprueba si existe un usuario con ese nombre y esa contraseña
        boolean userExist = false;
        try {
            FileReader file = new FileReader("files/usuarios.txt");
            BufferedReader br = new BufferedReader(file);
            while (!userExist && br.ready()) {
                String line = br.readLine();
                String[] info = line.split(";");
                String user = info[0];
                String pwd = info[1];
                userExist = nUsuario2.equals(user) && password2.equals(pwd);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error: el archivo usuarios.txt no ha sido encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo usuarios.txt");
            e.printStackTrace();
        }

        return userExist;
    }

    private boolean checkPlayer(String nUsuario2) { //comprueba si el nombre de ususario ya existe
        boolean userExist = false;
        try {
            FileReader file = new FileReader("files/usuarios.txt");
            BufferedReader br = new BufferedReader(file);
            while (!userExist && br.ready()) {
                String line = br.readLine();
                String[] info = line.split(";");
                String user = info[0];
                userExist = nUsuario2.equals(user);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error: el archivo usuarios.txt no ha sido encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo usuarios.txt");
            e.printStackTrace();
        }

        return userExist;
    }

    /* Sigin en la aplicacion, no lo puede hacer un admin */
    public boolean sigin(String nombre, String apellido1, String apellido2, int telefono, String mail, String nUsuario,
            String password) throws FileNotFoundException, IOException {
        if (admUser.equals(nUsuario) && admPwd.equals(password) || checkPlayer(nUsuario)) { //si es admin o ya existe ese nombre de usuario no lo crea
            return false;
        } else {
            return addUser(nUsuario, password, telefono);
        }
    }

    public boolean addUser(String nUsuario, String password, int telefono) {
        File archivo = new File("files/usuarios.txt");
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
            // Verificar si el archivo ya existe o no
            if (!archivo.exists()) {
                archivo.createNewFile(); // si no lo existe loc crrea
            }

            // Escribir las variables en el archivo
            escritor.write(nUsuario + ";" + password + ";" + telefono + ";");
            escritor.newLine();
            escritor.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        } 
        return true;
    }

    ///----------------------------------------metodos de admin----------------------------------------------------/

    

}
