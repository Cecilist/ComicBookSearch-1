package edu.bsu.cs222.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class GraphicalUserInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BasicConfigurator.configure();
        SearchStage searchView= new SearchStage();
        searchView.createStage();
        primaryStage=searchView;
        primaryStage.show();
    }



}