package com.is2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Ranking {
    private PriorityQueue<Entry<String, Integer>> rankingQueue;

    public Ranking() {
        rankingQueue = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue() // Orden descendente por puntos
        );

        try (BufferedReader br = new BufferedReader(new FileReader("files/ranking.txt"))) {
            while (br.ready()) {
                String line = br.readLine();
                String[] info = line.split(";");
                String user = info[0];
                int puntos = Integer.parseInt(info[1]);
                rankingQueue.add(new AbstractMap.SimpleEntry<>(user, puntos));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: el archivo ranking.txt no ha sido encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo ranking.txt");
            e.printStackTrace();
        }
    }

    public void setRanking(List<Entry<String, Integer>> newRanking) {
        // Actualiza la PriorityQueue con el nuevo ranking
        for (Entry<String, Integer> entry : newRanking) {
            rankingQueue.add(entry);
        }

        // Escribe el ranking actualizado en el archivo
        guardarRanking();
    }

    public List<Entry<String, Integer>> getRanking() {
        // Devuelve el ranking en forma de lista ordenada
        List<Entry<String, Integer>> rankingList = new ArrayList<>(rankingQueue);
        rankingList.sort((a, b) -> b.getValue() - a.getValue()); // Orden descendente
        return rankingList;
    }

    public void guardarRanking() {
        File file = new File("files/ranking.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Escribe cada entrada del ranking en el archivo
            for (Entry<String, Integer> entry : getRanking()) {
                bw.write(entry.getKey() + ";" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo ranking.txt");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getRanking().toString();
    }
}
