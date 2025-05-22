package TP;

import java.io.*;
import java.util.*;

import javafx.scene.paint.Color;

public class SectionEfficaceSeulPlaq {

    private static double closestAttenuation; // Valeur privée pour un meilleur contrôle d'accès
    static double targetEnergy;
    static double densityPlaque;
    static double densityPlaquePlomb, densityPlaqueAluminium, densityPlaqueCuivre, densityPlaqueFer, 
	densityPlaqueBaryum, densityPlaqueCadmium;
    public static void main(String[] args) {
    	String fileName="";
    	
        String selectedItem2 = (String) traveauPratique.comboBox_1.getSelectedItem();
        if ("Lead (Pb)".equals(selectedItem2)) {
        	densityPlaque = 11.34;
        	fileName = "sectionEfficace\\sectionEfficacePlomb.txt";
        }else if ("Aluminium (Al)".equals(selectedItem2)) {
        	densityPlaque =2.70 ;
        	fileName = "sectionEfficace\\sectionEfficaceAluminium.txt";
        }else if ("Copper (Cu)".equals(selectedItem2)) {
        	densityPlaque = 8.96;
        	fileName = "sectionEfficace\\sectionEfficaceCuivre.txt";
        }else if ("Iron (Ir)".equals(selectedItem2)) {
        	densityPlaque = 7.87 ;
        	fileName = "sectionEfficace\\sectionEfficaceFer.txt";
        }else if ("Barium".equals(selectedItem2)) {
        	densityPlaque = 3.62;
        	fileName = "sectionEfficace\\sectionEfficaceBaryum.txt";
        }else if ("Cadmium (Cd)".equals(selectedItem2)) {
        	densityPlaque = 8.65;
        	fileName = "sectionEfficace\\sectionEfficaceCadmium.txt";
        }
        
        
        //--------------------------------------------
        
        
        if(BackgroundNoiseSimulatorWithTimeDisplay.EnergySourceSeulPlaq.equals("EseulPlaq1")) {
        targetEnergy = Double.parseDouble(traveauPratique.lblNewLabel_3.getText()) * 0.001; // Énergie cible en kev
        }else if(BackgroundNoiseSimulatorWithTimeDisplay.EnergySourceSeulPlaq.equals("EseulPlaq2")){
        	targetEnergy = Double.parseDouble(traveauPratique.lblNewLabel_7.getText()) * 0.001; // Énergie cible en MeV
        }
        
        try {
            // Lire les données du fichier
            List<DataPoint> dataPoints = readDataFromFile(fileName);

            // Trouver la valeur d'atténuation la plus proche
            closestAttenuation = findClosestAttenuation(dataPoints, targetEnergy);

            // Afficher le résultat
            //System.out.printf("Pour une énergie cible de %.3f MeV, l'atténuation correspondante est %.6f\n", targetEnergy, closestAttenuation);

        } catch (IOException e) {
            System.err.println("Error reading file : " + e.getMessage());
        }
    }

    // Getter pour accéder à la valeur de closestAttenuation
    public static double getClosestAttenuation() {
        return closestAttenuation;
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
    public static double findClosestAttenuation(List<DataPoint> dataPoints, double targetEnergy) {
        double closestDistance = Double.MAX_VALUE;
        double closestAttenuation = 0.0;

        for (DataPoint point : dataPoints) {
            double distance = Math.abs(point.energy - targetEnergy);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestAttenuation = point.attenuation;
            }
        }

        return closestAttenuation;
    }
}
