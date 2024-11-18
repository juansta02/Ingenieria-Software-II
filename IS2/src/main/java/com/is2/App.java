package com.is2;

import java.util.Date;

import javax.swing.SwingUtilities;

import com.is2.IGrafica.AppController;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(AppController::new);
    }
}