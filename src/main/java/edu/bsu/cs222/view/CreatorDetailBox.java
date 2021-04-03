package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Creator;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreatorDetailBox extends HBox {
    public void showCreatorDetails(Creator creator) {
        VBox creatorDetails = new VBox();
        setMaxHeight(30);
        ImageView characterThumbnail = new ImageView(new Image(creator.getThumbnailURL().toString()));
        Label creatorName = new Label(creator.getName());
        Label creatorDescription = new Label("/nCreated in in " + creator.getComicsTotal() + " Marvel comics");
        creatorDetails.getChildren().addAll(creatorName, creatorDescription);
        getChildren().addAll(characterThumbnail, creatorDetails);
    }
}
