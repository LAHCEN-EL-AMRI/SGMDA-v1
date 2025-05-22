package TP;

import java.io.*;
import java.util.*;

import javafx.scene.paint.Color;

public class AttenuationFinder {

    private static double closestAttenuation; // Valeur privée pour un meilleur contrôle d'accès
    static double targetEnergy;
    static double densityPlaque;
    static double densityPlaquePlomb, densityPlaqueAluminium, densityPlaqueCuivre, densityPlaqueFer, 
	densityPlaqueBaryum, densityPlaqueCadmium;
    public static void main(String[] args) {
    	String fileName="";
    	
        //------------pour les plaques Mix-----------
        
        String selectedItem6 = (String) traveauPratique.comboBox_1.getSelectedItem();
        if ("Mixed".equals(selectedItem6)) {
        	
        	//----------Plomb------
        	if (PlaqueMixt.checkBoxes[0].isSelected() && "plomb".equals(BackgroundNoiseSimulatorWithTimeDisplay.PlombPlaque)) {
        		densityPlaquePlomb = 11.34;
            	fileName = "sectionEfficace\\sectionEfficacePlomb.txt";
        	}
        	//----------Aluminium------
        	if (PlaqueMixt.checkBoxes[1].isSelected() && "aluminium".equals(BackgroundNoiseSimulatorWithTimeDisplay.PlombPlaque)) {
        		densityPlaqueAluminium =2.70 ;
            	fileName = "sectionEfficace\\sectionEfficaceAluminium.txt";
        	}
        	//----------Cuivre------
        	if (PlaqueMixt.checkBoxes[2].isSelected() && "cuivre".equals(BackgroundNoiseSimulatorWithTimeDisplay.PlombPlaque)) {
        		densityPlaqueCuivre = 8.96;
            	fileName = "sectionEfficace\\sectionEfficaceCuivre.txt";
        	}
        	//----------Fer-----
        	if (PlaqueMixt.checkBoxes[3].isSelected() && "fer".equals(BackgroundNoiseSimulatorWithTimeDisplay.PlombPlaque)) {
        		densityPlaqueFer = 7.87 ;
            	fileName = "sectionEfficace\\sectionEfficaceFer.txt";
        	}
        	//----------Baryum------
        	if (PlaqueMixt.checkBoxes[4].isSelected() && "baryum".equals(BackgroundNoiseSimulatorWithTimeDisplay.PlombPlaque)) {
        		densityPlaqueBaryum = 3.62;
            	fileName = "sectionEfficace\\sectionEfficaceBaryum.txt";
        	}
        	//----------Cadmium------
        	if (PlaqueMixt.checkBoxes[5].isSelected() && "cadmium".equals(BackgroundNoiseSimulatorWithTimeDisplay.PlombPlaque)) {
        		densityPlaqueCadmium = 8.65;
            	fileName = "sectionEfficace\\sectionEfficaceCadmium.txt";
        	   
        	}
        	
        }
        
        
        //--------------------------------------------
        
        
        if(BackgroundNoiseSimulatorWithTimeDisplay.EnergySource.equals("E1")) {
        targetEnergy = Double.parseDouble(traveauPratique.lblNewLabel_3.getText()) * 0.001; // Énergie cible en kev
        }else if(BackgroundNoiseSimulatorWithTimeDisplay.EnergySource.equals("E2")){
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
