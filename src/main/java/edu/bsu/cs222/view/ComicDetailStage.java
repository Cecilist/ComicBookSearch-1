package edu.bsu.cs222.view;

import edu.bsu.cs222.model.ComicBook;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ComicDetailStage extends Stage {
    public void showComicDetail(ComicBook comicSelected) {
        HBox comicDetailBox = new HBox();
        ImageView comicThumbnail = new ImageView(new Image(comicSelected.getThumbnailURL().toString()));
        StringBuilder creators = new StringBuilder();
        for (int i = 0; i < comicSelected.getCreators().size(); i++) {
            creators.append(comicSelected.getCreators().get(i).getCreators());
        }
        TextArea comicDescription = new TextArea("Comic Book Title:\n" + comicSelected.getTitle() + "\n" + "Description: \n" + comicSelected.getDescription() + "\nCreators: \n" + creators);
        comicDescription.setWrapText(true);
        comicDescription.setEditable(false);
        comicDetailBox.getChildren().addAll(comicThumbnail, comicDescription);
        comicDetailBox.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        Stage comicDetailStage = new Stage();
        comicDetailStage.setScene(new Scene(comicDetailBox));
        comicDetailStage.show();
    }
}
