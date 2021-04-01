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

import edu.bsu.cs222.model.Superhero;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CharacterDetailBox extends HBox {
    public void showCharacterDetails(Superhero superHero) {
        VBox superDetails = new VBox();
        setMaxHeight(30);
        ImageView characterThumbnail = new ImageView(new Image(superHero.getThumbnailURL().toString()));
        Label superHeroName = new Label(superHero.getName());
        TextArea superHeroDescription = new TextArea(superHero.getDescription() +
                "\nAppears in " + superHero.getComicsTotal() + " Marvel comics");
        superHeroDescription.setWrapText(true);
        superHeroDescription.setEditable(false);
        superDetails.getChildren().addAll(superHeroName, superHeroDescription);
        getChildren().addAll(characterThumbnail, superDetails);
    }
}
