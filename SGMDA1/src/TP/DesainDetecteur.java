package TP;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class DesainDetecteur extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Matériau pour le cylindre externe (creux)
        PhongMaterial outerMaterial = new PhongMaterial(Color.GREEN);
        PhongMaterial innerMaterial = new PhongMaterial(Color.RED);
        PhongMaterial micaMaterial = new PhongMaterial(Color.GRAY);
        
        // Cylindre externe creux
        Cylinder outerCylinder = new Cylinder(50, 400); // Rayon 5cm (50mm), longueur 40cm (400mm)
        outerCylinder.setMaterial(outerMaterial);
        outerCylinder.setDrawMode(DrawMode.FILL);
        
        // Cylindre interne (vide)
        Cylinder innerCylinder = new Cylinder(30, 400); // Rayon intérieur 3cm (30mm)
        innerCylinder.setMaterial(new PhongMaterial(Color.BLACK)); // Simule le vide
        innerCylinder.setDrawMode(DrawMode.LINE);
        
        // Cylindre central plein
        Cylinder centralRod = new Cylinder(20, 400); // Rayon 2cm (20mm), longueur 40cm
        centralRod.setMaterial(innerMaterial);
        centralRod.setDrawMode(DrawMode.FILL);
        
        // Disque de mica (entrée du détecteur)
        Cylinder micaDisc = new Cylinder(50, 10); // Rayon 5cm (50mm), épaisseur 1cm (10mm)
        micaDisc.setMaterial(micaMaterial);
        micaDisc.setTranslateZ(-200); // Positionné à l'entrée du détecteur
        
        // Création de la scène 3D
        Group root = new Group(outerCylinder, innerCylinder, centralRod, micaDisc);
        Scene scene = new Scene(root, 600, 400, true);
        
        // Caméra en perspective
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-500);
        scene.setCamera(camera);
        
        primaryStage.setTitle("Cylindre Geiger-Müller");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
