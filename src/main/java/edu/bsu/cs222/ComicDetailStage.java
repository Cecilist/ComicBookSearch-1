package edu.bsu.cs222;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComicDetailStage extends Stage{
    public void showComicDetail(ComicBook comicSelected) {
        HBox comicDetailBox = new HBox();
        ImageView comicThumbnail = new ImageView(new Image(comicSelected.getThumbnailURL()));
        StringBuilder creators = new StringBuilder();
        for (int i = 0; i < comicSelected.getCreators().size(); i++) {
            creators.append(comicSelected.getCreators().get(i).getCreators());
        }
        TextArea comicDescription = new TextArea("Comic Book Title:\n" + comicSelected.getTitle() + "\n" + "Description: \n" + comicSelected.getDescription() + "\nCreators: \n" + creators);
        comicDescription.setWrapText(true);
        comicDescription.setEditable(false);
        comicDetailBox.getChildren().addAll(comicThumbnail, comicDescription);
        Stage comicDetailStage = new Stage();
        comicDetailStage.setScene(new Scene(comicDetailBox));
        comicDetailStage.show();
    }
}
