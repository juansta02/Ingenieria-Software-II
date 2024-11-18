package com.is2.IGrafica;

import javax.swing.*;

import com.is2.*;


public class AppController {
    private JFrame currentFrame; // Ventana actual
    private User user;

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
    
    public AppController() {
        // Inicia con la pantalla de inicio de sesión
        showLoginScreen();
    }

    public void showLoginScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        LoginView loginView = new LoginView(this);
        currentFrame = loginView;
        currentFrame.setVisible(true);
    }

    public void showMainScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        MainView mainView = new MainView(this, user);
        currentFrame = mainView;
        currentFrame.setVisible(true);
    }

    public void showTournamentManagementScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        TournamentManagementView tmView = new TournamentManagementView(this, user);
        currentFrame = tmView;
        currentFrame.setVisible(true);
    }

    public void showAccountManagamentScreen(){
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        AccountManagementView amView = new AccountManagementView(this, user);
        currentFrame = amView;
        currentFrame.setVisible(true);
    }

    public void logout() {
        user = null;
        showLoginScreen();
    }
    public void showRegisterScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        RegisterView registerView = new RegisterView(this);
        currentFrame = registerView;
        currentFrame.setVisible(true);
    }
}
