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

public class CreatorComicBox extends VBox {
    private int comicPage = 1;
    private Creator selectedCreator;
    private Stage primaryStage;
    private Button newSearchButton;
    private Button moreButton;
    private Button lessButton;

    public void showCreatorComics(List<ComicBook> comicBooks, String SearchTerm) {

        VBox resultsBox = new VBox();
        CreatorDetailBox CreatorDetails = new CreatorDetailBox();
        CreatorDetails.showCreatorDetails(selectedCreator);
        ComicGrid comicPane = new ComicGrid();
        Platform.runLater(() -> runLater(comicPane,comicBooks));
        HBox pageChooser = createPageChooser(SearchTerm);
        Label loadingLabel = new Label("Loading comics, Please wait!");
        comicPane.add(loadingLabel, 0, 0, 5, 1);
        resultsBox.getChildren().addAll(CreatorDetails, pageChooser, comicPane);
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        primaryStage primaryStageEdit = new primaryStage();
        primaryStageEdit.primaryStageEdit(primaryStage, 600, 600,"comic books");
        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();
    }

    private HBox createPageChooser(String SearchTerm) {
        HBox pageChooser = new HBox();
        pageChooser.setAlignment(Pos.CENTER);
        pageChooser.setSpacing(20);
        newSearch();
        pageChooser.getChildren().add(newSearchButton);
        if (selectedCreator.getComicsTotal() > comicPage * 100) {
            Label pageNumber = new Label("Page: " + comicPage);
            moreResults(SearchTerm);
            moreButton.setDisable(true);
            if (comicPage != 1) {
                lessResults(SearchTerm);
                pageChooser.getChildren().addAll(lessButton, pageNumber, moreButton);
            } else {
                pageChooser.getChildren().addAll(pageNumber, moreButton);
            }
        }
        return pageChooser;
    }

    private void moreResults(String SearchTerm) {
        moreButton = new Button("More comics");
        moreButton.setOnAction(event -> {
            comicPage += 1;
            comicBooks(selectedCreator, primaryStage, SearchTerm);
        });
        moreButton.setDisable(true);
    }

    private void lessResults(String SearchTerm) {
        lessButton = new Button("Less comics");
        lessButton.setOnAction(event -> {
            comicPage -= 1;
            if (comicPage < 1) comicPage = 1;
            comicBooks(selectedCreator, primaryStage, SearchTerm);
        });
        lessButton.setDisable(true);
    }

    public void comicBooks(Creator creator, Stage primary, String SearchTerm) {
        selectedCreator = creator;
        primaryStage = primary;
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(selectedCreator.getId(), comicPage, SearchTerm);
        showCreatorComics(comicBooks, SearchTerm);
    }
    private void newSearch() {
        newSearchButton = new Button("New search");
        newSearchButton.setDisable(true);
        newSearchButton.setOnAction(event -> {
            initialStage newInitialStage = new initialStage();
            newInitialStage.createStage( primaryStage);
        });
        newSearchButton.setDisable(true);
    }
    private void runLater(ComicGrid comicPane, List<ComicBook> comicBooks) {
        comicPane.createGrid(comicBooks);
        moreButton.setDisable(false);
        newSearchButton.setDisable(false);
        lessButton.setDisable(false);
    }
}
