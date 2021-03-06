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


import edu.bsu.cs222.model.MarvelObject;
import edu.bsu.cs222.model.MarvelSearchParser;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchSelectionBox extends GridPane {
    private final ComicBox comicBox;
    private List<MarvelObject> marvelObjectList;
    private List<MarvelObject> noComicList;
    private TitledPane selectionPane;

    public SearchSelectionBox(ComicBox comicBox) {
        this.comicBox = comicBox;
    }

    public void setTiledPane(TitledPane selectionPane) {
        this.selectionPane = selectionPane;
    }

    public void pickSearchOption(String searchCategory, String searchTerm) {
        getChildren().clear();
        selectionPane.setVisible(false);
        noComicList = new ArrayList<>();
        marvelObjectList = new ArrayList<>();
        MarvelSearchParser searchParser = new MarvelSearchParser();
        try {
            marvelObjectList = searchParser.retrieveData(searchTerm, searchCategory);
        } catch (IOException e) {
            showIOAlert(e);
        }
        if (!marvelObjectList.isEmpty()) {
            createChooseButtons(marvelObjectList, searchCategory);
        } else
            showDoesntExist();
    }

    private void createChooseButtons(List<MarvelObject> marvelObjectList, String searchCategory) {
        if (marvelObjectList.size() == 1) {
            comicBox.setSearchCategory(searchCategory);
            comicBox.setMarvelObject(marvelObjectList.get(0));
            comicBox.createComicBooks();
        } else {
            selectionPane.setVisible(true);
            createButtons(searchCategory);
        }
        if (!noComicList.isEmpty()) {
            alertNoComic(noComicList);
        }

    }

    public void styleBox() {
        setHgap(5);
        setVgap(5);
        setAlignment(Pos.CENTER);
        setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void alertNoComic(List<MarvelObject> characterNoComics) {
        Alert charHasNoComics = new Alert(Alert.AlertType.INFORMATION);
        charHasNoComics.setTitle("Some Characters have no comics");
        StringBuilder noComicsAlertText = new StringBuilder();
        noComicsAlertText.append("The following Marvel characters exist but have no comics: ");
        for (MarvelObject characterNoComic : characterNoComics) {
            noComicsAlertText.append("\n");
            noComicsAlertText.append(characterNoComic.getName());
        }
        charHasNoComics.setContentText(noComicsAlertText.toString());
        charHasNoComics.showAndWait();
    }

    private void createButtons(String searchCategory) {
        int comicCount = 0;
        int BUTTON_COLUMN_WIDTH = 3;
        for (int i = 1; i <= marvelObjectList.size() / BUTTON_COLUMN_WIDTH; i++) {
            for (int x = 0; x < BUTTON_COLUMN_WIDTH; x++) {
                if (comicCount < marvelObjectList.size()) {
                    if (marvelObjectList.get(comicCount).hasComics()) {
                        Button characterButton = new Button(marvelObjectList.get(comicCount).getName());
                        int finalComicCount = comicCount;
                        characterButton.setOnMouseClicked(event -> {
                            comicBox.setSearchCategory(searchCategory);
                            comicBox.setMarvelObject(marvelObjectList.get(finalComicCount));
                            comicBox.createComicBooks();
                        });
                        add(characterButton, x, i);
                    } else {
                        x--;
                        noComicList.add(marvelObjectList.get(comicCount));
                    }
                    comicCount++;
                }
            }
        }

    }

    private void showIOAlert(IOException e) {
        Alert IOAlert = new Alert(Alert.AlertType.ERROR);
        IOAlert.setTitle("IOEXCEPTION");
        IOAlert.setContentText(e.toString());
        IOAlert.showAndWait();
        Platform.exit();
    }

    private void showDoesntExist() {
        Alert doesntExist = new Alert(Alert.AlertType.ERROR);
        doesntExist.setTitle("Not Found");
        doesntExist.setContentText("The term that you searched for doesn't exist!");
        doesntExist.showAndWait();
    }
}