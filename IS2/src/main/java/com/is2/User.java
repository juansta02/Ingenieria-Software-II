package com.is2;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    /* Login en la aplicación */
    public boolean login(String nUsuario, String password) {
        if (admUser.equals(nUsuario) && admPwd.equals(password)) {
            this.isAdmin = true;
            this.username = admUser;
            this.password = admPwd;
            return true;
        } else if (checkUser(nUsuario, password)) {
            this.isAdmin = false;
            this.username = nUsuario;
            this.password = password;
            return true;
        }
        return false;
    }

    /* Comprueba si existe un usuario con ese nombre y contraseña */
    private boolean checkUser(String nUsuario, String password) {
        JSONObject data = cargarJSON();
        JSONArray usuarios = (JSONArray) data.get("Usuarios");

        for (Object obj : usuarios) {
            JSONObject user = (JSONObject) obj;
            if (nUsuario.equals(user.get("username")) && password.equals(user.get("password"))) {
                return true;
            }
        }
        return false;
    }

    /* Comprueba si el nombre de usuario ya existe */
    protected boolean checkUsername(String nUsuario) {
        JSONObject data = cargarJSON();
        JSONArray usuarios = (JSONArray) data.get("Usuarios");

        for (Object obj : usuarios) {
            JSONObject user = (JSONObject) obj;
            if (nUsuario.equals(user.get("username"))) {
                return true;
            }
        }
        return false;
    }

    protected JSONObject cargarJSON() {
        try (FileReader reader = new FileReader("files/usuarios.json")) {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
            return null;
        }
    }

    protected void guardarJSON(JSONObject jsonObject) {
        try (FileWriter writer = new FileWriter("files/usuarios.json")) {
            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }

}
