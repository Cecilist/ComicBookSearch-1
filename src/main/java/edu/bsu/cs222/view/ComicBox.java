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

import edu.bsu.cs222.model.Character;
import edu.bsu.cs222.model.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.util.List;


public class ComicBox extends VBox {
    private final CreatorDetailBox creatorDetails = new CreatorDetailBox();
    private final ComicGrid comicPane = new ComicGrid();
    private int comicPage = 1;
    private MarvelObject selected;
    private Button moreButton;
    private Button lessButton;
    private String searchCategory;
    private List<ComicBook> comicBooks;

    public void createComicBooks() {
        MarvelComicBookDataParser comicBookDataParser = new MarvelComicBookDataParser();
        comicBooks = null;
        try {
            comicBooks = comicBookDataParser.retrieveComicBookData(selected.getId(), comicPage, searchCategory);
        } catch (MalformedURLException e) {
            showURLError();
        }
        showComics();
    }

    public void setMarvelObject(MarvelObject selected) {
        this.selected = selected;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    private void showURLError() {
        Alert URLError = new Alert(Alert.AlertType.ERROR);
        URLError.setTitle("URL ERROR");
        URLError.setContentText("There was an error with the URL");
        URLError.showAndWait();
    }

    public void showComics() {
        getChildren().clear();
        createCharacterDetails();
        if (comicBooks != null) {
            displayComics();
            HBox pageChooser = createPageChooser();
            getChildren().addAll(pageChooser, comicPane);
        }
    }

    private void createCharacterDetails() {
        creatorDetails.getChildren().clear();
        if (selected instanceof Character) {
            CharacterDetailBox superDetails = new CharacterDetailBox();
            superDetails.showCharacterDetails((Character) selected);
            getChildren().add(superDetails);
        } else {
            creatorDetails.showCreatorDetails((Creator) selected);
            getChildren().add(creatorDetails);
        }
    }

    public void replaceCreatorDescription(String description) {
        creatorDetails.setCreatorDescription(description);
    }

    private void displayComics() {
        if (comicBooks.size() != 0) {
            comicPane.getChildren().clear();
            comicPane.createGrid(comicBooks);
        } else {
            showAPIError();

        }
    }

    private void showAPIError() {
        Alert APIError = new Alert(Alert.AlertType.INFORMATION);
        APIError.setTitle("API error");
        APIError.setContentText("No comic books exist in marvels Api");
        APIError.showAndWait();
    }


    private HBox createPageChooser() {
        HBox pageChooser = new HBox();
        pageChooser.setAlignment(Pos.CENTER);
        pageChooser.setSpacing(20);
        if (hasMoreComics()) {
            Label pageNumber = new Label("Page: " + comicPage);
            moreResults();
            if (comicPage != 1) {
                lessResults();
                pageChooser.getChildren().addAll(lessButton, pageNumber, moreButton);
            } else {
                pageChooser.getChildren().addAll(pageNumber, moreButton);
            }
        }
        return pageChooser;
    }

    private void moreResults() {
        moreButton = new Button("More comics");

        moreButton.setOnAction(event -> {
            comicPage += 1;
            createComicBooks();
        });

    }

    private void lessResults() {
        lessButton = new Button("Less comics");
        lessButton.setOnAction(event -> {
            comicPage -= 1;
            if (comicPage < 1) comicPage = 1;
            createComicBooks();
        });

    }


    private Boolean hasMoreComics() {
        final int COMIC_LIMIT = 100;
        return selected.getComicsTotal() > comicPage * COMIC_LIMIT;
    }
}
