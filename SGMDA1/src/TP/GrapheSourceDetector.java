package TP;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrapheSourceDetector extends JFXPanel {
	static JFXPanel jfxPanel = new JFXPanel();
	static Scene scene;
	PerspectiveCamera camera;

	static int getSceneWidth() {
	    return traveauPratique.panel_1.getWidth();
	}

	static int getSceneHeight() {
	    return traveauPratique.panel_1.getHeight();
	}

	// Méthode pour initialiser et rendre la scène redimensionnable
	static void initializeScene() {
	    Platform.runLater(() -> {
	        StackPane root = new StackPane();
	        scene = new Scene(root, getSceneWidth(), getSceneHeight());
	        jfxPanel.setScene(scene);

	        // Écouteur pour mettre à jour la taille de la scène lorsque panel_1 est redimensionné
	        traveauPratique.panel_1.addComponentListener(new java.awt.event.ComponentAdapter() {
	            @Override
	            public void componentResized(java.awt.event.ComponentEvent e) {
	                Platform.runLater(() -> {
	                    scene.getRoot().resize(getSceneWidth(), getSceneHeight());
	                });
	            }
	        });
	    });
	}

    
     Group root = new Group();
     List<Cylinder> rays = new ArrayList<>();
     Random random = new Random();

     void initScene(JFXPanel jfxPanel) {
        // Créer la caméra
    	 camera = new PerspectiveCamera(true);
    	 
    	// Ajustement de la position pour bien voir l'objet   	 
     		int zoom = traveauPratique.zoomplus;
     		camera.setTranslateZ(zoom); // Éloigner la caméra pour un meilleur champ de vision
         	camera.setTranslateY(0);  // Légèrement surélever la caméra
         	camera.setTranslateX(zoom);    

    	// Appliquer une rotation pour une meilleure perspective
    	camera.getTransforms().addAll(
    	    new Rotate(0, Rotate.X_AXIS), // Inclinaison vers le bas
    	    new Rotate(40, Rotate.Y_AXIS),   // Rotation légère pour un effet 3D
    	    new Rotate(0, Rotate.Z_AXIS)
    	);

    	// Ajustement des limites de vision
    	camera.setNearClip(1);
    	camera.setFarClip(1000);


        double DETECTOR_RADIUS = Double.parseDouble(traveauPratique.textField_1.getText());
        double DETECTOR_HEIGHT = Double.parseDouble(traveauPratique.textField_3.getText());
        
        // Créer la source radioactive
        double SOURCE_RADIUS = 0.75;
        createSource(SOURCE_RADIUS);
        // Ajouter une plaque pour atténuation
        createParallelepiped();  
        
        String selectedItem4 = (String) traveauPratique.comboBox_1.getSelectedItem();
        if ("Mixed".equals(selectedItem4)) {
        
        if (PlaqueMixt.checkBoxes != null && PlaqueMixt.checkBoxes[0] != null && PlaqueMixt.checkBoxes[0].isSelected()) {
        createParallelepipedPlomb();
        }
        if (PlaqueMixt.checkBoxes != null && PlaqueMixt.checkBoxes[1] != null && PlaqueMixt.checkBoxes[1].isSelected()) {
        createParallelepipedAluminium();
        }
        if (PlaqueMixt.checkBoxes != null && PlaqueMixt.checkBoxes[2] != null && PlaqueMixt.checkBoxes[2].isSelected()) {
        createParallelepipedCuivre();
        }
        if (PlaqueMixt.checkBoxes != null && PlaqueMixt.checkBoxes[3] != null && PlaqueMixt.checkBoxes[3].isSelected()) {
        createParallelepipedFer();
        }
        if (PlaqueMixt.checkBoxes != null && PlaqueMixt.checkBoxes[4] != null && PlaqueMixt.checkBoxes[4].isSelected()) {
        createParallelepipedBaryum();
        }
        if (PlaqueMixt.checkBoxes != null && PlaqueMixt.checkBoxes[5] != null && PlaqueMixt.checkBoxes[5].isSelected()) {
        createParallelepipedCadmuim();
        }
        }
        // Créer le détecteur Geiger-Müller
        double distance =  (Double.parseDouble(traveauPratique.textField_2.getText())+DETECTOR_HEIGHT/2)/100;   // 80 cm = 0.8 unité dans l'échelle de la scène
        createGeigerMullerDetector(distance);
        
        //-------------------------------efficacité geaometrique-----------
        //double R = Double.parseDouble(traveauPratique.textField_1.getText());  // Rayon du détecteur en cm
        double d = Double.parseDouble(traveauPratique.textField_2.getText());  // Distance entre la source et le détecteur en cm
        
        // Calcul de la surface du détecteur (A_detecteur)
        double surface_detecteur = Math.PI * Math.pow(DETECTOR_RADIUS, 2);

        // Calcul de la surface de la sphère (A_sphere)
        double surface_sphere = 4 * Math.PI * Math.pow(d, 2);
        double fraction;
        
        String selectedItem = "Clear";
        String selectedItem1 = "Mixed";
        selectedItem = (String) traveauPratique.comboBox.getSelectedItem();
        selectedItem1 = (String) traveauPratique.comboBox.getSelectedItem();
        int VideAair;
        if ("Clear".equals(selectedItem) && "Mixed".equals(selectedItem1)) {
        	VideAair=0;
        	
        } else  {
        	VideAair=1;
        	
        } 
        
        if (d < 1 * DETECTOR_RADIUS) {
            
            fraction=0.5;
            
        }else {fraction = surface_detecteur / surface_sphere; }
        
        int numberOfPoints = (int) (VideAair*Double.parseDouble(traveauPratique.textField_5.getText())*1000);
        int durationInSeconds = (int) Double.parseDouble(traveauPratique.textField_6.getText());
        createAnimatedBackgroundNoise(root, numberOfPoints, durationInSeconds);

        // Ajouter une ligne entre la source et le détecteur
        createDistanceLine(distance);

     // Paramètres configurables
        double sourceX = -35; // Position X de la source
        double sourceY = 0;    // Position Y de la source
        double sourceZ = 0;    // Position Z de la source
        int NbrPtSource = (int) (Double.parseDouble(traveauPratique.textField_4.getText())+Double.parseDouble(traveauPratique.textField_7.getText())); // Nombre total de points rouges
        int emissionDuration = (int) Double.parseDouble(traveauPratique.textField_6.getText()); // Durée totale d'émission en secondes
        double maxDistance = 150; // Distance maximale parcourue par les points avant disparition
        if (NbrPtSource>5) {NbrPtSource=5;}
        simulateRadiationEmission(root, sourceX, sourceY, sourceZ,
        		NbrPtSource, emissionDuration,  maxDistance);
        
        
        animateRays();

        // Configurer la scène
        Scene scene = new Scene(root, getSceneWidth(), getSceneHeight(), true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        // Rotation interactive
        Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
        root.getTransforms().addAll(rotateX, rotateY);

        scene.setOnMouseDragged(event -> {
            rotateX.setAngle(rotateX.getAngle() - event.getSceneY() * 0.005);
            rotateY.setAngle(rotateY.getAngle() + event.getSceneX() * 0.005);
        });

        jfxPanel.setScene(scene);
    }

     
    
    // Créer la source radioactive sous forme Clyinder
    private void createSource(double SOURCE_RADIUS) {
    	 //String selectedItemGeoS = (String) SourceInfoDialog.comboBoxgeomSour.getSelectedItem();
         
    	Sphere source = new Sphere(0.3);
        PhongMaterial material = new PhongMaterial(Color.YELLOW);
        source.setMaterial(material);       
        source.setTranslateX(-35);
        source.setTranslateY(0);
        source.setTranslateZ(0);
        root.getChildren().add(source);
    }
   
    // Ajouter un parallélépipède
    private void createParallelepiped() {
        double thickness = Double.parseDouble(traveauPratique.textField_8.getText())*0.1;
        double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
        double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
        double distanceFromSource = Double.parseDouble(traveauPratique.textField_9.getText())+thickness/2;

        Box parallelepiped = new Box(width, height, thickness);
        
        String selectedItem1 = (String) traveauPratique.comboBox_1.getSelectedItem();
        Color color = null;
        // Interagir en fonction de l'élément sélectionné
        if (!"Clear".equals(selectedItem1) && !"Mixed".equals(selectedItem1)) {
        	
        if ("Lead (Pb)".equals(selectedItem1)) {
        	color = Color.valueOf("#606060"); // Gris foncé proche de la couleur du plomb
        }else if ("Aluminium (Al)".equals(selectedItem1)) {
        	color = Color.valueOf("#B0B0B0"); // Gris métallique proche de l'aluminium
        }else if ("Copper (Cu)".equals(selectedItem1)) {
        	color = Color.valueOf("#B87333"); // Cuivre - une couleur bronze/orangée
        }else if ("Iron (Ir)".equals(selectedItem1)) {
        	color = Color.valueOf("#A9A9A9"); // Gris métallisé similaire à l'acier
        }else if ("Barium".equals(selectedItem1)) {
        	color = Color.valueOf("#8FBC8F"); // Vert pâle similaire à l'apparence du baryum
        }else if ("Cadmium (Cd)".equals(selectedItem1)) {
        	color = Color.valueOf("#B0C4DE"); // Gris clair avec une touche métallisée, proche du cadmium
        }
        
        
        PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
        parallelepiped.setMaterial(material);

        parallelepiped.setTranslateX(-35 + distanceFromSource);
        parallelepiped.setTranslateY(0);
        parallelepiped.setTranslateZ(0);
        parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
        parallelepiped.setRotate(90);

        root.getChildren().add(parallelepiped);
        }
    }
          
              //----------------Mix Plaque-----------------------
    
    
    
    private void createParallelepipedPlomb() {
    	
    	
        double thickness = Double.parseDouble(PlaqueMixt.thicknessFields[0].getText())*0.1;//
        double distanceFromSourcePalaqueMixt = Double.parseDouble(PlaqueMixt.DistanceFields[0].getText())+thickness/2;//
    	
        double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
        double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
        

        Box parallelepiped = new Box(width, height, thickness);       
        Color color = null;       
        color = Color.valueOf("#606060"); // Gris foncé proche de la couleur du plomb
        PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
        parallelepiped.setMaterial(material);

        parallelepiped.setTranslateX(-35 + distanceFromSourcePalaqueMixt);
        parallelepiped.setTranslateY(0);
        parallelepiped.setTranslateZ(0);
        parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
        parallelepiped.setRotate(90);

        root.getChildren().add(parallelepiped);
        }
    
    
    private void createParallelepipedAluminium() {
    	
    	
        double thickness = Double.parseDouble(PlaqueMixt.thicknessFields[1].getText())*0.1;//
        double distanceFromSourcePalaqueMixt = Double.parseDouble(PlaqueMixt.DistanceFields[1].getText())+thickness/2;//
        double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
        double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
        

        Box parallelepiped = new Box(width, height, thickness);       
        Color color = null;       
        color = Color.valueOf("#B0B0B0"); // Gris métallique proche de l'aluminium
        PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
        parallelepiped.setMaterial(material);

        parallelepiped.setTranslateX(-35 + distanceFromSourcePalaqueMixt);
        parallelepiped.setTranslateY(0);
        parallelepiped.setTranslateZ(0);
        parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
        parallelepiped.setRotate(90);

        root.getChildren().add(parallelepiped);
        }
    
      private void createParallelepipedCuivre() {
    	
    	
        double thickness = Double.parseDouble(PlaqueMixt.thicknessFields[2].getText())*0.1;//
        double distanceFromSourcePalaqueMixt = Double.parseDouble(PlaqueMixt.DistanceFields[2].getText())+thickness/2	;//
        double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
        double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
        

        Box parallelepiped = new Box(width, height, thickness);       
        Color color = null;       
        color = Color.valueOf("#B87333"); // Cuivre - une couleur bronze/orangée
        PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
        parallelepiped.setMaterial(material);

        parallelepiped.setTranslateX(-35 + distanceFromSourcePalaqueMixt);
        parallelepiped.setTranslateY(0);
        parallelepiped.setTranslateZ(0);
        parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
        parallelepiped.setRotate(90);

        root.getChildren().add(parallelepiped);
        }
      
      private void createParallelepipedFer() {
      	
      	
          double thickness = Double.parseDouble(PlaqueMixt.thicknessFields[3].getText())*0.1;//
          double distanceFromSourcePalaqueMixt = Double.parseDouble(PlaqueMixt.DistanceFields[3].getText())+thickness /2;//
          double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
          double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
          

          Box parallelepiped = new Box(width, height, thickness);       
          Color color = null;       
          color = Color.valueOf("#A9A9A9"); // Gris métallisé similaire à l'acier
          PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
          parallelepiped.setMaterial(material);

          parallelepiped.setTranslateX(-35 + distanceFromSourcePalaqueMixt);
          parallelepiped.setTranslateY(0);
          parallelepiped.setTranslateZ(0);
          parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
          parallelepiped.setRotate(90);

          root.getChildren().add(parallelepiped);
          }
      
      private void createParallelepipedBaryum() {
        	
        	
            double thickness = Double.parseDouble(PlaqueMixt.thicknessFields[4].getText())*0.1;//
            double distanceFromSourcePalaqueMixt = Double.parseDouble(PlaqueMixt.DistanceFields[4].getText())+thickness/2;//
            double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
            double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
            

            Box parallelepiped = new Box(width, height, thickness);       
            Color color = null;       
            color = Color.valueOf("#8FBC8F"); // Vert pâle similaire à l'apparence du baryum
            PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
            parallelepiped.setMaterial(material);

            parallelepiped.setTranslateX(-35 + distanceFromSourcePalaqueMixt);
            parallelepiped.setTranslateY(0);
            parallelepiped.setTranslateZ(0);
            parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
            parallelepiped.setRotate(90);

            root.getChildren().add(parallelepiped);
            }
      
    
    
      private void createParallelepipedCadmuim() {
      	
      	
          double thickness = Double.parseDouble(PlaqueMixt.thicknessFields[5].getText())*0.1;//
          double distanceFromSourcePalaqueMixt = Double.parseDouble(PlaqueMixt.DistanceFields[5].getText())+thickness/2;//
          double height = Double.parseDouble(traveauPratique.textField_1.getText())*6;
          double width = Double.parseDouble(traveauPratique.textField_1.getText())*5;
          

          Box parallelepiped = new Box(width, height, thickness);       
          Color color = null;       
          color = Color.valueOf("#B0C4DE"); // Gris clair avec une touche métallisée, proche du cadmium
          PhongMaterial material = new PhongMaterial(color); // Appliquer la couleur au matériau
          parallelepiped.setMaterial(material);

          parallelepiped.setTranslateX(-35 + distanceFromSourcePalaqueMixt);
          parallelepiped.setTranslateY(0);
          parallelepiped.setTranslateZ(0);
          parallelepiped.setRotationAxis(new javafx.geometry.Point3D(0, 1, 0));
          parallelepiped.setRotate(90);

          root.getChildren().add(parallelepiped);
          }
    
                             //-----------------------------------------------------------
 
 // Créer le détecteur Geiger-Müller avec un fil au centre
    private void createGeigerMullerDetector(double distance) {
        // Définir les dimensions du détecteur
        double DETECTOR_RADIUS = Double.parseDouble(traveauPratique.textField_1.getText());
        double DETECTOR_HEIGHT = Double.parseDouble(traveauPratique.textField_3.getText());

        PhongMaterial outerMaterial = new PhongMaterial(Color.GRAY);
        PhongMaterial innerMaterial = new PhongMaterial(Color.RED);
        PhongMaterial micaMaterial = new PhongMaterial(Color.BLACK);
        
        // // Cylindre externe creux
        Cylinder detectorOuter = new Cylinder(DETECTOR_RADIUS+ Double.parseDouble(GeigerMullerDialog.txtWallThickness.getText())*0.1, DETECTOR_HEIGHT);
        detectorOuter.setMaterial(outerMaterial);
        detectorOuter.setDrawMode(DrawMode.FILL);
        
     // Positionner les cylindres
        detectorOuter.setTranslateX(distance * 100 - 35);
        detectorOuter.setTranslateY(0);
        detectorOuter.setTranslateZ(0);
        detectorOuter.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
        detectorOuter.setRotate(90);
       
         
     // Cylindre interne (vide)
        double WIRE_RADIUS = DETECTOR_RADIUS ;
        Cylinder detectorInner = new Cylinder(WIRE_RADIUS, DETECTOR_HEIGHT); // Rayon intérieur 3cm (30mm)
        detectorInner.setMaterial(new PhongMaterial(Color.YELLOW)); // Simule le vide
        detectorInner.setDrawMode(DrawMode.LINE);
        
        detectorInner.setTranslateX(distance * 100 - 35);
        detectorInner.setTranslateY(0);
        detectorInner.setTranslateZ(0);
        detectorInner.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
        detectorInner.setRotate(90);
        
     // Cylindre central plein              
        Cylinder centralRod = new Cylinder(1, DETECTOR_HEIGHT); // Rayon 2cm (20mm), longueur 40cm
        centralRod.setMaterial(innerMaterial);
        centralRod.setDrawMode(DrawMode.FILL);
        
        centralRod.setTranslateX(distance * 100 - 35);
        centralRod.setTranslateY(0);
        centralRod.setTranslateZ(0);
        centralRod.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
        centralRod.setRotate(90);
        
     // Disque de mica (entrée du détecteur)
        double EpesseuMica=0.1;
        Cylinder micaDisc = new Cylinder(WIRE_RADIUS, EpesseuMica); // Rayon 5cm (50mm), épaisseur 1cm (10mm)
        micaDisc.setMaterial(micaMaterial);
        micaDisc.setTranslateX(distance * 100-DETECTOR_HEIGHT/2 - 35);
        micaDisc.setTranslateY(0);
        micaDisc.setTranslateZ(0);
        micaDisc.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
        micaDisc.setRotate(90);
        String selectedItemeTube = (String) GeigerMullerDialog.comboBoxWindowMaterial.getSelectedItem();
        
        if("Mica".equals(selectedItemeTube)) {micaDisc.setMaterial(new PhongMaterial(Color.BLACK));}else {micaDisc.setMaterial(new PhongMaterial(Color.rgb(200, 255, 255, 0.2)));}
        
        
        // Ajouter le fil central au groupe racine
        
        
        root.getChildren().add(detectorOuter);
        root.getChildren().add(detectorInner);
        root.getChildren().add(centralRod);
        root.getChildren().add(micaDisc);
        
    }



    // Créer une ligne entre la source et le détecteur
    private void createDistanceLine(double distance) {
        double startX = -35;
        double endX = distance * 100 - 35 ;

        Cylinder line = new Cylinder(0.03, Math.abs(endX - startX));
        line.setMaterial(new PhongMaterial(Color.WHITE));
        line.setTranslateX((startX + endX) / 2);
        line.setTranslateY(0);
        line.setTranslateZ(0);
        line.setRotationAxis(new javafx.geometry.Point3D(0, 0, 1));
        line.setRotate(90);

        root.getChildren().add(line);
    }

    // Ajouter du bruit de fond
 // Ajouter du bruit de fond
    private void createAnimatedBackgroundNoise(Group root, int numberOfPoints, int durationInSeconds) {
        // Vérifier la condition avant d'exécuter la méthode
        if (!"run".equals(traveauPratique.execution)) {
            //System.out.println("L'animation du bruit de fond est arrêtée immédiatement car execution != 'run'");
            return;
        }

        Random random = new Random();
        List<Timeline> timelines = new ArrayList<>(); // Stocker les timelines pour les arrêter plus tard
        List<Sphere> points = new ArrayList<>(); // Stocker les points pour les supprimer plus tard

        if (numberOfPoints > 1000) {
            numberOfPoints = 1000;
        }

        for (int i = 0; i < numberOfPoints; i++) {
            Sphere point = new Sphere(0.1);
            PhongMaterial material = new PhongMaterial(Color.LIME);
            point.setMaterial(material);

            double startX = random.nextDouble() * 400 - 200;
            double startY = random.nextDouble() * 400 - 200;
            double startZ = random.nextDouble() * 400 - 200;

            point.setTranslateX(startX);
            point.setTranslateY(startY);
            point.setTranslateZ(startZ);

            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                    new KeyValue(point.translateXProperty(), startX),
                    new KeyValue(point.translateYProperty(), startY),
                    new KeyValue(point.translateZProperty(), startZ)
                ),
                new KeyFrame(Duration.seconds(2 + random.nextDouble() * 3),
                    new KeyValue(point.translateXProperty(), random.nextDouble() * 400 - 200),
                    new KeyValue(point.translateYProperty(), random.nextDouble() * 400 - 200),
                    new KeyValue(point.translateZProperty(), random.nextDouble() * 400 - 200)
                )
            );

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(true);
            timeline.play();

            // Ajouter la timeline et le point aux listes
            timelines.add(timeline);
            points.add(point);

            root.getChildren().add(point);
        }

        // Vérification périodique de l'état de execution
        Timeline checkExecution = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (!"run".equals(traveauPratique.execution)) {
                //System.out.println("Arrêt immédiat des points car execution != 'run'");
                stopAnimationAndRemovePoints(root, timelines, points);
            }
        }));
        checkExecution.setCycleCount(Timeline.INDEFINITE);
        checkExecution.play();

        // Planifier l'arrêt des animations et la suppression des points après 17 secondes
        Timeline stopAndRemove = new Timeline(new KeyFrame(Duration.seconds(durationInSeconds), e -> {
            //System.out.println("Arrêt après 17 secondes.");
            stopAnimationAndRemovePoints(root, timelines, points);
        }));
        stopAndRemove.setCycleCount(1);
        stopAndRemove.play();
    }

    // Méthode pour arrêter toutes les animations et supprimer les points
    private void stopAnimationAndRemovePoints(Group root, List<Timeline> timelines, List<Sphere> points) {
        for (Timeline t : timelines) {
            t.stop();
        }
        
        root.getChildren().removeAll(points);
        //System.out.println("Tous les points ont été supprimés.");
    }


    // Animer les rayons gamma
    private void animateRays() {
    	
    	List<Cylinder> rays = new ArrayList<>();  // Liste pour stocker les rayons
        long startTime = System.currentTimeMillis();  // Temps de départ
        int maxDuration = (int) Double.parseDouble(traveauPratique.textField_6.getText())*1000;
        new Thread(() -> {
            while (true) {
                long elapsedTime = System.currentTimeMillis() - startTime;  // Calculer le temps écoulé

                // Si plus de 10 secondes se sont écoulées, arrêter l'animation
                if (elapsedTime >= maxDuration || !traveauPratique.execution.equals("run")) {
                    break;  // Sortir de la boucle et arrêter l'animation
                }

                // Ajouter un nouveau rayon
                Cylinder ray = createRay();
                rays.add(ray);

                // Ajouter le rayon au groupe
                javafx.application.Platform.runLater(() -> root.getChildren().add(ray));

                // Retirer les rayons anciens après un certain temps (par exemple, après 1 seconde)
                if (rays.size() > 10000/1000) {
                    Cylinder oldRay = rays.remove(0);
                    javafx.application.Platform.runLater(() -> root.getChildren().remove(oldRay));
                }

                // Pause pour simuler une animation fluide (par exemple, 100 millisecondes entre chaque rayon)
                try {
                    Thread.sleep(1);  // Temps de pause entre chaque ajout de rayon
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Une fois la boucle terminée, retirer tous les rayons restants
            javafx.application.Platform.runLater(() -> {
                for (Cylinder ray : rays) {
                    root.getChildren().remove(ray);
                }
                rays.clear();  // Vider la liste des rayons
            });

        }).start();
    }

    
 // Ajouter des points de source rouge en temps réel avec suppression après x secondes
    private void simulateRadiationEmission(Group root, double sourceX, double sourceY, double sourceZ,
            int NbrPtSource, int emissionDuration, double maxDistance) {
    	    
    	
        Random random = new Random();

        // Temps de départ
        long startTime = System.currentTimeMillis();

        // AnimationTimer pour l'émission en temps réel
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                // Temps écoulé en secondes
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

                // Arrêter l'émission après la durée spécifiée
                if (elapsedTime > emissionDuration || !traveauPratique.execution.equals("run")) {
                    stop();
                    return;
                }

                // Ajouter un nouveau point rouge
                
                for (int i = 0; i < NbrPtSource ; i++) {
                	
                    Sphere point = new Sphere(0.1); // Rayon du point (plus visible)
                    PhongMaterial material = new PhongMaterial(Color.RED); // Couleur rouge
                    point.setMaterial(material);

                    // Positionner initialement le point à la position de la source
                    point.setTranslateX(sourceX);
                    point.setTranslateY(sourceY);
                    point.setTranslateZ(sourceZ);

                    // Générer des déplacements aléatoires dans toutes les directions
                    double targetX = sourceX + (random.nextDouble() * maxDistance - maxDistance / 2);
                    double targetY = sourceY + (random.nextDouble() * maxDistance - maxDistance / 2);
                    double targetZ = sourceZ + (random.nextDouble() * maxDistance - maxDistance / 2);

                    // Animation : déplacer le point vers sa position cible
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(point.translateXProperty(), sourceX),
                                    new KeyValue(point.translateYProperty(), sourceY),
                                    new KeyValue(point.translateZProperty(), sourceZ)
                            ),
                            new KeyFrame(Duration.seconds(2 + random.nextDouble() * 2), // Durée aléatoire
                                    new KeyValue(point.translateXProperty(), targetX),
                                    new KeyValue(point.translateYProperty(), targetY),
                                    new KeyValue(point.translateZProperty(), targetZ)
                            )
                    );

                    // Supprimer le point après l'animation
                    timeline.setOnFinished(e -> root.getChildren().remove(point));
                    timeline.play();

                    // Ajouter le point au graphe
                    root.getChildren().add(point);
                }
            }
        }.start();
    }

    // Créer un rayon gamma aléatoire en 3D (dans toutes les directions sphériques)
    private Cylinder createRay() {
    	
        // Calculer les angles aléatoires pour les deux axes sphériques
        double theta = random.nextDouble() * Math.PI;  // Angle polaire (entre 0 et PI)
        double phi = random.nextDouble() * 2 * Math.PI;  // Angle azimutal (entre 0 et 2PI)
        double RAY_LENGTH = random.nextDouble() * 20;

        // Calculer les coordonnées X, Y, Z du rayon en utilisant les coordonnées sphériques
        double endX = RAY_LENGTH * Math.sin(theta) * Math.cos(phi);
        double endY = RAY_LENGTH * Math.sin(theta) * Math.sin(phi);
        double endZ = RAY_LENGTH * Math.cos(theta);

        // Créer un cylindre pour représenter le rayon gamma
        Cylinder ray = new Cylinder(0.1, RAY_LENGTH);
        ray.setTranslateX(-35);
        ray.setTranslateY(0);
        ray.setTranslateZ(0);

        // Positionner le rayon en 3D
        ray.setRotationAxis(new javafx.geometry.Point3D(endX, endY, endZ));
        ray.setRotate(90);  // Orienter le rayon le long de l'axe défini par (endX, endY, endZ)

        ray.setMaterial(new PhongMaterial(Color.RED));
        return ray;
    }
    
    
    
    
    
    


}
