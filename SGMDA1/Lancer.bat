java --module-path "libs\javafx-sdk-21.0.2\lib" ^
     --add-modules javafx.controls,javafx.fxml,javafx.swing ^
     --add-exports javafx.graphics/com.sun.javafx.embed=ALL-UNNAMED ^
     --add-exports javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED ^
     --add-exports javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED ^
     --add-exports javafx.base/com.sun.javafx.logging=ALL-UNNAMED ^
     -jar "SGMDA.jar"
