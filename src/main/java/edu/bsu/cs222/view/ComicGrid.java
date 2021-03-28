package edu.bsu.cs222.view;

import edu.bsu.cs222.model.ComicBook;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class ComicGrid extends GridPane {
    public void createGrid(List<ComicBook> comicBooks) {
        final int COMICBOOK_WIDTH = 5;
        int comicCount = comicBooks.size();
        int comicNumber = 0;
        for (int i = 0; i < comicCount; i++)
            for (int x = 0; x < COMICBOOK_WIDTH; x++) {
                if (comicNumber < comicCount) {
                    Button comicButton = new Button();
                    ComicBook comicCharacter = comicBooks.get(comicNumber);
                    ImageView comicThumbnail = new ImageView(new Image(comicCharacter.getThumbnailURL().toString()));
                    comicButton.setGraphic(comicThumbnail);
                    add(comicButton, x, i);
                    comicButton.setOnMouseClicked(event -> {
                        ComicDetailStage detailStage = new ComicDetailStage();
                        detailStage.showComicDetail(comicCharacter);
                    });
                    comicNumber++;
                }
            }
    }
}
