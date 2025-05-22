/**
 * 
 */
/**
 * 
 */
module SGMDA1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;
    requires org.jfree.jfreechart;    
    requires jcommon;
    requires java.prefs;
	
	
	

    opens TP to javafx.fxml;  // Modifier ici, car tu ouvres maintenant le package TP
    exports TP;  // Modifier ici aussi, pour exporter le package TP
}
