import java.util.*;
import java.io.*;

public class User {
    protected String username;
    protected String password;
    protected static String admUser = "root";
    protected static String admPwd = "admin";
    protected boolean isAdmin;

    public String getUsername() {
        if (isAdmin) {
            return admUser;
        } else {
            return username;
        }
    }

    public String getPassword() {
        if (isAdmin) {
            return admPwd;
        } else {
            return password;
        }
    }    

    public boolean isAdmin() {
        return isAdmin;
    }

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
            this.isAdmin = true;
            nUsuario = admUser;
            password = admPwd;
            /* Comporbar si existe el user, en cuyo caso se inicia sesion */
        } else if (checkUser(nUsuario, password)) {
            this.isAdmin = false;
            this.username = nUsuario;
            this.password = password;
            login = true;
        }
        return login;
    }

    /* comprueba si existe un usuario con ese nombre y esa contraseña */
    private boolean checkUser(String nUsuario2, String password2) {
        boolean userExist = false;
        try (BufferedReader br = new BufferedReader(new FileReader("IS2/files/usuarios.txt"));) {
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

    protected boolean checkUsername(String nUsuario2) { // comprueba si el nombre de ususario ya existe
        boolean userExist = false;
        try {
            FileReader file = new FileReader("IS2/files/usuarios.txt");
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

    /// ----------------------------------------metodos de
    /// admin----------------------------------------------------/

}
