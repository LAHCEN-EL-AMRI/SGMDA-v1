package TP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import java.awt.Label;
import java.awt.Toolkit;

public class traveauPratique extends JFrame {
static String execution="run";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//static String fichier="rien";
	//String fichie;
	static JTextField textField;
	static JTextField textField_1;
	static JTextField textField_2;
	static JPanel panel_1, panel_3;
	static JPanel panel;
	JPanel panel_2;
	static JTextField textField_3;
	static JComboBox<String> comboBox, comboBox_1;	
	static JTextField textField_4;
	static JTextField textField_5;
	static JTextField textField_6;
	static JLabel lblNewLabel_18, lblNewLabel_19, lblNewLabel_20;
	static JFXPanel pnl;
	static JTextField textField_7;
	static JLabel lblNewLabel_3;
	static JLabel lblNewLabel_7;
	static JTextField textField_8;
	static JTextField textField_9;
	static double Abandce1, Abandce2;
	static JLabel lblNewLabel_1;
	static JLabel lblNewLabel_5;
	static JLabel lblNewLabel_9;
	static int	zoomplus=-70;
	static JTextField textField_10;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {	            	
	                // Créer l'instance de la fenêtre principale	            	
	                traveauPratique frame = new traveauPratique();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            }
	    });
	}

	/**
	 * Create the frame.
	 */
	public traveauPratique() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons\\image.png"));
		setTitle("SGMDA (Simulation of Geiger Muller Detector and Attenuation)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1282, 674);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		//----------------------------------------------------
		
		JLabel lblNewLabel = new JLabel("Source :");
		
		lblNewLabel_1 = new JLabel("");
		
		JLabel lblNewLabel_2 = new JLabel("Energy1(keV) :");
		
		lblNewLabel_3 = new JLabel("");
		
		JLabel lblNewLabel_4 = new JLabel("Iγ :");
		
		lblNewLabel_5 = new JLabel("");
		
		JLabel lblNewLabel_6 = new JLabel("Energy2(keV) :");
		
		lblNewLabel_7 = new JLabel("");
		
		JLabel lblNewLabel_8 = new JLabel("Iγ :");
		
		lblNewLabel_9 = new JLabel("");
		
		JLabel lblNewLabel_10 = new JLabel("Activity(Bq)");
		
		textField = new JTextField();
		
		textField_4 = new JTextField();
		
		textField_7 = new JTextField();
		
		//---------------------------------------------------
		
		JButton btnNewButton_1 = new JButton("Radioactive source parameters");
		JButton btnNewButton_2 = new JButton("GM Detector parameter");
		JButton btnNewButton_3 = new JButton("mixed attenuation plate");
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Radioactive source parameters");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.doClick();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Detector (GM) parameter");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.doClick();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Mixed attenuation plate");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.doClick();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Exit");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);


			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		 
		JMenu mnNewMenu_1 = new JMenu("Gamma-ray interaction");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Radioactive source.");
		mntmNewMenuItem.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("HPGe detector ");
		mntmNewMenuItem_5.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Calibration");
		mntmNewMenuItem_6.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Background noise");
		mntmNewMenuItem_7.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Photopeak");
		mntmNewMenuItem_8.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Compton peak (continuum, edge)");
		mntmNewMenuItem_9.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Escape peaks");
		mntmNewMenuItem_10.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Backscatter");
		mntmNewMenuItem_11.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_11);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SourceInfoDialog dialog = new SourceInfoDialog(traveauPratique.this);  // Passer l'instance de la fenêtre principale
                dialog.setVisible(true);  // Ouvrir la fenêtre modale
				
			}
		});
		toolBar.add(btnNewButton_1);
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GeigerMullerDialog dialog = new GeigerMullerDialog(traveauPratique.this);  // Passer l'instance de la fenêtre principale
                dialog.setVisible(true);  // Ouvrir la fenêtre modale
				
				
			}
		});
		toolBar.add(btnNewButton_2);
		
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PlaqueMixt dialog = new PlaqueMixt(traveauPratique.this);  // Passer l'instance de la fenêtre principale
                dialog.setVisible(true);  // Ouvrir la fenêtre modale
					
				
			}
		});
		toolBar.add(btnNewButton_3);
		
		ImageIcon iconzoomplus = new ImageIcon("icons\\zoom.png");
		JButton btnNewButton_4 = new JButton(iconzoomplus);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			     zoomplus= zoomplus+5;
				
				
			}
		});
		toolBar.add(btnNewButton_4);
		
		panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setForeground(new Color(255, 255, 255));
		

		
		textField.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Detector radius:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Source-detector distance:");		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		
		
		// Créer une boîte de sélection (JComboBox) avec des options de fruits 
		String[] fruits = {"Air","Clear"}; 
		comboBox = new JComboBox<>(fruits);
		// Ajouter un écouteur d'action
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'élément sélectionné
                String selectedItem = (String) comboBox.getSelectedItem();

                // Interagir en fonction de l'élément sélectionné
                if ("Clear".equals(selectedItem)) {
                	traveauPratique.textField_5.setText("0");
                	traveauPratique.textField_5.setEnabled(false);  // Empêche la saisie
                } else if ("Air".equals(selectedItem)) {
                	traveauPratique.textField_5.setEnabled(true);
                	if (traveauPratique.textField_5.getText().equals("0")) {
                		traveauPratique.textField_5.setText("0.5");}
                }
            }
        });
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1233, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addGap(19))
		);
		
		ImageIcon iconzoommoin = new ImageIcon("icons\\zoomm.png");
		JButton btnNewButton_8 = new JButton(iconzoommoin);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoomplus=zoomplus-5;
				
			}
		});
		toolBar.add(btnNewButton_8);
		
		ImageIcon iconsave = new ImageIcon("icons\\save.png");
		JButton btnNewButton_9 = new JButton(iconsave);
		toolBar.add(btnNewButton_9);
		
		ImageIcon iconhindl = new ImageIcon("icons\\not_view_data.png");
		JButton btnNewButton_10 = new JButton(iconhindl);
		toolBar.add(btnNewButton_10);
		
		JLabel lblNewLabel_13 = new JLabel("Environment");
		
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		
		// Initialisation du GroupLayout pour panel_1
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		panel_1.setLayout(gl_panel_1);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		
        lblNewLabel_18 = new JLabel("Time elapsed 0 s");
		
		lblNewLabel_19 = new JLabel("Cumulative count: 0 photons");
		
		lblNewLabel_20 = new JLabel("Total count: Pending...");

		JButton btnNewButton_5 = new JButton("Start");
		btnNewButton_5.setFont(new Font("Tahoma", Font.ITALIC, 15));
		btnNewButton_5.setForeground(Color.RED);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GeigerMullerDialog évitéErreur= new GeigerMullerDialog(false);
				//GeigerMullerDialog.main(null);
				//GeigerMullerDialog.dialog.setVisible(false);
				
				execution="run";
				
				
				lblNewLabel_18.setText("telapsed time: 0  s");
				lblNewLabel_18.repaint();
				
				lblNewLabel_19.setText("Cumulative count : 0 photons");
				lblNewLabel_19.repaint();
				
				lblNewLabel_20.setText("Total count: Pending...");
				lblNewLabel_20.repaint();
				
				textField.setText(textField.getText());
				
				double Abandce1 = Double.parseDouble(traveauPratique.lblNewLabel_5.getText());
				double Abandce2 = Double.parseDouble(traveauPratique.lblNewLabel_9.getText());
				
				int ActivityTxtField= (int) Double.parseDouble(textField.getText());
				int intensitTxtField1=(int) (Abandce1*ActivityTxtField*0.01);
				int intensitTxtField2=(int) (Abandce2*ActivityTxtField*0.01);
				textField_4.setText(String.valueOf(intensitTxtField1));
				textField_7.setText(String.valueOf(intensitTxtField2));
				
				
				//-------------Message d'Avertissement en cas d'un seul plaque-----------
				 double epesseurEror = Double.parseDouble(traveauPratique.textField_8.getText())*0.1; 
				 double DistanceSourcePlaqueEror = Double.parseDouble(traveauPratique.textField_9.getText())+epesseurEror; 
				 double DistanceSourceDetectorEror = Double.parseDouble(traveauPratique.textField_2.getText()); 
				 
				 String selectedItemm = (String) traveauPratique.comboBox_1.getSelectedItem();			        
				if(DistanceSourceDetectorEror<DistanceSourcePlaqueEror && !"Clear".equals(selectedItemm )&& !"Mixed".equals(selectedItemm )) {	
				 
				JFrame fram = new JFrame("Warning");
				JOptionPane.showMessageDialog(fram, "Attention!, The Source-Plate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);
				
			            return; // Quitte la méthode main()			        
				}
				//-------------Message d'Avertissement en cas des plaques Mixt-----------
				PlaqueMixt pourEviterInitialise = new PlaqueMixt(null);
				              //----Plomb-------
				if("Mixed".equals(selectedItemm )) {
				double epesseurPlombEror = Double.parseDouble(PlaqueMixt.thicknessFields[0].getText())*0.1; 
				double DistanceSourcePlaquePlombEror = Double.parseDouble(PlaqueMixt.DistanceFields[0].getText())+epesseurPlombEror; 
				
				if(DistanceSourceDetectorEror<DistanceSourcePlaquePlombEror && PlaqueMixt.checkBoxes[0].isSelected()) {						 
					JFrame fram = new JFrame("Avertissement");
					JOptionPane.showMessageDialog(fram, "Attention!, the Source-Lead Plate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);	
				            return; // Quitte la méthode main()			        
					}
				              //----Aluminium-------
				double epesseurAluminiumEror = Double.parseDouble(PlaqueMixt.thicknessFields[1].getText())*0.1; 
				double DistanceSourcePlaqueAluminiumEror = Double.parseDouble(PlaqueMixt.DistanceFields[1].getText())+epesseurAluminiumEror; 
				if(DistanceSourceDetectorEror<DistanceSourcePlaqueAluminiumEror && PlaqueMixt.checkBoxes[1].isSelected()) {						 
					JFrame fram = new JFrame("Warning");
					JOptionPane.showMessageDialog(fram, "Attention!, the Source-Aluminium Plate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);	
				            return; // Quitte la méthode main()			        
					}
	                             //----Cuivre-------
					double epesseurCuivreEror = Double.parseDouble(PlaqueMixt.thicknessFields[2].getText())*0.1; 
					double DistanceSourcePlaqueCuivreEror = Double.parseDouble(PlaqueMixt.DistanceFields[2].getText())+epesseurCuivreEror; 
					if(DistanceSourceDetectorEror<DistanceSourcePlaqueCuivreEror && PlaqueMixt.checkBoxes[2].isSelected()) {						 
						JFrame fram = new JFrame("Warning");
						JOptionPane.showMessageDialog(fram, "Warning!, the Source-CopperPlate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);	
					            return; // Quitte la méthode main()			        
						}
					              //----Fer-------
					double epesseurFerEror = Double.parseDouble(PlaqueMixt.thicknessFields[3].getText())*0.1; 
					double DistanceSourcePlaqueFerEror = Double.parseDouble(PlaqueMixt.DistanceFields[3].getText())+epesseurFerEror; 
					if(DistanceSourceDetectorEror<DistanceSourcePlaqueFerEror && PlaqueMixt.checkBoxes[3].isSelected()) {						 
						JFrame fram = new JFrame("Warning");
						JOptionPane.showMessageDialog(fram, "Warning!, the Source-Iron Plate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);	
					            return; // Quitte la méthode main()			        
						}
					
					                //----Baryum-------
					double epesseurBaryumEror = Double.parseDouble(PlaqueMixt.thicknessFields[4].getText())*0.1; 
					double DistanceSourcePlaqueBaryumEror = Double.parseDouble(PlaqueMixt.DistanceFields[4].getText())+epesseurBaryumEror; 
					if(DistanceSourceDetectorEror<DistanceSourcePlaqueBaryumEror&& PlaqueMixt.checkBoxes[4].isSelected() ) {						 
						JFrame fram = new JFrame("Warning");
						JOptionPane.showMessageDialog(fram, "Warning!, the Source-Barium Plate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);	
					            return; // Quitte la méthode main()			        
						}
					                //----Cadmium-------
					double epesseurCadmiumEror = Double.parseDouble(PlaqueMixt.thicknessFields[5].getText())*0.1; 
					double DistanceSourcePlaqueCadmiumEror = Double.parseDouble(PlaqueMixt.DistanceFields[5].getText())+epesseurCadmiumEror; 
					if(DistanceSourceDetectorEror<DistanceSourcePlaqueCadmiumEror && PlaqueMixt.checkBoxes[5].isSelected()) {						 
						JFrame fram = new JFrame("Warning");
						JOptionPane.showMessageDialog(fram, "Warning!, the Source-Cadmium Plate distance must be less than the Source-Detector distance", "Error", JOptionPane.WARNING_MESSAGE);	
					            return; // Quitte la méthode main()			        
						} 
				}
				//-----------------------------------------------------------------------
				
				panel_1.removeAll();				
               GrapheSourceDetector.jfxPanel = new JFXPanel();
	            
	            Platform.runLater(() -> {
	                GrapheSourceDetector app = new GrapheSourceDetector();
	                app.initScene(GrapheSourceDetector.jfxPanel);
	            });
				
				
	         // Déclaration et initialisation du JFXPanel
	            pnl = new JFXPanel();
	            pnl = new GrapheSourceDetector();
	            pnl = GrapheSourceDetector.jfxPanel;

	            // Ajout de pnl à panel_1
	            panel_1.setLayout(new java.awt.BorderLayout());
	            panel_1.add(pnl);
	            panel_1.validate();

	            // Rendre pnl redimensionnable en fonction de panel_1
	            panel_1.addComponentListener(new java.awt.event.ComponentAdapter() {
	                @Override
	                public void componentResized(java.awt.event.ComponentEvent e) {
	                    pnl.setSize(panel_1.getWidth(), panel_1.getHeight());
	                    pnl.repaint(); // Redessiner le contenu si nécessaire
	                }
	            });

				
				
				panel_3.removeAll();
				JPanel pnl1 = new JPanel();				
				pnl1 = new BackgroundNoiseSimulatorWithTimeDisplay();  
				//pnl1=BackgroundNoiseSimulatorWithTimeDisplay.chartPanel;
				panel_3.setLayout(new java.awt.BorderLayout());
				panel_3.add(pnl1);
				panel_3.validate();	
				
				
				
			}
		});
		
		
		JLabel lblNewLabel_14 = new JLabel("detector height :");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Intensity1:");
		
		
		
		textField_4.setEnabled(false);  // Empêche la saisie
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Noise (photons/s)");
		
		textField_5 = new JTextField();
		textField_5.setText("0.5");
		textField_5.setColumns(10);
		//textField_5.setEnabled(false);  // Empêche la saisie
		
		JLabel lblNewLabel_17 = new JLabel("Measurement time (s)");
		
		textField_6 = new JTextField();
		textField_6.setText("20");
		textField_6.setColumns(10);
		textField_6.setForeground(Color.RED);
		// Définir une grande taille de police (exemple : taille 24)
		textField_6.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel_21 = new JLabel("Intensity2:");
		
		
		textField_7.setEnabled(false);  // Empêche la saisie
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_22 = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		lblNewLabel_22.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_22.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_23 = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		lblNewLabel_23.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_23.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_24 = new JLabel("Attenuation plates:");
		
		String[] plaque = {"Clear", "Lead (Pb)", "Aluminium (Al)", "Copper (Cu)", "Iron (Ir)", "Barium", "Cadmium (Cd)", "Mixed"};

		comboBox_1 = new JComboBox(plaque);
		comboBox_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Récupérer l'élément sélectionné
		        String selectedMaterial = (String) comboBox_1.getSelectedItem();

		        // Vous pouvez maintenant utiliser la valeur selectedMaterial pour toute action
		        if (selectedMaterial != null) {
		            switch (selectedMaterial) {
		                case "Clear":
		                    // Action pour Vide
		                   // System.out.println("Plaque vide sélectionnée");
		                    break;
		                case "Lead (Pb)":
		                    // Action pour Plomb (Pb)
		                   // System.out.println("Plomb sélectionné");
		                    break;
		                case "Aluminium (Al)":
		                    // Action pour Aluminium (Al)
		                   // System.out.println("Aluminium sélectionné");
		                    break;
		                case "Copper (Cu)":
		                    // Action pour Cuivre (Cu)
		                    //System.out.println("Cuivre sélectionné");
		                    break;
		                case "Iron (Ir)":
		                    // Action pour Fer
		                    //System.out.println("Fer sélectionné");
		                    break;
		                case "Barium":
		                    // Action pour Baryum
		                   // System.out.println("Baryum sélectionné");
		                    break;
		                case "Cadmium (Cd)":
		                    // Action pour Cadmium (Cd)
		                    //System.out.println("Cadmium sélectionné");
		                    break;
		                case "Mixed":
		                	PlaqueMixt dialog = new PlaqueMixt(traveauPratique.this);  // Passer l'instance de la fenêtre principale
		                    dialog.setVisible(true);  // Ouvrir la fenêtre modale
		    					
		                    break;
		                default:
		                    //System.out.println("Matériau non sélectionné");
		                    break;
		            }
		        }
		    }
		});

		
		JLabel lblNewLabel_25 = new JLabel("Thickness(mm):");
		
		textField_8 = new JTextField("1");
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("Source-plate distance:");
		
		textField_9 = new JTextField();
		textField_9.setText("2");
		textField_9.setColumns(10);
		JButton btnNewButton_7 = new JButton("Initialize");
		btnNewButton_7.setFont(new Font("Tahoma", Font.ITALIC, 15));
		btnNewButton_7.setForeground(Color.RED);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				lblNewLabel_18.setText("time elapsed 0  s");
				lblNewLabel_18.repaint();
				
				lblNewLabel_19.setText("Cumulative count : 0 photons");
				lblNewLabel_19.repaint();
				
				lblNewLabel_20.setText("Total count :     ");
				lblNewLabel_20.repaint();
				
				
			}
		});
		
		JLabel lblNewLabel_27 = new JLabel("Voltage (V)");
		
		textField_10 = new JTextField();
		textField_10.setText("800");
		textField_10.setColumns(10);
		
		JButton btnNewButton = new JButton("Stop");
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				execution="Stop";
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(99)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblNewLabel_6)
					.addGap(11)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(82)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(144))
				.addComponent(lblNewLabel_22, GroupLayout.PREFERRED_SIZE, 1233, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblNewLabel_23, GroupLayout.PREFERRED_SIZE, 1233, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addComponent(btnNewButton_7)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_18, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_19, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_20, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(74)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_21, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(163)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_17, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(143, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(268)
							.addComponent(lblNewLabel_25, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(690)
							.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(406)
							.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(149)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_24, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(527)
							.addComponent(lblNewLabel_26, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
					.addGap(45)
					.addComponent(lblNewLabel_27, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_6))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_8))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_10)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(4)
					.addComponent(lblNewLabel_22, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(8)
											.addComponent(lblNewLabel_11))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(5)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(5)
											.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(5)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(3)
													.addComponent(lblNewLabel_12))
												.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(8)
											.addComponent(lblNewLabel_13))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(9)
									.addComponent(lblNewLabel_14)))
							.addGap(8))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_21)
								.addComponent(lblNewLabel_15)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_17)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_16)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addComponent(lblNewLabel_25))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(16)
							.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(16)
							.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_23)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(5)
									.addComponent(lblNewLabel_26))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_27)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(11)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addComponent(lblNewLabel_24)))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_5)
						.addComponent(lblNewLabel_18)
						.addComponent(lblNewLabel_19)
						.addComponent(lblNewLabel_20)
						.addComponent(btnNewButton_7)
						.addComponent(btnNewButton))
					.addGap(11))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
