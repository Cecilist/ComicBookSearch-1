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

import edu.bsu.cs222.model.Creator;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CreatorDetailBox extends HBox {
    private TextArea creatorDescription;

    public void showCreatorDetails(Creator creator) {
        VBox creatorDetails = new VBox();
        setMaxHeight(30);
        ImageView characterThumbnail = new ImageView(new Image(creator.getThumbnailURL().toString()));
        characterThumbnail.setFitWidth(100);
        characterThumbnail.setFitHeight(150);
        Label creatorName = createCreatorName(creator.getName());
        TextArea creatorDescription = createCreatorDescription(creator);
        creatorDetails.getChildren().addAll(creatorName, creatorDescription);
        getChildren().addAll(characterThumbnail, creatorDetails);
    }

    private Label createCreatorName(String creator) {
        Label creatorName = new Label(creator);
        creatorName.setTextFill(Color.web("#ffffffff"));
        creatorName.setFont(new Font("Fantasy", 25));
        return creatorName;
    }

    private TextArea createCreatorDescription(Creator creator) {
        creatorDescription = new TextArea("Helped make " + creator.getSeriesTotal() + " Marvel series" +
                "\nHelped make " + creator.getStoriesTotal() + " Marvel stories" +
                "\nHelped make " + creator.getComicsTotal() + " Marvel comics");
        creatorDescription.setWrapText(true);
        creatorDescription.setEditable(false);
        return creatorDescription;
    }

    public void setCreatorDescription(String creatorDescription) {
        this.creatorDescription.setText(creatorDescription);
    }
}
