package edu.bsu.cs222.view;

import javafx.stage.Stage;

public class primaryStage {
    public void primaryStageEdit(Stage primaryStage, int height, int width, String title) {
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        primaryStage.setTitle(title);
        primaryStage.centerOnScreen();
    }
}
