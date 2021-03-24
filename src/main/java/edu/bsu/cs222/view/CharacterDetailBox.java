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
        TextArea superHeroDescript = new TextArea(superHero.getDescription() +
                "\nAppears in " + superHero.getComicsTotal() + " Marvel comics");
        superHeroDescript.setWrapText(true);
        superHeroDescript.setEditable(false);
        superDetails.getChildren().addAll(superHeroName, superHeroDescript);
        getChildren().addAll(characterThumbnail, superDetails);
    }
}
