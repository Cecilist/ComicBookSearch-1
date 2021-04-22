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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CharacterDetailBox extends HBox {
    public void showCharacterDetails(Character character) {
        VBox superDetails = new VBox();
        setMaxHeight(30);
        ImageView characterThumbnail = new ImageView(new Image(character.getThumbnailURL().toString()));
        Label characterName = createCharacterName(character.getName());
        TextArea characterDescription = createCharacterDescription(character);
        superDetails.getChildren().addAll(characterName, characterDescription);
        getChildren().addAll(characterThumbnail, superDetails);
    }

    private Label createCharacterName(String name) {
        Label characterName = new Label(name);
        characterName.setTextFill(Color.web("#ffffffff"));
        characterName.setFont(new Font("Fantasy", 25));
        return characterName;
    }

    private TextArea createCharacterDescription(Character character) {
        TextArea characterDescription = new TextArea(character.getDescription() +
                "\nAppears in " + character.getComicsTotal() + " Marvel comics");
        characterDescription.setWrapText(true);
        characterDescription.setEditable(false);
        return characterDescription;
    }
}
