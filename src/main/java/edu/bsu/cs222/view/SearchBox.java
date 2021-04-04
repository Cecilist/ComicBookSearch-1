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
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class SearchBox extends VBox {
    public void createStage(Stage primaryStage, String searchTerm) {
        primaryStage primarystage = new primaryStage();
        primarystage.primaryStageEdit(primaryStage, 300, 400, " Search");
        VBox searchBox = createSearchBox();
        Label titleLabel = createTitleLabel(searchTerm);
        Label SearchLabel = createSearchLabel(searchTerm);
        searchTerm = searchTerm + "s";
        TextField searchBar = createSearchBar();
        Button searchButton = createSearchButton(searchTerm, searchBar, primaryStage);
        searchButton.setDefaultButton(true);
        Button backButton = backButton(primaryStage);
        searchBox.getChildren().addAll(titleLabel, SearchLabel, searchBar, searchButton, backButton);
        primaryStage.setScene(new Scene(searchBox));
        primaryStage.show();
    }

    private VBox createSearchBox() {
        VBox searchBox = new VBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        searchBox.setSpacing(20);
        return searchBox;
    }

    private Label createTitleLabel(String searchTerm) {
        Label titleLabel = new Label(searchTerm + " Search");
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 30));
        titleLabel.setPadding(new Insets(10, 10, 10, 10));
        return titleLabel;
    }

    private Label createSearchLabel(String searchTerm) {
        Label titleLabel = new Label("Enter the name of a Marvel " + searchTerm);
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 12));
        titleLabel.setPadding(new Insets(10, 10, -10, 10));
        return titleLabel;
    }


    private TextField createSearchBar() {
        TextField searchBar = new TextField("");
        searchBar.setMaxWidth(300);
        searchBar.setOnMouseClicked(event -> searchBar.clear());
        return searchBar;
    }

    private Button createSearchButton(String searchTerm, TextField articleName, Stage primaryStage) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            SearchSelectionBox heroView = new SearchSelectionBox();
            heroView.pickSearchOption(searchTerm, articleName.getText(), primaryStage);
        });
        return searchButton;
    }

    private Button backButton(Stage primaryStage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            initialStage newInitialStage = new initialStage();
            newInitialStage.createStage( primaryStage);
        });
        return backButton;
    }
}
