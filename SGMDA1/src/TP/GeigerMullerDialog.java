package TP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

public class GeigerMullerDialog extends JDialog {
    static JTextField txtTubeLength, txtTubeDiameter, txtWallThickness, txtWindowThickness;
    static JTextField txtVoltage, txtVoltageSeuil, txtK2;
    static JTextField txtDetectionThreshold, txtEfficiency, txtCountRate, txtDeadTime;
    static JComboBox<String> comboBoxTubeMaterial, comboBoxWindowMaterial, comboBoxGasType;
    private JButton btnOK, btnCancel, btnInitialize;
    String pathFile = "DetectorParametres\\DetectorParametres.txt";

    public GeigerMullerDialog(JFrame parent) {
        super(parent, "Geiger-Müller Detector Parameters", true);
        setSize(820, 430);
        setLayout(null);

        
        // Paramètres géométriques
        //String Ln=  traveauPratique.textField_3.getText();
        createLabel("Detector length", 10, 10);
        txtTubeLength = createTextField("", 170+15, 10);
        createLabel("cm", 280, 10);
        //String ry=  traveauPratique.textField_1.getText();
        createLabel("Detector radius", 10, 40);
        txtTubeDiameter = createTextField("", 170+15, 40);
        createLabel("cm", 280, 40);

        createLabel("Tube material", 10, 70);
        String[] materials = {"Stainless steel", "Glass"};
        comboBoxTubeMaterial = new JComboBox<>(materials);
        comboBoxTubeMaterial.setBounds(185, 70, 80, 25);
        add(comboBoxTubeMaterial);

        createLabel("Wall thickness", 10, 100);
        txtWallThickness = createTextField("0.5", 170+15, 100);
        createLabel("mm", 280, 100);

        createLabel("Window material", 10, 130);
        String[] windowMaterials = {"Mica", "Glass"};
        comboBoxWindowMaterial = new JComboBox<>(windowMaterials);
        comboBoxWindowMaterial.setBounds(185, 130, 80, 25);
        add(comboBoxWindowMaterial);

        createLabel("Window thickness", 10, 160);
        txtWindowThickness = createTextField("1", 170+15, 160);
        createLabel("µm", 280, 160);

        // Paramètres du gaz
        createLabel("Gas type", 10, 190);
        String[] gasTypes = {"Xénon", "Argon", "Néon", "Hélium", "krypton"};
        comboBoxGasType = new JComboBox<>(gasTypes);
        comboBoxGasType.setBounds(190, 190, 80, 25);
        add(comboBoxGasType);

        

        // Paramètres électriques - Alignés sur la même ligne avec positions absolues
        createLabel("Voltage (V1)", 310, 250);
        txtVoltage = createTextField("220", 470, 250);
        createLabel("V", 580, 250);   // Un label à côté de Tension d'alimentation
        
        createLabel("Voltage threshold (Vp)", 10, 250);
        txtVoltageSeuil = createTextField("120", 170+15, 250);
        createLabel("V", 280, 250);   // Un label à côté de Référence Tension

        createLabel("Voltage  (V2)", 610, 250);
        txtK2 = createTextField("660", 700, 250);
        
       

        createLabel("Dead time", 10, 280);
        txtDeadTime = createTextField("200", 170+15, 280);
        createLabel("µs", 280, 280);

        loadDataFromFile();
        
        // Boutons
        btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	double VV1 = Double.parseDouble(txtVoltage.getText());
            	double VV2 = Double.parseDouble(txtK2.getText());
            	int V_fonct =(int) (VV1+(VV2-VV1)/2);
            	traveauPratique.textField_10.setText(String.valueOf(V_fonct));
            	
            	saveDataToFile();
            	
            	traveauPratique.textField_1.setText(txtTubeDiameter.getText());
            	traveauPratique.textField_3.setText(txtTubeLength.getText());
            	
            	
            	dispose();
            }
        });
            
        btnOK.setBounds(50, 340, 100, 30);
        add(btnOK);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	dispose();
            }
        });
           
        
        btnCancel.setBounds(180, 340, 100, 30);
        add(btnCancel);

        btnInitialize = new JButton("Initialize");
        btnInitialize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	pathFile = "initialDetectorGM\\DetectorParametres.txt";
            	loadDataFromFile();
            	pathFile = "DetectorParametres\\DetectorParametres.txt";	
            	
        
      }
    });
        btnInitialize.setBounds(310, 340, 100, 30);
        add(btnInitialize);
    
    
    
    }

    private JTextField createTextField(String text, int x, int y) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, 80, 25);
        add(textField);
        return textField;
    }

    private void createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 160, 25);
        add(label);
    }
    
    //--------------------lire le contenu de la soucre dejà enregistré----------
    private void loadDataFromFile() {
        try {
            Path path = Paths.get(pathFile);
            if (!Files.exists(path)) {
                return; // Ne rien faire si le fichier n'existe pas
            }

            java.util.List<String> lines = Files.readAllLines(path);
            if (lines.size() < 9) {
                throw new IOException("File incorrectly formatted!");
            }

            // Remplir les champs avec les données du fichier
            txtTubeLength.setText(lines.get(0));
            txtTubeDiameter.setText(lines.get(1));
            comboBoxTubeMaterial.setSelectedItem(lines.get(2));
            txtWallThickness.setText(lines.get(3));
            comboBoxWindowMaterial.setSelectedItem(lines.get(4));
            txtWindowThickness.setText(lines.get(5));
            comboBoxGasType.setSelectedItem(lines.get(6));
            txtVoltageSeuil.setText(lines.get(7));
            txtVoltage.setText(lines.get(8));
            txtK2.setText(lines.get(9));           
            txtDeadTime.setText(lines.get(10));           

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile))) {
        	writer.write(txtTubeLength.getText() + "\n");
        	writer.write(txtTubeDiameter.getText() + "\n");
        	writer.write(comboBoxTubeMaterial.getSelectedItem().toString() + "\n");
            writer.write(txtWallThickness.getText() + "\n");
            writer.write(comboBoxWindowMaterial.getSelectedItem().toString() + "\n");
            writer.write(txtWindowThickness.getText() + "\n");
            writer.write(comboBoxGasType.getSelectedItem().toString() + "\n");
            writer.write(txtVoltageSeuil.getText() + "\n");
            writer.write(txtVoltage.getText() + "\n");
            writer.write(txtK2.getText() + "\n");           
            writer.write(txtDeadTime.getText() + "\n");

            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GeigerMullerDialog dialog = new GeigerMullerDialog(frame);
        dialog.setVisible(true);
    }
}
