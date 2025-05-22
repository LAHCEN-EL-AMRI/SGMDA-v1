package TP;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;


import java.awt.*;


import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

public class SourceInfoDialog extends JDialog {
    private JTextField txtSourceName, txtEnergy1, txtEnergy1Abundance, txtEnergy2, txtEnergy2Abundance;
    private JTextField txtHalfLife, txtDecayMode, txtActivityDate,  txtGammaDistribution, txtPosition;
    static JTextField txtGeometry;
    static JComboBox comboBoxTime, comboBoxSource;
    String FILE_PATH_Source;
    String chemainFile ;
    
    
    public SourceInfoDialog(JFrame parent) {
        super(parent, "Informations Source Radioactive", true);
        setSize(500, 500);
        setLayout(null);

     // Charger les données depuis le fichier
        
        
        
               // Initialisation des champs de texte
        String[] sources = {"Source 1", "Source 2", "Source 3"};
        comboBoxSource = new JComboBox(sources);
        txtSourceName = createTextField("", 170, 10);
   	    txtEnergy1 = createTextField("", 170, 40);
        
        
        txtEnergy1Abundance = createTextField("", 170, 70);
        
        
        txtEnergy2 = createTextField("", 170, 100);
       
        
        txtEnergy2Abundance = createTextField("", 170, 130);
        
        
        txtHalfLife = createTextField("", 170, 160);
        
        String[] Time = {"y", "m", "d", "h", "min", "s"};
        comboBoxTime = new JComboBox(Time);
        comboBoxTime.setBounds(380, 160, 80, 25);
        add(comboBoxTime);
        
        txtDecayMode = createTextField("", 170, 190);
        txtActivityDate = createTextField("", 170, 220);
        
        
        

        
        Preferences prefs = Preferences.userNodeForPackage(getClass());
     
        
        
        
        
        txtGeometry = createTextField("", 170, 250);
        //txtGeometry.setBounds(290, 250, 80, 25);
       
        
        txtGammaDistribution = createTextField("", 170, 280);
        txtPosition = createTextField("", 170, 310);
        
        createLabel("keV", 380, 40);
        createLabel("%", 380, 70);
        createLabel("keV", 380, 100);
        createLabel("%", 380, 130);
        createLabel("Bq", 380, 220);
        //createLabel("Radius", 240, 250);
        //createLabel("cm", 380, 250);
        createLabel("cm", 380, 310);
        
        
        
        
        
        Fille_Path_Source();
        FILE_PATH_Source=chemainFile;
        loadDataFromFile();
        
        
        comboBoxSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'élément sélectionné
                String selectedItem = (String) comboBoxSource.getSelectedItem();
                String fileS ="FileSource\\";
                String combbSelected=fileS+selectedItem +".txt";
                // Interagir en fonction de l'élément sélectionné
                
                
                	chemainFile=combbSelected;
                	FILE_PATH_Source=chemainFile;
                	loadDataFromFile();
                	
                
            }
        });
        
        comboBoxSource.setBounds(380, 10, 80, 25);
        add(comboBoxSource);
        
       

        // Création des labels
        createLabel("Source Name:", 10, 10);
        createLabel("Energy 1 (keV):", 10, 40);
        createLabel("Energy Abundance 1(%):", 10, 70);
        createLabel("Energy 2 (keV):", 10, 100);
        createLabel("Energy Abundance 2(%):", 10, 130);
        createLabel("Half-Life (T1/2):", 10, 160);
        createLabel("Decay Mode:", 10, 190);
        createLabel("Activity:", 10, 220);
        createLabel("Source Geometry:", 10, 250);
        createLabel("Gamma Distribution:", 10, 280);
        createLabel("Source-detector position(cm):", 10, 310);

        // Création des boutons
        JButton AjoutSource = new JButton("ajoute autre source");
        AjoutSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//comboBoxSource.addItem("Nouvelle Option Dynamique");
			}
		});
        AjoutSource.setBounds(80, 360, 210, 30);
        
        JButton btnOK = new JButton("OK");
        btnOK.setBounds(80, 400, 80, 30);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(190, 400, 100, 30);
        
        JButton initial = new JButton("Initialize");
        initial.setBounds(320, 400, 100, 30);
        

        // Actions des boutons
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//DefaultComboBoxModel<String> newModel = new DefaultComboBoxModel<>();
            	//newModel.addElement(txtSourceName.getText());  // Ajoute l'élément à la première position
            	//comboBoxSource.setModel(newModel);  // Applique le nouveau modèle
            	//System.out.println(txtSourceName.getText());

            	 //JOptionPane.showMessageDialog(null, "Sélection sauvegardée !");
            	
            	//-----------sauvegarder le contenu des txtField----
            	saveChemainFile();
            	saveDataToFile();
            	chemainFile="FileSource\\"+txtSourceName.getText()+".txt";
            	FILE_PATH_Source=chemainFile;
            	
            	//--------------------------------------------------
            	
            	traveauPratique.lblNewLabel_1.setText(txtSourceName.getText()); // nome de la source	
            	traveauPratique.textField.setText(txtActivityDate.getText()); 	//Activité
            	traveauPratique.lblNewLabel_3.setText(txtEnergy1.getText()); //Energie1
            	traveauPratique.lblNewLabel_7.setText(txtEnergy2.getText()); //Energie2
            	traveauPratique.lblNewLabel_5.setText(txtEnergy1Abundance.getText()); //Abandance1
            	traveauPratique.lblNewLabel_9.setText(txtEnergy2Abundance.getText()); //Abandance2txtPosition
            	traveauPratique.textField_2.setText(txtPosition.getText()); //Distance source Detecteur
            	
            	double Abandce1 = Double.parseDouble(traveauPratique.lblNewLabel_5.getText());
				double Abandce2 = Double.parseDouble(traveauPratique.lblNewLabel_9.getText());
            	
            	int ActivityTxtField= (int) Double.parseDouble(traveauPratique.textField.getText());
				int intensitTxtField1=(int) (Abandce1*ActivityTxtField*0.01);
				int intensitTxtField2=(int) (Abandce2*ActivityTxtField*0.01);
				traveauPratique.textField_4.setText(String.valueOf(intensitTxtField1));
				traveauPratique.textField_7.setText(String.valueOf(intensitTxtField2));
            	
            	
            	traveauPratique.lblNewLabel_1.setForeground(Color.BLUE);
            	traveauPratique.lblNewLabel_3.setForeground(Color.BLUE);
            	traveauPratique.lblNewLabel_5.setForeground(Color.BLUE);
            	traveauPratique.lblNewLabel_7.setForeground(Color.BLUE);
            	traveauPratique.lblNewLabel_9.setForeground(Color.BLUE);
            	traveauPratique.textField.setForeground(Color.BLUE);
            	
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        
        initial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 File selectedFile = null;
            	JFileChooser fileChooser = new JFileChooser(new File("initialSource"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                   // System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
                }
            	
                chemainFile=selectedFile.getAbsolutePath();
            	FILE_PATH_Source=chemainFile;
            	loadDataFromFile();
                
            }
        });
        

        // Ajouter les boutons à la fenêtre
        //add(AjoutSource);
        add(btnOK);
        add(btnCancel);
        add(initial);
       
    }

    private void createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        add(label);
    }

    private JTextField createTextField(String defaultValue, int x, int y) {
        JTextField textField = new JTextField(defaultValue);
        textField.setBounds(x, y, 200, 25);
        add(textField);
        return textField;
    }

    private void resetFields() {
        txtSourceName.setText("60Co");
        txtEnergy1.setText("654");
        txtEnergy1Abundance.setText("85");
        txtEnergy2.setText("1173");
        txtEnergy2Abundance.setText("99");
        txtHalfLife.setText("30.1");
        txtDecayMode.setText("Béta");
        txtActivityDate.setText("257600");
        txtGeometry.setText("Point source");
        txtGammaDistribution.setText("Isotropique");
        txtPosition.setText("30");
    }
    
    //---------Souvegarde le contenus de source----------
 // Méthode pour charger les données depuis le fichier "
    
    String FichierpourTrouverChemainSource= "FileSource\\ChemainSource.txt";
    private void Fille_Path_Source() {
    	try {
            Path path = Paths.get(FichierpourTrouverChemainSource);
            if (!Files.exists(path)) {
                return; // Ne rien faire si le fichier n'existe pas
            }

            java.util.List<String> lines = Files.readAllLines(path);
            if (lines.size() < 1) {
                throw new IOException("File incorrectly formatted!");
            }  
            chemainFile = lines.get(0);
    	} catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file    : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    //----------pour sauvegarder le Chemain de la source----------
    private void saveChemainFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FichierpourTrouverChemainSource))) {  
        	
            writer.write(chemainFile + "\n");   
           // System.out.println("chemainFile :  " + chemainFile);
            
            //JOptionPane.showMessageDialog(this, "Données enregistrées avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file    : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //--------------------lire le contenu de la soucre dejà enregistré----------
    private void loadDataFromFile() {
        try {
            Path path = Paths.get(FILE_PATH_Source);
            if (!Files.exists(path)) {
                return; // Ne rien faire si le fichier n'existe pas
            }

            java.util.List<String> lines = Files.readAllLines(path);
            if (lines.size() < 11) {
                throw new IOException("Fichier mal formaté !");
            }

            // Remplir les champs avec les données du fichier
            comboBoxSource.setSelectedItem(lines.get(0));
            txtSourceName.setText(lines.get(1));
            txtEnergy1.setText(lines.get(2));
            txtEnergy1Abundance.setText(lines.get(3));
            txtEnergy2.setText(lines.get(4));
            txtEnergy2Abundance.setText(lines.get(5));
            txtHalfLife.setText(lines.get(6));
            comboBoxTime.setSelectedItem(lines.get(7));
            txtDecayMode.setText(lines.get(8));
            txtActivityDate.setText(lines.get(9));
            txtGeometry.setText(lines.get(10));
            txtGammaDistribution.setText(lines.get(11));
            txtPosition.setText(lines.get(12));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file   : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Méthode pour enregistrer les données dans "137Cs.txt"
    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_Source))) {
        	writer.write(comboBoxSource.getSelectedItem().toString() + "\n");
            writer.write(txtSourceName.getText() + "\n");
            writer.write(txtEnergy1.getText() + "\n");
            writer.write(txtEnergy1Abundance.getText() + "\n");
            writer.write(txtEnergy2.getText() + "\n");
            writer.write(txtEnergy2Abundance.getText() + "\n");
            writer.write(txtHalfLife.getText() + "\n");
            writer.write(comboBoxTime.getSelectedItem().toString() + "\n");
            writer.write(txtDecayMode.getText() + "\n");
            writer.write(txtActivityDate.getText() + "\n");
            writer.write(txtGeometry.getText() + "\n");
            writer.write(txtGammaDistribution.getText() + "\n");
            writer.write(txtPosition.getText() + "\n");

            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file    : "+ e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
