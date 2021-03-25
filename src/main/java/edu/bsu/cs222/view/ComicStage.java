package edu.bsu.cs222.view;

import edu.bsu.cs222.model.ComicBook;
import edu.bsu.cs222.model.Superhero;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ComicStage extends VBox {
    private int comicPage = 0;

    public void showComics(Superhero superHero, List<ComicBook> comicBooks, Stage primaryStage) {
        final int COMICBOOK_WIDTH = 5;
        VBox resultsBox = new VBox();
        CharacterDetailBox superDetails = new CharacterDetailBox();
        superDetails.showCharacterDetails(superHero);
        HBox characterBox;
        characterBox = superDetails;
        GridPane comicPane = new GridPane();
        Platform.runLater(() -> {
            int comicCount = comicBooks.size();
            int comicNumber = 0;
            for (int i = 0; i < comicCount; i++)
                for (int x = 0; x < COMICBOOK_WIDTH; x++) {
                    if (comicNumber < comicCount) {
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

        });
        HBox pageChooser = HBoxPageChooser(superHero, primaryStage);
        Label loadingLabel = new Label("Loading comics, Please wait!");
        comicPane.add(loadingLabel, 0, 0, 5, 1);
        resultsBox.getChildren().addAll(characterBox, pageChooser, comicPane);
        edu.bsu.cs222.view.primaryStage primarystage = new primaryStage();
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        primaryStage.setScene(new Scene(scrollPane));
        primarystage.primaryStageEdit(primaryStage,600,600,superHero.getName()+" comics");
    }

    private Button nextResults(Superhero superhero, Stage primaryStage) {
        Button nextButton = new Button("Next comics Page");
        nextButton.setOnAction(event -> {
            comicPage += 1;
            comicBooks(superhero, primaryStage);
        });
        return nextButton;
    }

    private Button previousResults(Superhero superhero, Stage primaryStage) {
        Button previousButton = new Button("Previous comics Page");
        previousButton.setOnAction(event -> {
            comicPage -= 1;
            if (comicPage < 0) comicPage = 0;
            comicBooks(superhero, primaryStage);
        });
        return previousButton;
    }

    public void comicBooks(Superhero superhero, Stage primaryStage) {
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(superhero.getId(), comicPage);
        showComics(superhero, comicBooks, primaryStage);
    }
    public HBox HBoxPageChooser(Superhero superHero, Stage primaryStage) {
        HBox pageChooser = new HBox();
        pageChooser.setAlignment(Pos.CENTER);
        pageChooser.setSpacing(20);
        Button backButton = backResults(primaryStage);
        pageChooser.getChildren().add(backButton);
        if (superHero.getComicsTotal() > (comicPage+1) * 100) {
            Label pageNumber = new Label("Page: " + (comicPage + 1));
            Button nextButton = nextResults(superHero, primaryStage);
            if (comicPage != 0) {
                Button previousButton = previousResults(superHero, primaryStage);
                pageChooser.getChildren().addAll(previousButton, pageNumber, nextButton);
            } else {
                pageChooser.getChildren().addAll(pageNumber, nextButton);
            }
        }
        return pageChooser;
    }

    private Button backResults(Stage primaryStage) {
        Button backButton = new Button("Back to Search");
        backButton.setOnAction(event -> {
            SearchStage createStage = new SearchStage();
            createStage.createStage( primaryStage);
        });
        return backButton;
    }
}
