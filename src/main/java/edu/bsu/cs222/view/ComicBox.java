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

import edu.bsu.cs222.model.Character;
import edu.bsu.cs222.model.ComicBook;
import edu.bsu.cs222.model.Creator;
import edu.bsu.cs222.model.MarvelObject;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ComicBox extends VBox {
    private int comicPage = 1;
    private MarvelObject selected;
    private Stage primaryStage;
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
        SearchBox SearchHBox = new SearchBox();
        VBox HBoxSearchHBox = SearchHBox.createStage(primaryStage);
        resultsBox.getChildren().add(HBoxSearchHBox);
        if (selected instanceof Character) {
            CharacterDetailBox superDetails = new CharacterDetailBox();
            superDetails.showCharacterDetails((Character) selected);
            resultsBox.getChildren().add(superDetails);
        } else {
            CreatorDetailBox creatorDetails = new CreatorDetailBox();
            creatorDetails.showCreatorDetails((Creator) selected);
            resultsBox.getChildren().add(creatorDetails);
        }
        ComicGrid comicPane = new ComicGrid();
        Platform.runLater(() -> runLaterDisplayComics(comicBooks, comicPane));
        HBox pageChooser = createPageChooser();
        Label loadingLabel = new Label("Loading comics, Please wait!");
        comicPane.add(loadingLabel, 0, 0, 5, 1);
        resultsBox.getChildren().addAll(pageChooser, comicPane);
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        PrimaryStage primaryStageEdit = new PrimaryStage();
        primaryStageEdit.primaryStageEdit(primaryStage, 600, 600, "comic books");
        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();
    }

    private void runLaterDisplayComics(List<ComicBook>comicBooks, ComicGrid comicPane) {
        if (comicBooks.size() != 0){
            comicPane.createGrid(comicBooks);
            enableButtons();
        }else{
            Alert APIError = new Alert(Alert.AlertType.INFORMATION);
            APIError.setTitle("API error");
            APIError.setContentText("No more comic books exist in marvels Api \n Returning to previous page");
            APIError.showAndWait();
            comicPage -= 1;
            if (comicPage < 1) comicPage = 1;
            comicBooks(selected, primaryStage, searchTerm);

        }
    }


    private HBox createPageChooser() {
        HBox pageChooser = new HBox();
        pageChooser.setAlignment(Pos.CENTER);
        pageChooser.setSpacing(20);
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



    private Boolean isMoreComics() {
        return selected.getComicsTotal() > comicPage * 100;
    }

    private void enableButtons() {
        if (moreButton != null) {
            moreButton.setDisable(false);
        }
        if (lessButton != null) {
            lessButton.setDisable(false);
        }
    }
}
