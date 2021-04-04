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

import edu.bsu.cs222.model.ComicBook;
import edu.bsu.cs222.model.Creator;
import edu.bsu.cs222.model.MarvelObject;
import edu.bsu.cs222.model.Superhero;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ComicBox extends VBox {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private int comicPage = 1;
    private MarvelObject selected;
    private Stage primaryStage;
    private Button newSearchButton;
    private Button moreButton;
    private Button lessButton;
    private String searchTerm;

    public void comicBooks(MarvelObject selected, Stage primary, String searchTerm) {
        this.selected = selected;
        primaryStage = primary;
        this.searchTerm = searchTerm;
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(selected.getId(), comicPage, searchTerm);
        showComics(comicBooks);
    }

    public void showComics(List<ComicBook> comicBooks) {
        VBox resultsBox = new VBox();
        if (selected instanceof Superhero) {
            SuperheroDetailBox superDetails = new SuperheroDetailBox();
            superDetails.showSuperheroDetails((Superhero) selected);
            resultsBox.getChildren().add(superDetails);
        } else {
            CreatorDetailBox creatorDetails = new CreatorDetailBox();
            creatorDetails.showCreatorDetails((Creator) selected);
            resultsBox.getChildren().add(creatorDetails);
        }
        ComicGrid comicPane = new ComicGrid();
        Platform.runLater(() -> {
            comicPane.createGrid(comicBooks);
            executor.execute(this::runLater);
        });
        HBox pageChooser = createPageChooser();
        Label loadingLabel = new Label("Loading comics, Please wait!");
        comicPane.add(loadingLabel, 0, 0, 5, 1);
        resultsBox.getChildren().addAll(pageChooser, comicPane);
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        primaryStage primaryStageEdit = new primaryStage();
        primaryStageEdit.primaryStageEdit(primaryStage, 600, 600, "comic books");
        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();
    }


    private HBox createPageChooser() {
        HBox pageChooser = new HBox();
        pageChooser.setAlignment(Pos.CENTER);
        pageChooser.setSpacing(20);
        newSearch();
        pageChooser.getChildren().add(newSearchButton);
        if (isMoreComics()) {
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
            comicBooks(selected, primaryStage, searchTerm);
        });
        moreButton.setDisable(true);
    }

    private void lessResults() {
        lessButton = new Button("Less comics");
        lessButton.setOnAction(event -> {
            comicPage -= 1;
            if (comicPage < 1) comicPage = 1;
            comicBooks(selected, primaryStage, searchTerm);
        });
        lessButton.setDisable(true);
    }


    private void newSearch() {
        newSearchButton = new Button("New search");
        newSearchButton.setOnAction(event -> {
            initialStage newInitialStage = new initialStage();
            newInitialStage.createStage(primaryStage);
        });
        newSearchButton.setDisable(true);
    }

    private Boolean isMoreComics() {
        return selected.getComicsTotal() > comicPage * 100;
    }

    private void runLater() {
        newSearchButton.setDisable(false);
        if (isMoreComics()) {
            moreButton.setDisable(false);
        }
        if (comicPage > 1) {
            lessButton.setDisable(false);
        }
    }
}
