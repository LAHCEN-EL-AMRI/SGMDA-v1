package TP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PlaqueMixt extends JDialog {
    static JCheckBox[] checkBoxes;
    static JTextField[] thicknessFields;
    static JTextField[] DistanceFields;
    static JLabel checkedLabel;
    JCheckBox checkBox;
    private static final String FILE_NAME = "checkbox_state.properties";
    private static final String FILE_NAME_EPESSEUR_Distance = "saved_data.txt";

    public PlaqueMixt(JFrame parent) {
        super(parent, "mixed attenuation plate", true);
        setLayout(null);
        
        String[] materials = {"Lead (Pb)", "Aluminum (Al)", "Copper (Cu)", "Iron (Ir)", "Barium", "Cadmium (Cd)"};
                              
        checkBoxes = new JCheckBox[materials.length];        
        thicknessFields = new JTextField[materials.length];
        DistanceFields = new JTextField[materials.length];
        
        JLabel titleLabel = new JLabel("Plate name");
        titleLabel.setBounds(20, 20, 100, 20);
        add(titleLabel);
        
        checkedLabel = new JLabel("Check");
        checkedLabel.setBounds(150, 20, 50, 20);
        add(checkedLabel);
        
        JLabel thicknessLabel = new JLabel("Thickness (mm)");
        thicknessLabel.setBounds(230, 20, 100, 20);
        add(thicknessLabel);
        
        JLabel distancesourceplaque = new JLabel("Source-Plate Distance (cm)");
        distancesourceplaque.setBounds(350, 20, 170, 20);
        add(distancesourceplaque);
        
        for (int i = 0; i < materials.length; i++) {
            JLabel materialLabel = new JLabel(materials[i]);
            materialLabel.setBounds(20, 50 + i * 30, 100, 20);
            add(materialLabel);
            
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setBounds(150, 50 + i * 30, 20, 20);
            add(checkBoxes[i]);
            
            thicknessFields[i] = new JTextField(5);
            thicknessFields[i].setBounds(250, 50 + i * 30, 50, 20);
            add(thicknessFields[i]);
            
            DistanceFields[i] = new JTextField(5);
            DistanceFields[i].setBounds(390, 50 + i * 30, 50, 20);
            add(DistanceFields[i]);
        }
        thicknessFields[0].setText("30");
        thicknessFields[1].setText("20");
        thicknessFields[2].setText("10");
        thicknessFields[3].setText("35");
        thicknessFields[4].setText("20");
        thicknessFields[5].setText("30");
        
        DistanceFields[0].setText("5");
        DistanceFields[1].setText("9");
        DistanceFields[2].setText("12");
        DistanceFields[3].setText("22");
        DistanceFields[4].setText("25");
        DistanceFields[5].setText("30");
        
     // Charger les états enregistrés
        loadCheckBoxStates();

        JButton saveButton = new JButton("OK");
        saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				saveCheckBoxStates();	
				saveDataEpesseurDistance();  // Sauvegarder les données lorsque le bouton est cliqué
		         
				saveData();	
				dispose();
			}
		});
        
        loadData();
        
        saveButton.setBounds(170, 280, 80, 30);
        add(saveButton);
        
        
      
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(260, 280, 80, 30);
        add(cancelButton);
        
        
        cancelButton.addActionListener(e -> dispose());
       
        
        setSize(550, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
 // Enregistrer l'état des cases cochées
    private void saveCheckBoxStates() {
        Properties properties = new Properties();
        for (int i = 0; i < checkBoxes.length; i++) {
            properties.setProperty("checkbox_" + i, String.valueOf(checkBoxes[i].isSelected()));
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            properties.store(fos, "État des cases cochées");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Charger l'état des cases cochées
    private void loadCheckBoxStates() {
        Properties properties = new Properties();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
                properties.load(fis);
                for (int i = 0; i < checkBoxes.length; i++) {
                    String value = properties.getProperty("checkbox_" + i);
                    if (value != null) {
                        checkBoxes[i].setSelected(Boolean.parseBoolean(value));
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
 // Fonction pour charger les données du fichier
    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_EPESSEUR_Distance))) {
            // Charger les valeurs des thicknessFields
            for (int i = 0; i < thicknessFields.length; i++) {
                String line = reader.readLine();
                if (line != null) {
                    thicknessFields[i].setText(line);
                }
            }

            // Charger les valeurs des DistanceFields
            for (int i = 0; i < DistanceFields.length; i++) {
                String line = reader.readLine();
                if (line != null) {
                    DistanceFields[i].setText(line);
                }
            }

            //System.out.println("Données chargées !");
        } catch (IOException ex) {
           // System.out.println("Aucune donnée enregistrée, ou erreur lors du chargement.");
        }
    }
    
    
    //----------------sauvegarde le contenu de txtFiel de eppesseur et la distance
    
    // Fonction pour sauvegarder les données des champs dans un fichier
    private void saveDataEpesseurDistance() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_EPESSEUR_Distance))) {
            // Sauvegarder les valeurs des thicknessFields
            for (int i = 0; i < thicknessFields.length; i++) {
                writer.write(thicknessFields[i].getText());
                writer.newLine();
            }

            // Sauvegarder les valeurs des DistanceFields
            for (int i = 0; i < DistanceFields.length; i++) {
                writer.write(DistanceFields[i].getText());
                writer.newLine();
            }

            System.out.println("Recorded data  !");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    //--------------------------------------------------------------------------
    
    
    private void saveData() {
        // Implémenter la logique pour sauvegarder les données
        JOptionPane.showMessageDialog(this, " Data saved successfully! ");
    }
    
    private void resetFields() {
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setSelected(false);
            thicknessFields[i].setText("");
            DistanceFields[i].setText("");
        }

    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            SourceInfoDialog dialog = new SourceInfoDialog(frame);
            dialog.setVisible(true);
        });
    }
}
