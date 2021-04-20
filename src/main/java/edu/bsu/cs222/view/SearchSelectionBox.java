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
import edu.bsu.cs222.model.Creator;
import edu.bsu.cs222.model.MarvelObject;
import edu.bsu.cs222.model.MarvelSearchParser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchSelectionBox extends VBox {
    private List<MarvelObject> marvelObjectList;
    private List<MarvelObject> noComicList;
    private Stage primaryStage;

    public void pickSearchOption(String searchTerm, String characterName, Stage primaryStage) {
        this.primaryStage = primaryStage;
        noComicList = new ArrayList<>();
        marvelObjectList = new ArrayList<>();
        MarvelSearchParser searchParser = new MarvelSearchParser();

        if (searchTerm.equals("CHARACTERS")) {
            List<Character> charactersList = null;
            try {
                charactersList = searchParser.retrieveCharacterData(characterName);
            } catch (IOException e) {
                showIOAlert();
            }
            if (charactersList != null)
                marvelObjectList.addAll(charactersList);

        } else {
            List<Creator> creatorList = null;
            try {
                creatorList = searchParser.retrieveCreatorData(characterName);
            } catch (IOException e) {
                showIOAlert();
            }
            if (creatorList != null)
                marvelObjectList.addAll(creatorList);
        }

        if (marvelObjectList.size() != 0) {
            getChildren().add(createInstructionLabel());
            setSpacing(5);
            setAlignment(Pos.CENTER);
            createButtons(searchTerm);
            if (!noComicList.isEmpty()) {
                alertNoComic(noComicList);
            }
            setBackground(new Background(
                    new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
            ScrollPane buttonScroll = new ScrollPane(SearchSelectionBox.this);
            buttonScroll.setFitToWidth(true);
            buttonScroll.setFitToHeight(true);
            primaryStage.setScene(new Scene(buttonScroll));
            primaryStage.show();
            refreshStage();
        } else
            showDoesntExist();

    }

    private Label createInstructionLabel() {
        Label instruction = new Label("Please select a Character: ");
        instruction.setTextFill(Color.web("#ffffffff"));
        instruction.setFont(Font.font("Fantasy", FontWeight.BOLD, 15));
        return instruction;
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

    private void createButtons(String SearchTerm) {
        ComicBox comicBox = new ComicBox();
        for (int i = 0; i < marvelObjectList.size(); i++) {
            if (marvelObjectList.get(i).hasComics()) {
                Button characterButton = new Button(marvelObjectList.get(i).getName());
                int finalI = i;
                characterButton.setOnMouseClicked(event -> comicBox.comicBooks(marvelObjectList.get(finalI), primaryStage, SearchTerm));
                getChildren().add(characterButton);
            } else {
                noComicList.add(marvelObjectList.get(i));
            }
        }
    }

    private void showIOAlert() {
        Alert IOAlert = new Alert(Alert.AlertType.ERROR);
        IOAlert.setTitle("IOEXCEPTION");
        IOAlert.setContentText("There was a problem when getting character or creator data");
        IOAlert.showAndWait();
    }

    private void showDoesntExist() {
        Alert doesntExist = new Alert(Alert.AlertType.ERROR);
        doesntExist.setTitle("Not Found");
        doesntExist.setContentText("The term that you searched for doesn't exist!");
        doesntExist.showAndWait();
    }

    public void refreshStage() {
        primaryStage.setWidth(primaryStage.getWidth() + 0.0001);
    }
}