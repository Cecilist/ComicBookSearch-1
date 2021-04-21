
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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class SearchBox extends VBox {
    private final SearchSelectionBox selectionBox;
    private ToggleGroup searchTypeGroup;

    public SearchBox(SearchSelectionBox selectionBox) {
        this.selectionBox = selectionBox;
    }

    public void createStage() {
        createSearchBox();
        Label searchLabel = createSearchLabel();
        HBox searchTypeSelector = creatorSearchCategorySelector();
        TextField searchBar = createSearchBar();
        Button searchButton = createSearchButton(searchBar);
        HBox creatorSearchHBox = new HBox(searchLabel, searchBar, searchButton);
        searchButton.setDefaultButton(true);
        getChildren().addAll(searchTypeSelector, creatorSearchHBox);
    }

    private void createSearchBox() {
        setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        setSpacing(10);
    }


    private Label createSearchLabel() {
        Label searchLabel = new Label("Enter the name:         ");
        searchLabel.setTextFill(Color.web("#ffffffff"));
        searchLabel.setFont(new Font("Fantasy", 12));
        searchLabel.setPadding(new Insets(10, 10, 10, 10));
        return searchLabel;
    }

    private Label createTermLabel() {
        Label searchLabel = new Label("Select a search Item:");
        searchLabel.setTextFill(Color.web("#ffffffff"));
        searchLabel.setFont(new Font("Fantasy", 12));
        searchLabel.setPadding(new Insets(10, 10, -10, 10));
        return searchLabel;
    }


    private TextField createSearchBar() {
        TextField searchBar = new TextField("");
        searchBar.setMaxWidth(300);
        searchBar.setOnMouseClicked(event -> searchBar.clear());
        return searchBar;
    }

    private HBox creatorSearchCategorySelector() {
        searchTypeGroup = new ToggleGroup();
        RadioButton characterButton = new RadioButton("Character");
        RadioButton creatorButton = new RadioButton("Creator");
        createRadioButton(characterButton);
        characterButton.setSelected(true);
        characterButton.setUserData("CHARACTERS");
        creatorButton.setUserData("CREATORS");
        createRadioButton(creatorButton);
        Label termLabel = createTermLabel();
        HBox creatorSearchTypeBox = new HBox(termLabel, characterButton, creatorButton);
        creatorSearchTypeBox.setSpacing(5);
        return creatorSearchTypeBox;
    }

    private Button createSearchButton(TextField searchTerm) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String searchCategory = getSearchTerm();
            selectionBox.setAlignment(Pos.CENTER_LEFT);
            selectionBox.pickSearchOption(searchCategory, searchTerm.getText());
        });
        return searchButton;
    }

    private void createRadioButton(RadioButton Button) {

        Button.setToggleGroup(searchTypeGroup);
        Button.setTextFill(Color.web("#ffffffff"));
        Button.setFont(new Font("Fantasy", 12));
    }

    private String getSearchTerm() {
        return searchTypeGroup.getSelectedToggle().getUserData().toString();
    }
}
