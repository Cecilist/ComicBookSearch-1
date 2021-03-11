package edu.bsu.cs222;

import javafx.application.Application;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SearchStage searchView= new SearchStage();
        searchView.createStage();
        primaryStage=searchView;
        primaryStage.show();
    }



}