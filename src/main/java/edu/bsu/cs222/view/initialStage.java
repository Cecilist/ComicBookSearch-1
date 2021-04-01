//  <Program to search for comics and creators that Marvel has available information on.>
//  Copyright (C) <2021>  <Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish>

//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see <https://www.gnu.org/licenses/>.

package edu.bsu.cs222.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class initialStage extends VBox {
    public void createStage(Stage primaryStage) {
        primaryStage primarystage = new primaryStage();
        primarystage.primaryStageEdit(primaryStage,275,400,"Search Selector");
        VBox searchBox = guiStage();
        Label titleLabel = createTitleLabel();
        Button superheroSearchButton = createSuperheroSearchButton( primaryStage);
        Button creatorSearchButton = createCreatorSearchButton( primaryStage);
        superheroSearchButton.setDefaultButton(true);
        searchBox.getChildren().addAll(titleLabel, superheroSearchButton,creatorSearchButton);
        primaryStage.setScene(new Scene(searchBox));
        primaryStage.show();
    }

    private VBox guiStage() {
        VBox initialBox = new VBox();
        initialBox.setAlignment(Pos.CENTER);
        initialBox.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        initialBox.setSpacing(20);
        return initialBox;
    }

    private Label createTitleLabel() {
        Label titleLabel = new Label("Search Selector");
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 30));
        titleLabel.setPadding(new Insets(10, 10, 10, 10));
        return titleLabel;
    }
    private Button createSuperheroSearchButton(Stage primaryStage) {
        Button superheroSearchButton = new Button("Superhero Search");
        superheroSearchButton.setOnAction(event -> {
            SearchBox createStage = new SearchBox();
            createStage.createStage( primaryStage,"characters",true);
        });
        return superheroSearchButton;
    }
    private Button createCreatorSearchButton(Stage primaryStage) {
        Button superheroSearchButton = new Button("Creator Search");
        superheroSearchButton.setOnAction(event -> {
            SearchBox createStage = new SearchBox();
            createStage.createStage( primaryStage,"creators",false);
        });
        return superheroSearchButton;
    }
}
