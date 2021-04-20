/*
 *  Program to search for comics and creators that Marvel has available information on.
 *  Copyright (C) 2021  Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see https://www.gnu.org/licenses/.
 */

package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Creator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.net.MalformedURLException;
import java.net.URL;

public class GraphicalUserInterface extends Application {
    private final ComicBox comicBox = new ComicBox();
    private final SearchSelectionBox selectionBox = new SearchSelectionBox(comicBox);
    private final SearchBox searchBox = new SearchBox(selectionBox);
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public void createStage() {
        stageSetup();
        VBox resultsBox = createResultsBox();
        searchBox.createStage();
        selectionBox.styleBox();
        showBlankDetails();
        comicBox.replaceCreatorDescription("Description will appear after search!");
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        scrollPane.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();
    }

    private VBox createResultsBox() {
        TitledPane selectionPane = new TitledPane("Select Option", selectionBox);
        Label title = createTitleLabel();
        VBox resultsBox = new VBox();
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setBackground(new Background(new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        resultsBox.getChildren().addAll(title, searchBox, selectionPane, comicBox);
        return resultsBox;
    }

    public void showBlankDetails() {
        Creator dummyCreator = new Creator();
        try {
            dummyCreator.setThumbnailURL(new URL("https://www.marvel.com/static/images/favicon/android-chrome-icon-194.png"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        comicBox.setMarvelObject(dummyCreator);
        comicBox.setSearchCategory("CREATOR");
        comicBox.showComics(null);
    }

    private void stageSetup() {
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setTitle("Comic Book Search");
        primaryStage.centerOnScreen();
    }

    private Label createTitleLabel() {
        Label titleLabel = new Label("Comic Book Search");
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 30));
        titleLabel.setPadding(new Insets(10, 10, 10, 10));
        return titleLabel;
    }

    @Override
    public void start(Stage primaryStage) {
        BasicConfigurator.configure();
        this.primaryStage = primaryStage;
        createStage();
    }


}