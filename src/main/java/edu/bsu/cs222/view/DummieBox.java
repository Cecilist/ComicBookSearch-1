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

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DummieBox extends VBox {
    private Stage primaryStage;

    public void comicBooks( Stage primary) {
        primaryStage = primary;
        showComics();
    }

    public void showComics() {
        VBox resultsBox = new VBox();
        SearchBox SearchHBox = new SearchBox();
        VBox HBoxSearchHBox = SearchHBox.createStage(primaryStage);
        resultsBox.getChildren().add(HBoxSearchHBox);

        DummieBox superDetails = new DummieBox();
        HBox superDetailsBox = superDetails.showBlankDetails();
        resultsBox.getChildren().add(superDetailsBox);

        ComicGrid comicPane = new ComicGrid();
        Label loadingLabel = new Label("And comic books will appear here");
        comicPane.add(loadingLabel, 0, 0, 5, 1);
        resultsBox.getChildren().add(comicPane);
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        PrimaryStage primaryStageEdit = new PrimaryStage();
        primaryStageEdit.primaryStageEdit(primaryStage, 600, 600, "comic books");
        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();
    }
    public HBox showBlankDetails() {
        VBox superDetailsVBox = new VBox();
        HBox superDetails = new HBox();
        ImageView emptyThumbnail = new ImageView(new Image("https://www.marvel.com/static/images/favicon/android-chrome-icon-194.png"));
        emptyThumbnail.setFitWidth(100);
        emptyThumbnail.setFitHeight(150);
        Label NoSearchLabel = new Label("Nothing search yet");
        TextArea emptyDescription = new TextArea("Search for something and a description will appear hear");
        emptyDescription.setMaxHeight(135);
        superDetailsVBox.getChildren().addAll(NoSearchLabel, emptyDescription);
        superDetails.getChildren().addAll(emptyThumbnail, superDetailsVBox);
        return superDetails;
    }
}
