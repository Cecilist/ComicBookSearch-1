package edu.bsu.cs222.view;

import edu.bsu.cs222.model.ComicBook;
import edu.bsu.cs222.model.Superhero;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ComicStage extends Stage {
    private int comicPage=1;
    public void showComics(Superhero superHero, List<ComicBook> comicBooks) {
        final int COMICBOOK_WIDTH = 5;
        VBox resultsBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        HBox characterBox = new HBox();
        characterBox.setMaxHeight(30);
        ImageView characterThumbnail = new ImageView(new Image(superHero.getThumbnailURL().toString()));
        VBox superHeroDetails = new VBox();
        Label superHeroName = new Label(superHero.getName());
        TextArea superHeroDescript = new TextArea(superHero.getDescription()+
                "\nAppears in "+superHero.getComicsTotal()+" Marvel comics");
        superHeroDescript.setWrapText(true);
        superHeroDescript.setEditable(false);
        superHeroDetails.getChildren().addAll(superHeroName, superHeroDescript);
        characterBox.getChildren().addAll(characterThumbnail, superHeroDetails);

        GridPane comicPane = new GridPane();

        int comicCount = comicBooks.size();
        int comicNumber=0;
        for (int i = 0; i < comicCount; i++)
            for (int x = 0; x < COMICBOOK_WIDTH; x++) {
                if (comicNumber<comicCount) {
                    Button comicButton = new Button();
                    ComicBook comicCharacter = comicBooks.get(comicNumber);
                    ImageView comicThumbnail = new ImageView(new Image(comicCharacter.getThumbnailURL().toString()));
                    comicButton.setGraphic(comicThumbnail);
                    comicPane.add(comicButton, x, i);
                    comicButton.setOnMouseClicked(event -> {
                        ComicDetailStage detailStage = new ComicDetailStage();
                        detailStage.showComicDetail(comicCharacter);
                    });
                    comicNumber++;
                }
            }
        resultsBox.getChildren().addAll(characterBox, comicPane);
        if (superHero.getComicsTotal()> comicPage * 100) {
            Button moreButton = moreResults(superHero);
            resultsBox.getChildren().add(moreButton);
        }
        setHeight(600);
        setWidth(600);
        setScene(new Scene(scrollPane));
        showAndWait();
    }

    private Button moreResults(Superhero superhero) {
        Button moreButton = new Button("More comics");
        moreButton.setOnAction(event -> {
            comicPage++;
            comicBooks(superhero);
        });
        return moreButton;
    }

    public void comicBooks(Superhero superhero) {
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(superhero.getId(), comicPage);
        ComicStage comicView = new ComicStage();
        comicView.showComics(superhero, comicBooks);
    }
}
