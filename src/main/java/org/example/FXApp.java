package org.example;


import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXApp extends Application {

    String content = System.getProperty("airesponse", "Default Message");;

    int width = 400;
    int height = 200;

    public static void launchApp(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        // Text erstellen
        Label label = new Label(content);
        label.setStyle("-fx-text-fill: DARKGRAY"); // Textfarbe
        label.setWrapText(true);
        label.setMaxWidth(400);  // maximale Breite definieren

        Button button = new Button("X");

        button.setOnAction(event -> {System.exit(0);});
        button.setStyle("-fx-background-color: rgba(0,0,0,0.76);");

        // scroll pane
        // ScrollPane bauen
        ScrollPane scroll = new ScrollPane(label);

// Scrollbars nur anzeigen, wenn nötig
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

// Optik etwas anpassen (optional)
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");



        // Layout erstellen und Text hinzufügen
        StackPane root = new StackPane(scroll);
        root.setStyle("-fx-background-color: rgba(0,0,0,0.88);"); // Hintergrund schwarz
        root.getChildren().add(button);

        StackPane.setAlignment(button, javafx.geometry.Pos.TOP_RIGHT);





        // Szene erstellen
        Scene scene = new Scene(root, width, height);

        scene.getStylesheets().add(
                "data:text/css," +
                        ".scroll-bar:vertical .track { -fx-background-color: #000000; }" +
                        ".scroll-bar:vertical .thumb { -fx-background-color: #333333; }" +
                        ".scroll-bar:horizontal .track { -fx-background-color: #000000; }" +
                        ".scroll-bar:horizontal .thumb { -fx-background-color: #333333; }"
        );


        stage.initStyle(StageStyle.UNDECORATED);

        // Fenster konfigurieren
        stage.setTitle("Window");
        stage.setScene(scene);

        stage.setOnShown(e -> {
            Rectangle2D screen = Screen.getPrimary().getVisualBounds();

            double x = screen.getMaxX() - stage.getWidth() - 20; // 20px Abstand vom Rand
            double y = screen.getMaxY() - stage.getHeight() - 20;

            stage.setX(x);
            stage.setY(y);
        });

        stage.show();
    }







}
