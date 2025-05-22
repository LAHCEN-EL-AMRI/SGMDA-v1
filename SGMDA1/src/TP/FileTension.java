package TP;

import java.io.*;
import java.util.*;

import javafx.scene.paint.Color;

public class FileTension {

    private static double closestTension; // Valeur privée pour un meilleur contrôle d'accès
    
    static double densityPlaque;
    
    public static void main(String[] args) {
    	
    	
        String fileName = "sectionEfficace\\FileTension.txt"; // Nom du fichier
        
        //System.out.println("fileName    :" + fileName);
        
        
        
        try {
            // Lire les données du fichier
            List<DataPoint> dataPoints = readDataFromFile(fileName);

            // Trouver la valeur d'atténuation la plus proche
            closestTension = findclosestTension(dataPoints, BackgroundNoiseSimulatorWithTimeDisplay.targetTension);

            // Afficher le résultat
            //System.out.printf("Pour une énergie cible de %.3f MeV, l'atténuation correspondante est %.6f\n", targetTension, closestTension);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    // Getter pour accéder à la valeur de closestTension
    public static double getclosestTension() {
        return closestTension;
    }

    // Classe pour représenter une paire (Énergie, Atténuation)
    static class DataPoint {
        double energy;
        double attenuation;

        public DataPoint(double energy, double attenuation) {
            this.energy = energy;
            this.attenuation = attenuation;
        }
    }

    // Fonction pour lire les données du fichier texte
    public static List<DataPoint> readDataFromFile(String fileName) throws IOException {
        List<DataPoint> dataPoints = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Sauter la première ligne contenant les en-têtes
            reader.readLine();

            // Lire chaque ligne et extraire les données
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Séparer les colonnes par espace ou tabulation
                if (parts.length == 2) {
                    double energy = Double.parseDouble(parts[0].replace(",", "."));
                    double attenuation = Double.parseDouble(parts[1].replace(",", "."));
                    dataPoints.add(new DataPoint(energy, attenuation));
                }
            }
        }
        return dataPoints;
    }

    // Fonction pour trouver la valeur d'atténuation la plus proche
    public static double findclosestTension(List<DataPoint> dataPoints, double targetTension) {
        double closestDistance = Double.MAX_VALUE;
        double closestTension = 0.0;

        for (DataPoint point : dataPoints) {
            double distance = Math.abs(point.energy - targetTension);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestTension = point.attenuation;
            }
        }

        return closestTension;
    }
}
