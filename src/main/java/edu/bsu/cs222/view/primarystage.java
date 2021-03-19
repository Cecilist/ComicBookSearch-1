package edu.bsu.cs222.view;

import javafx.stage.Stage;

public class primarystage {
    public void primaryStageEdit(Stage primaryStage, int hight, int width, String title) {
        primaryStage.setHeight(hight);
        primaryStage.setWidth(width);
        primaryStage.setTitle(title);
        primaryStage.centerOnScreen();
    }
}
