package org.example.checkstars;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

// CST-283
// Aaron Pelto
// Winter 2024

// The astronomer who hired you for this solution has changed the algorithm to be used for star detection.
// Instead of just the star, and its four "neighbors", change to include the start and all eight neighbors.

// The algorithm does not require averaging all the positions plus eight neighboring points.
// If the average exceeds the value of 5.3, you will now declare a star at that location.


public class CheckStars extends Application
{
    // Class wide component declarations
    private TextField fileNameField;
    private Label fileNameLabel;
    private Label starOutputLabel;
    private TextArea starDisplayArea;
    private Button goButton;

    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Initialize label objects
        fileNameLabel = new Label("Data Filename");
        starOutputLabel = new Label("Star Field");

        // Initialize text field objects
        fileNameField = new TextField();
        fileNameField.setText("inStar.txt");

        // Set up text area
        starDisplayArea = new TextArea();
        starDisplayArea.setFont(Font.font("Monospaced", FontWeight.BOLD, 25));

        // Initialize button and its label.  Register handler for button.
        goButton = new Button("Determine Star Field");
        goButton.setOnAction(new ButtonClickHandler());

        // Add components to vertical containers
        VBox fileInputBox   = new VBox(5,fileNameLabel,fileNameField);
        fileInputBox.setAlignment(Pos.CENTER);
        VBox starDisplayBox = new VBox(5,starOutputLabel,starDisplayArea);
        starDisplayBox.setAlignment(Pos.CENTER);
        VBox mainBox = new VBox(20,fileInputBox,starDisplayBox,goButton);
        mainBox.setAlignment(Pos.CENTER);

        // Set up overall scene
        Scene scene = new Scene(mainBox, 270, 380);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Star Field Analyzer");
        primaryStage.show();
    }
    // Private inner class for event handling.
    // At "Convert" button click, collect input and validate.
    class ButtonClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            StarField theStars;

            String inputFile = fileNameField.getText();
            theStars = new StarField(inputFile);
            theStars.ScanForStars();

            starDisplayArea.setText(theStars.toString());
        }
    }
}
