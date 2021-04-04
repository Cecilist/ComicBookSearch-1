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

import edu.bsu.cs222.model.Creator;
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

import java.util.ArrayList;
import java.util.List;

public class CreatorBox extends VBox {
    private List<Creator> CreatorList;
    private List<Creator> CreatorNoComics;

    public void pickCreator(String searchTerm, String CreatorName, Stage primaryStage) {
        Creator newCreator = new Creator();
        CreatorNoComics = new ArrayList<>();
        CreatorList = newCreator.createCreator(searchTerm,CreatorName);
        if (CreatorList != null) {
            getChildren().add(createInstructionLabel());
            setSpacing(5);
            setAlignment(Pos.CENTER);
            createButtons(primaryStage, searchTerm);
            if (CreatorNoComics.size() != 0) {
                alertNoComic(CreatorNoComics);
            }
            setBackground(new Background(
                    new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
            ScrollPane buttonScroll = new ScrollPane(CreatorBox.this);
            buttonScroll.setFitToWidth(true);
            buttonScroll.setFitToHeight(true);
            primaryStage.setScene(new Scene(buttonScroll));
            primaryStage.show();
        }
    }

    private Label createInstructionLabel() {
        Label instruction = new Label("Please select a Creator: ");
        instruction.setTextFill(Color.web("#ffffffff"));
        instruction.setFont(Font.font("Fantasy", FontWeight.BOLD, 15));
        return instruction;
    }

    private void alertNoComic(List<Creator> CreatorNoComics) {
        Alert charHasNoComics = new Alert(Alert.AlertType.INFORMATION);
        charHasNoComics.setTitle("Creators with no comics found");
        StringBuilder noComicsAlertText = new StringBuilder();
        noComicsAlertText.append("The following creators exist in Marvel's database, however are not " +
                "associated with any comics: ");
        for (Creator CreatorNoComic : CreatorNoComics) {
            noComicsAlertText.append("\n");
            noComicsAlertText.append(CreatorNoComic.getName());
        }
        charHasNoComics.setContentText(noComicsAlertText.toString());
        charHasNoComics.showAndWait();
    }

    private void createButtons(Stage primaryStage, String SearchTerm) {
        ComicBox comicBox = new ComicBox();
        for (int i = 0; i < CreatorList.size(); i++) {
            if (CreatorList.get(i).hasComics()) {
                Button creatorButton = new Button(CreatorList.get(i).getName());
                int finalI = i;
                creatorButton.setOnMouseClicked(event -> comicBox.comicBooks(CreatorList.get(finalI), primaryStage, SearchTerm));
                getChildren().add(creatorButton);
            } else {
                CreatorNoComics.add(CreatorList.get(i));
            }
        }
    }
}