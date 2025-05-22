package TP;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BackgroundNoiseSimulatorWithTimeDisplay extends JPanel  {
    //private DefaultCategoryDataset dataset;
    private JLabel cumulativeCountLabel; // JLabel pour afficher le comptage cumulé
    private JLabel totalCountLabel;      // JLabel pour afficher le comptage total à la fin
    private JLabel timeElapsedLabel;     // JLabel pour afficher le temps écoulé
    private int timeElapsed = 0;         // Temps écoulé (en secondes)
    private double cumulativeCount = 0;    // Comptage cumulé (somme des photons détectés)
    private Timer timer;
    static JFreeChart chart;
    static ChartPanel chartPanel;
    private XYSeries series;
    private XYSeriesCollection dataset;
    static String EnergySource,EnergySourceGaz, EnergySourceSeulPlaq, PlombPlaque;  
    static double targetTension;
    private double tempsMort;
    double lambda;
    double lamda1=0;
    double bruit1=0;
    double bruit;
    double LamdaBruit,Incertitly,LamdaBruit0, totalCountTst, EffetTempsMort;
    int cumInt1;
   
    public BackgroundNoiseSimulatorWithTimeDisplay() {
    	
      
        setSize(900, 600);
        

        // Initialiser l'interface graphique
        setupUI();
       
        bruit = (double) Double.parseDouble(traveauPratique.textField_5.getText());
        double intensity =  Double.parseDouble(traveauPratique.textField_4.getText());
        double intensity2 =  Double.parseDouble(traveauPratique.textField_7.getText());
        //---------------pour efficacité geometrique------------
        double R = Double.parseDouble(traveauPratique.textField_1.getText());  // Rayon du détecteur en cm
        double d = Double.parseDouble(traveauPratique.textField_2.getText());  // Distance entre la source et le détecteur en cm
        double eppeseurDectecto = Double.parseDouble(GeigerMullerDialog.txtWallThickness.getText())*0.1;
        // Calcul de la surface du détecteur (A_detecteur)
        double surface_detecteur = Math.PI * Math.pow(R, 2);
        double surface_Epesseur_detecteur = Math.PI * Math.pow(R, 2)-Math.PI * Math.pow(R-eppeseurDectecto, 2);

        // Calcul de la surface de la sphère (A_sphere)
        double surface_sphere = 4 * Math.PI * Math.pow(d, 2);
        
        
//----------------------angle solid en cas de source sous forme disk--------------
        double rg = R-eppeseurDectecto;       // Rayon du détecteur en cm
        //double rPrime = Double.parseDouble(SourceInfoDialog.txtGeometry.getText());  // Rayon de la source en cm
        //double dg = Double.parseDouble(traveauPratique.textField_2.getText());      // Distance entre la source et le détecteur en cm
       // double DD=(rPrime*d)/(rg-rPrime)+d;
        //double surface_sphereForSourcDisk = 4 * Math.PI * Math.pow(DD, 2);
       
           
            //--------------------------------------
            double fraction, fractionEpesseurDetector;
//----------------------angle solid en cas de source sous forme point-------------- 
            	
                     // Calcul de la fraction des photons atteignant le détecteur       
                     if (d < 1 * R) {       
        	         fraction=0.5;
                     fractionEpesseurDetector=1;            
                     }else {fraction =  (0.18041219280990245 -0.055785225830648585 * d + 0.006619831830070661 * Math.pow(d, 2) -2.7210248613863686E-4 * Math.pow(d, 3))*rg*rg;
                     
                    		 //fraction =(surface_detecteur-surface_Epesseur_detecteur) / surface_sphere; 
              // fractionEpesseurDetector=surface_Epesseur_detecteur/ surface_sphere;
                     } 
         
            
        //----------------pour efficacité intrinsèque--------------------------------       
        double densite = 0;
        String selectedItem6 = (String) GeigerMullerDialog.comboBoxGasType.getSelectedItem();
        if ("Argon".equals(selectedItem6)) {
        	 densite= 0.00178; // Argon (g/cm3)
        }
        if ("Xénon".equals(selectedItem6)) {
        	densite= 0.0059 ;  //Xénon (g/cm3)        	 
        }
        if ("Néon".equals(selectedItem6)) {
        	densite=0.00090 ;// Néon (g/cm3)       	
        } 
        if ("Hélium".equals(selectedItem6)) {
        	densite=0.0001786; //Hélium (g/cm3)      	
        }
        if ("krypton".equals(selectedItem6)) {
        	densite=0.0035; //Hélium (g/cm3)      	
        }
        
        
        EnergySourceGaz="Eg1";
        SectionEfficaceGaz.main(null);
        
        // Récupérer la valeur de l'atténuation la plus proche
        double closestAttenuation = SectionEfficaceGaz.getClosestAttenuation();       
        double sectionEfficace=closestAttenuation;
        double efficacitIntrinsèque;
        //System.out.println("Pourrrrrrrrrrrrrrrrrrr     "+ sectionEfficace);
        double longueur,L1=0,L2 = 0;
        double lon = Double.parseDouble(traveauPratique.textField_3.getText());
        double energyForLong1 = Double.parseDouble(traveauPratique.lblNewLabel_3.getText());
        if(energyForLong1>=1000) {L1=lon;}
        if(energyForLong1>=800 && energyForLong1<1000) {L1=lon/1.5;}
        if(energyForLong1>=500 && energyForLong1<800) {L1=lon/2;}
        if(energyForLong1>=250 && energyForLong1<500) {L1=lon/3;}
        if(energyForLong1>=100 && energyForLong1<250) {L1=lon/4;}
        if(energyForLong1>=0 && energyForLong1<100) {L1=lon/5;}
        longueur = L1;
            // Formule ajustée : η = 1 - exp(-σ * ρ * L)
          efficacitIntrinsèque=   1 - Math.exp(-sectionEfficace * densite * longueur);
        //System.out.println("efficacitIntrinsèque1     "+ efficacitIntrinsèque);
        
        
                           //-----------deuxiemme intrinsèque-----------
      //----------------pour efficacité intrinsèque--------------------------------
        EnergySourceGaz="Eg2";
        SectionEfficaceGaz.main(null);

        // Récupérer la valeur de l'atténuation la plus proche
        double closestAttenuation2 = SectionEfficaceGaz.getClosestAttenuation();       
        double sectionEfficace2=closestAttenuation2;
        double efficacitIntrinsèque2;
        
        double lon2 = Double.parseDouble(traveauPratique.textField_3.getText());
        double energyForLong2 = Double.parseDouble(traveauPratique.lblNewLabel_7.getText());
        if(energyForLong2>=1000) {L2=lon;}
        if(energyForLong2>=800 && energyForLong1<1000) {L2=lon2/1.5;}
        if(energyForLong2>=500 && energyForLong1<800) {L2=lon2/2;}
        if(energyForLong2>=250 && energyForLong1<500) {L2=lon2/3;}
        if(energyForLong2>=100 && energyForLong1<250) {L2=lon2/4;}
        if(energyForLong2>=0 && energyForLong1<100) {L2=lon2/5;}
        longueur = L2;
        //System.out.println("Section eficace de gaz en deuxiemme Energie     "+ sectionEfficace2);       
            // Formule ajustée : η = 1 - exp(-σ * ρ * L)
          efficacitIntrinsèque2=   1 - Math.exp(-sectionEfficace2 * densite * longueur);
          //System.out.println("efficacitIntrinsèque2     "+ efficacitIntrinsèque2);
          
          //------------------------tous les facteurs impactent de detecteur-----------------
       // Valeurs typiques pour un détecteur GM au xénon détectant des rayons gamma
          /* ICI
          double P = Double.parseDouble(GeigerMullerDialog.txtGasPressure.getText());   // Pression en atm
          double C = Double.parseDouble(GeigerMullerDialog.txtGasConcentration.getText()); // Concentration en %
          //double V = Double.parseDouble(GeigerMullerDialog.txtVoltage.getText());// Tension en V
          double V = Double.parseDouble(traveauPratique.textField_10.getText());
          double Zf = Double.parseDouble(GeigerMullerDialog.txtImpedance.getText());  // Impédance de fuite en ohms
          double RL = Double.parseDouble(GeigerMullerDialog.txtLoadResistance.getText());  // Résistance de charge en ohms
          double T = Double.parseDouble(GeigerMullerDialog.txtTemperature.getText()); // Température en K
          double P0 = Double.parseDouble(GeigerMullerDialog.txtPressure.getText());  // Pression atmosphérique en atm
         */
          double V = Double.parseDouble(traveauPratique.textField_10.getText());// ici doit etre suprimer en cas derevenir l etat initial
          double Vseuil = Double.parseDouble(GeigerMullerDialog.txtVoltageSeuil.getText());    // Tension de référence (V)         
          double V1 = Double.parseDouble(GeigerMullerDialog.txtVoltage.getText());
     	  double V2 = Double.parseDouble(GeigerMullerDialog.txtK2.getText());
     	  double correctionFactor=0.78;
          
          countingRate(V, Vseuil,  V1,  V2); 
        // Calcul de l'efficacité intrinsèque
          double efficaciteFenetreDialog = calculerEfficacite(V,  Vseuil,  V1,  V2)*correctionFactor;
          
          if(efficaciteFenetreDialog<0) {efficaciteFenetreDialog=0;}
          
          // Affichage du résultat
          //System.out.printf("Efficacité intrinsèque du détecteur GM au xénon : %.4f\n", efficaciteFenetreDialog);
          //System.out.printf("BSBSBSBSBSBSBSBSB : "+ targetTension);
      
        //---------------------------coeficient d aténuation masique----seul plaque-----------------------
        
                   
                  
                  //System.out.println("ensityPlaqueAttenuation:::      "+ densityPlaqueAttenuation);
                  double epesseurPlaque =Double.parseDouble(traveauPratique.textField_8.getText())*0.1;
                  
             //-------pour coeficient d aténuation masique de premier Energie------   
                  
                  //-------condition en cas "Clear" (vide)est selectinné---
                  double IntensityAtenue1=0,IntensityAtenue2=0; 
                  String selectedItemIci = (String) traveauPratique.comboBox_1.getSelectedItem();
     if ("Clear".equals(selectedItemIci)) {
                  	IntensityAtenue1=1; IntensityAtenue2=1;
                  	 //System.out.println("IntensityAtenue1 en cas vide     "+ IntensityAtenue1);
                  	// System.out.println("IntensityAtenue2 en cas vide     "+ IntensityAtenue2);  
     }else if(!"Mixed".equals(selectedItemIci)) {
                  //--------------------------------------------------
        
        EnergySourceSeulPlaq="EseulPlaq1";
        SectionEfficaceSeulPlaq.main(null);
        double densityPlaqueAttenuation= SectionEfficaceSeulPlaq.densityPlaque;
        double CoeficientAttenuationPlaque1 = SectionEfficaceSeulPlaq.getClosestAttenuation(); 
        //System.out.println("CoeficientAttenuationPlaque1     "+ CoeficientAttenuationPlaque1);  
        
        IntensityAtenue1=   Math.exp(-CoeficientAttenuationPlaque1 * densityPlaqueAttenuation * epesseurPlaque);
       // System.out.println("IntensityAtenue1      "+ IntensityAtenue1);
                  //-------pour coeficient d aténuation masique de deuxiemme Energie------
        EnergySourceSeulPlaq="EseulPlaq2";
        SectionEfficaceSeulPlaq.main(null);
        double CoeficientAttenuationPlaque2 = SectionEfficaceSeulPlaq.getClosestAttenuation(); 
        //System.out.println("CoeficientAttenuationPlaque2     "+ CoeficientAttenuationPlaque2);  
        IntensityAtenue2=   Math.exp(-CoeficientAttenuationPlaque2 * densityPlaqueAttenuation * epesseurPlaque);
        //System.out.println("IntensityAtenue2      "+ IntensityAtenue2); 
                     //----------- en cas d'une seule energie---------
        
        double CasEnergieZero = Double.parseDouble(traveauPratique.lblNewLabel_7.getText());
        if (CasEnergieZero==0) {// En cas d une seule energie
        	efficacitIntrinsèque2=1;
        	IntensityAtenue2=1;
        	//System.out.println("efficacitIntrinsèque2........     "+ efficacitIntrinsèque2); 
        }
        
}
                          //----------------------------------------------    
        //---------------------------------------------------------------------------
        double IntensityAteinentDetecteur;
        IntensityAteinentDetecteur = fraction*(efficacitIntrinsèque*IntensityAtenue1*intensity+efficacitIntrinsèque2*IntensityAtenue2*intensity2);  // Intensité initiale en photons/s
      
        
        
        //------------------------Coeficients d'atténuation en cas des plaques Mixt------------------------------------
        //String selectedItem5 = (String) traveauPratique.comboBox_1.getSelectedItem();
        if ("Mixed".equals(selectedItemIci)) {
        	
        	double IntensityAtenuePlomb1, IntensityAtenuePlomb2;
        	double IntensityAtenueAluminium1, IntensityAtenueAluminium2;
        	double IntensityAtenueCuivre1, IntensityAtenueCuivre2;
        	double IntensityAtenueFer1, IntensityAtenueFer2;
        	double IntensityAtenueBaryum1, IntensityAtenueBaryum2;
        	double IntensityAtenueCadmium1, IntensityAtenueCadmium2;
        	  //-Plomb------coeficient d aténuation masique de premier Energie------   
        	if (PlaqueMixt.checkBoxes[0].isSelected()) {
        		
            EnergySource="E1"; 
            PlombPlaque= "plomb";
            AttenuationFinder.main(null);
            double densityPlaqueAttenuationPlomb= AttenuationFinder.densityPlaquePlomb;
            double epesseurPlaquePlomb =Double.parseDouble(PlaqueMixt.thicknessFields[0].getText())*0.1;
            double CoeficientAttenuationPlaquePlomb1 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaquePlomb1     "+ CoeficientAttenuationPlaquePlomb1);  
            
            IntensityAtenuePlomb1=   Math.exp(-CoeficientAttenuationPlaquePlomb1 * densityPlaqueAttenuationPlomb * epesseurPlaquePlomb);
            //System.out.println("IntensityAtenuePlomb1      "+ IntensityAtenuePlomb1);
            
                      //-Plomb------ coeficient d aténuation masique de deuxiemme Energie------
            EnergySource="E2";
            AttenuationFinder.main(null);
            double CoeficientAttenuationPlaquePlomb2 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaquePlomb2     "+ CoeficientAttenuationPlaque2);  
            IntensityAtenuePlomb2=   Math.exp(-CoeficientAttenuationPlaquePlomb2* densityPlaqueAttenuationPlomb * epesseurPlaquePlomb);
            //System.out.println("IntensityAtenuePlomb2      "+ IntensityAtenuePlomb2);
        	}else {IntensityAtenuePlomb1=1; IntensityAtenuePlomb2=1; }
          //-Aluminium------coeficient d aténuation masique de premier Energie------   
        	if (PlaqueMixt.checkBoxes[1].isSelected()) {
            EnergySource="E1"; 
            PlombPlaque= "aluminium";
            AttenuationFinder.main(null);
            double densityPlaqueAttenuationAluminium= AttenuationFinder.densityPlaqueAluminium;
            double epesseurPlaqueAluminium =Double.parseDouble(PlaqueMixt.thicknessFields[1].getText())*0.1;
            double CoeficientAttenuationPlaqueAluminium1 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueAluminium1     "+ CoeficientAttenuationPlaqueAluminium1);  
            
            IntensityAtenueAluminium1=   Math.exp(-CoeficientAttenuationPlaqueAluminium1 * densityPlaqueAttenuationAluminium * epesseurPlaqueAluminium);
            //System.out.println("IntensityAtenueAluminium1      "+ IntensityAtenueAluminium1);
            
                      //-Aluminium------ coeficient d aténuation masique de deuxiemme Energie------
            EnergySource="E2";
            AttenuationFinder.main(null);
            double CoeficientAttenuationPlaqueAluminium2 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueAluminium2     "+ CoeficientAttenuationPlaqueAluminium2);  
            IntensityAtenueAluminium2=   Math.exp(-CoeficientAttenuationPlaqueAluminium2* densityPlaqueAttenuationAluminium * epesseurPlaqueAluminium);
            //System.out.println("IntensityAtenueAluminium2      "+ IntensityAtenueAluminium2);
        	}else {IntensityAtenueAluminium1=1; IntensityAtenueAluminium2=1;}
          //-Cuivre------coeficient d aténuation masique de premier Energie------ 
        	if (PlaqueMixt.checkBoxes[2].isSelected()) {
            EnergySource="E1"; 
            PlombPlaque= "cuivre";
            AttenuationFinder.main(null);
            double densityPlaqueAttenuationCuivre= AttenuationFinder.densityPlaqueCuivre;
            double epesseurPlaqueCuivre =Double.parseDouble(PlaqueMixt.thicknessFields[2].getText())*0.1;
            double CoeficientAttenuationPlaqueCuivre1 = AttenuationFinder.getClosestAttenuation(); 
           // System.out.println("CoeficientAttenuationPlaqueCuivre1     "+ CoeficientAttenuationPlaqueCuivre1);  
            
            IntensityAtenueCuivre1=   Math.exp(-CoeficientAttenuationPlaqueCuivre1 * densityPlaqueAttenuationCuivre * epesseurPlaqueCuivre);
            //System.out.println("IntensityAtenueCuivre1      "+ IntensityAtenueCuivre1);
            
                      //-Cuivre------ coeficient d aténuation masique de deuxiemme Energie------
            EnergySource="E2";
            AttenuationFinder.main(null);
            double CoeficientAttenuationPlaqueCuivre2 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueCuivre2     "+ CoeficientAttenuationPlaqueCuivre2);  
            IntensityAtenueCuivre2=   Math.exp(-CoeficientAttenuationPlaqueCuivre2* densityPlaqueAttenuationCuivre * epesseurPlaqueCuivre);
            //System.out.println("IntensityAtenueCuivre2      "+ IntensityAtenueCuivre2);
        	}else {IntensityAtenueCuivre1=1; IntensityAtenueCuivre2=1;}
          //-Fer------coeficient d aténuation masique de premier Energie------ 
        	if (PlaqueMixt.checkBoxes[3].isSelected()) {
            EnergySource="E1"; 
            PlombPlaque= "fer";
            AttenuationFinder.main(null);
            double densityPlaqueAttenuationFer= AttenuationFinder.densityPlaqueFer;
            double epesseurPlaqueFer =Double.parseDouble(PlaqueMixt.thicknessFields[3].getText())*0.1;
            double CoeficientAttenuationPlaqueFer1 = AttenuationFinder.getClosestAttenuation(); 
           // System.out.println("CoeficientAttenuationPlaqueFer1     "+ CoeficientAttenuationPlaqueFer1);  
            
            IntensityAtenueFer1=   Math.exp(-CoeficientAttenuationPlaqueFer1 * densityPlaqueAttenuationFer * epesseurPlaqueFer);
            //System.out.println("IntensityAtenueFer1      "+ IntensityAtenueFer1);
            
                      //-Fer------ coeficient d aténuation masique de deuxiemme Energie------
            EnergySource="E2";
            AttenuationFinder.main(null);
            double CoeficientAttenuationPlaqueFer2 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueFer2     "+ CoeficientAttenuationPlaqueFer2);  
            IntensityAtenueFer2=   Math.exp(-CoeficientAttenuationPlaqueFer2* densityPlaqueAttenuationFer * epesseurPlaqueFer);
           // System.out.println("IntensityAtenueFer2      "+ IntensityAtenueFer2);
        	} else {IntensityAtenueFer1=1; IntensityAtenueFer2=1;}
          //-Baryum------coeficient d aténuation masique de premier Energie--Cadmium----     
        	if (PlaqueMixt.checkBoxes[4].isSelected()) {
            EnergySource="E1"; 
            PlombPlaque= "baryum";
            AttenuationFinder.main(null);
            double densityPlaqueAttenuationBaryum= AttenuationFinder.densityPlaqueBaryum;
            double epesseurPlaqueBaryum =Double.parseDouble(PlaqueMixt.thicknessFields[4].getText())*0.1;
            double CoeficientAttenuationPlaqueBaryum1 = AttenuationFinder.getClosestAttenuation(); 
           // System.out.println("CoeficientAttenuationPlaqueBaryum1     "+ CoeficientAttenuationPlaqueBaryum1);  
            
            IntensityAtenueBaryum1=   Math.exp(-CoeficientAttenuationPlaqueBaryum1 * densityPlaqueAttenuationBaryum * epesseurPlaqueBaryum);
            //System.out.println("IntensityAtenueBaryum1      "+ IntensityAtenueBaryum1);
            
                      //-Baryum------ coeficient d aténuation masique de deuxiemme Energie------
            EnergySource="E2";
            AttenuationFinder.main(null);
            double CoeficientAttenuationPlaqueBaryum2 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueBaryum2     "+ CoeficientAttenuationPlaqueBaryum2);  
            IntensityAtenueBaryum2=   Math.exp(-CoeficientAttenuationPlaqueBaryum2* densityPlaqueAttenuationBaryum * epesseurPlaqueBaryum);
            //System.out.println("IntensityAtenueBaryum2      "+ IntensityAtenueBaryum2);
        	} else {IntensityAtenueBaryum1=1; IntensityAtenueBaryum2=1;}
          //-Cadmium------coeficient d aténuation masique de premier Energie--Cadmium----  
        	if (PlaqueMixt.checkBoxes[5].isSelected()) {
            EnergySource="E1"; 
            PlombPlaque= "cadmium";
            AttenuationFinder.main(null);
            double densityPlaqueAttenuationCadmium= AttenuationFinder.densityPlaqueCadmium;
            double epesseurPlaqueCadmium =Double.parseDouble(PlaqueMixt.thicknessFields[5].getText())*0.1;
            double CoeficientAttenuationPlaqueCadmium1 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueCadmium1     "+ CoeficientAttenuationPlaqueCadmium1);  
            
            IntensityAtenueCadmium1=   Math.exp(-CoeficientAttenuationPlaqueCadmium1 * densityPlaqueAttenuationCadmium * epesseurPlaqueCadmium);
            //System.out.println("IntensityAtenuecadmium1      "+ IntensityAtenueCadmium1);
            
                      //-Cadmium------ coeficient d aténuation masique de deuxiemme Energie------
            EnergySource="E2";
            AttenuationFinder.main(null);
            double CoeficientAttenuationPlaqueCadmium2 = AttenuationFinder.getClosestAttenuation(); 
            //System.out.println("CoeficientAttenuationPlaqueCadmium2     "+ CoeficientAttenuationPlaqueCadmium2);  
            IntensityAtenueCadmium2=   Math.exp(-CoeficientAttenuationPlaqueCadmium2* densityPlaqueAttenuationCadmium * epesseurPlaqueCadmium);
            //System.out.println("IntensityAtenuecadmium2      "+ IntensityAtenueCadmium2);
        	} else {IntensityAtenueCadmium1=1; IntensityAtenueCadmium2=1;}
            //--------------Condition en cas de source d'une seule energie
            double CasEnergieZeroMixt = Double.parseDouble(traveauPratique.lblNewLabel_7.getText());
            if (CasEnergieZeroMixt==0) {// En cas d une seule energie
            	efficacitIntrinsèque2=1;
            	     
            	IntensityAtenuePlomb2=1;
            	IntensityAtenueAluminium2=1;
            	IntensityAtenueCuivre2=1;
            	IntensityAtenueFer2=1;
            	IntensityAtenueBaryum2=1;
            	IntensityAtenueCadmium2=1;
            	//System.out.println("efficacitIntrinsèque2........     "+ efficacitIntrinsèque2); 
            }
            
            IntensityAteinentDetecteur = fraction*(efficacitIntrinsèque*
            		IntensityAtenuePlomb1*IntensityAtenueAluminium1*IntensityAtenueCuivre1
            		*IntensityAtenueFer1*IntensityAtenueBaryum1*IntensityAtenueCadmium1*intensity
            		
            		+efficacitIntrinsèque2*IntensityAtenuePlomb2*IntensityAtenueAluminium2*
            		IntensityAtenueCuivre2*IntensityAtenueFer2*IntensityAtenueBaryum2
            		*IntensityAtenueCadmium2*intensity2);  // Intensité initiale en photons/s   
           
            
            //System.out.println("intensity  :" +fraction );
        }
        
      //---------------------------------------------------------------------------
        
        int temps = (int) Double.parseDouble(traveauPratique.textField_6.getText());
        lambda = (double) (efficaciteFenetreDialog*(IntensityAteinentDetecteur));
        System.out.println("ityAteinentDet1111111111111     "+ (lambda+bruit)*20);
        //System.out.println("IntensityAteinentDetecteurIntensity11111111111111111     "+ efficaciteFenetreDialog*efficacitIntrinsèque);
        //System.out.println("IntensityAteinentDetecteurIntensity22222222222222222     "+ efficaciteFenetreDialog*efficacitIntrinsèque2);
        simulateBackgroundNoise(lambda, temps); // Simuler photons/s, T = 60 s
    }
    
    
    private void setupUI() {    	
        setLayout(new BorderLayout());
        // Initialiser le dataset pour le graphique
        series = new XYSeries("");
        dataset = new XYSeriesCollection(series);

        
        // Créer le graphique avec JFreeChart
        chart = ChartFactory.createXYLineChart(
                "",   // Titre du graphique
                "Time (s)",                    // Axe des abscisses
                "Number of counts",             // Axe des ordonnées
                dataset,                        // Données
                PlotOrientation.VERTICAL,       // Orientation du graphique
                false,                           // Légende activée
                true,                           // Outils activés
                false                           // URLs désactivées
        );

        // Récupérer le plot pour personnalisation
        XYPlot plot = chart.getXYPlot();

        // Personnaliser l'axe des abscisses (NumberAxis pour fixer la plage)
        NumberAxis domainAxis = new NumberAxis("Time (s)");
        domainAxis.setRange(0.0, (int) Double.parseDouble(traveauPratique.textField_6.getText())); // Fixer la plage de l'axe des abscisses entre 0 et 60 secondes
        plot.setDomainAxis(domainAxis);

        // Personnaliser l'axe des ordonnées
        plot.getRangeAxis().setLabel("Number of counts");

        // Personnaliser le fond du graphique en noir
        chart.setBackgroundPaint(Color.BLACK);
        plot.setBackgroundPaint(Color.BLACK);

        // Personnaliser les axes pour qu'ils soient en noir et blanc
        domainAxis.setAxisLinePaint(Color.WHITE);
        domainAxis.setLabelPaint(Color.WHITE);
        domainAxis.setTickLabelPaint(Color.WHITE);
        plot.getRangeAxis().setAxisLinePaint(Color.WHITE);
        plot.getRangeAxis().setLabelPaint(Color.WHITE);
        plot.getRangeAxis().setTickLabelPaint(Color.WHITE);

        // Personnaliser les lignes de grille pour qu'elles ne soient pas visibles
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        // Changer la couleur de la courbe (la série de données) en vert
        plot.getRenderer().setSeriesPaint(0, Color.GREEN);

        // Panneau du graphique
        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);





        // Panneau des informations en bas
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // JLabel pour le comptage cumulé
        cumulativeCountLabel = new JLabel("Cumulative count : 0 photons");
        cumulativeCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        traveauPratique.panel.add(cumulativeCountLabel);

        // JLabel pour le comptage total à la fin
        totalCountLabel = new JLabel("Total count: Pending...");
        totalCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        traveauPratique.panel.add(totalCountLabel);

        // JLabel pour afficher le temps écoulé
        timeElapsedLabel = new JLabel("Time elapsed : 0 s");
        timeElapsedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        traveauPratique.panel.add(timeElapsedLabel);

        //JButton quitButton = new JButton("Quitter");
       // quitButton.addActionListener(e -> System.exit(0));
        //infoPanel.add(quitButton);

        add(infoPanel, BorderLayout.SOUTH);
    }

    private void simulateBackgroundNoise(double lamda, int totalTime) {
    	
    	
        Random random = new Random();

        // Simuler le bruit de fond en temps réel
        timer = new Timer(1000, e -> {
            if (timeElapsed < totalTime && traveauPratique.execution.equals("run")) {
                // Générer un bruit suivant une distribution de Poisson
                //double cumulativeCount = generatePoisson(lamda, random);
                //System.out.println("noisenoisenoise :  "+noise);
                // Ajouter le bruit au comptage cumulé
                //cumulativeCount += lamda;
                //lamda1= cumulativeCount;
                
                //bruit1+=bruit;
            	LamdaBruit0=lamda+bruit;
            	
            	tempsMort=Double.parseDouble(GeigerMullerDialog.txtDeadTime.getText())*0.000001;                
                EffetTempsMort=(LamdaBruit0/(1+LamdaBruit0*tempsMort));
                //EffetTempsMort=LamdaBruit0;
                int samples=1;
                double tst = generatePoissonAveraged(EffetTempsMort, random, samples);
                totalCountTst +=tst;
                Incertitly=LamdaBruit;// POUR calculer incertitude
                
                
                
                //int AvecTempsMort=(int) (tst/(1+tst*tempsMort));
                
             // Mettre à jour la série de données
                series.add(timeElapsed+1, tst);


             // Mettre à jour le JLabel pour le comptage cumulé avec du HTML
               
               //double SansTempsMort=cumulativeCount;
                //cumInt1= (int) (totalCountTst/(1+totalCountTst*tempsMort));
                cumInt1= (int)totalCountTst;
                traveauPratique.lblNewLabel_19.setText("<html>Cumulative count : <span style='color:red;'><font size='6'>" + cumInt1 + "</font></span> photons</html>");

                //cumulativeCount=SansTempsMort;
                // Mettre à jour le JLabel pour le temps écoulé avec du HTML
                traveauPratique.lblNewLabel_18.setText("<html>Time elapsed : <span style='color:red;'><font size='6'>" + (timeElapsed + 1) + "</font></span> s</html>");
                timeElapsed++;
            } else {
                // Arrêter la simulation
                timer.stop();
                //cumulativeCount= (cumulativeCount/(1+cumulativeCount*tempsMort));
                //int cumInt2= (int)cumulativeCount;
                // Mettre à jour le JLabel pour le comptage total
                
                //int cumInt3 = (int) Math.sqrt(Incertitly);
                traveauPratique.lblNewLabel_20.setText("<html>Total count : <span style='color:red;'><font size='6'>" + cumInt1 + "</font></span> photons</html>");


                JOptionPane.showMessageDialog(this, "Simulation completed.\nTotal count : " + cumInt1 +" photons");
            }
        });

        timer.start();
    }

    private double generatePoissonAveraged(double lambda, Random random, int samples) {
        int sum = 0;
        for (int i = 0; i < samples; i++) {
            sum += generatePoisson(lambda, random);
        }
        return sum / samples; // Moyenne sur plusieurs échantillons
    }

    
    private int generatePoisson(double lambda, Random random) {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;

        do {
            k++;
            p *= random.nextDouble();
        } while (p > L);

        int poissonValue = k - 1; // Valeur obtenue

        // Ajustement pour se rapprocher de lambda
        if (poissonValue > lambda) {
            return (random.nextDouble() < (poissonValue - lambda)) ? poissonValue - 1 : poissonValue;
        } else if (poissonValue < lambda) {
            return (random.nextDouble() < (lambda - poissonValue)) ? poissonValue + 1 : poissonValue;
        }

        return poissonValue; // Si déjà proche, ne rien changer
    }

    
    //----------------------les facteurs efficacité intrinsèque---------------------------
 // Fonction pour calculer l'efficacité intrinsèque
    public static double calculerEfficacite(double V,  double Vseuil, double V1,double V2) {
        
        
        // Calcul de l'efficacité intrinsèque
        double eta =  countingRate(V, Vseuil, V1, V2);  // Remplacement par la fonction sigmoïde
               
        //System.out.printf("Facteur de tension : %.4f\n", countingRate(V, Vseuil, V1, V2));
   return eta;
    }
    
    
    public static double countingRate(double V, double Vseuil, double V1, double V2) {
    	
    	double NbrValueVsV1= 18; // En fichier de tension
    	double NbrValueV1V2= 48; // En fichier de tension(plateau)
    	double PasValueVsV1= (V1-Vseuil)/NbrValueVsV1;
    	double PasValueV1V2= (V2-V1)/NbrValueV1V2;
    	
       
        if (V < Vseuil) {
            return 0;
        } else if (V < V1) {
        	targetTension =V/PasValueVsV1;
        	FileTension.main(null);
            return FileTension.getclosestTension(); 
        } else {
        	FileTension.main(null);
        	targetTension =V/PasValueV1V2;
            return FileTension.getclosestTension();
        }
    }


    //------------------------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Vérifier que la bibliothèque JFreeChart est disponible
            try {
                BackgroundNoiseSimulatorWithTimeDisplay simulator = new BackgroundNoiseSimulatorWithTimeDisplay();
                simulator.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: JFreeChart library not found!");
            }
        });
    }
}
