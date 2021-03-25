package edu.bsu.cs222.view;

import edu.bsu.cs222.model.ComicBook;
import edu.bsu.cs222.model.Superhero;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ComicBox extends VBox {
    private int comicPage = 1;
    private Superhero selectedHero;
    private Stage primaryStage;

    public void showComics(List<ComicBook> comicBooks) {

        VBox resultsBox = new VBox();
        CharacterDetailBox superDetails = new CharacterDetailBox();
        superDetails.showCharacterDetails(selectedHero);
        ComicGrid comicPane = new ComicGrid();
        Platform.runLater(() -> comicPane.createGrid(comicBooks));
        HBox pageChooser = createPageChooser();
        Label loadingLabel = new Label("Loading comics, Please wait!");
        comicPane.add(loadingLabel, 0, 0, 5, 1);
        resultsBox.getChildren().addAll(superDetails, pageChooser, comicPane);
        ScrollPane scrollPane = new ScrollPane(resultsBox);
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();
    }

    private HBox createPageChooser() {
        HBox pageChooser = new HBox();
        pageChooser.setAlignment(Pos.CENTER);
        pageChooser.setSpacing(20);
        if (selectedHero.getComicsTotal() > comicPage * 100) {
            Label pageNumber = new Label("Page: " + comicPage);
            Button moreButton = moreResults();
            if (comicPage != 1) {
                Button lessButton = lessResults();
                pageChooser.getChildren().addAll(lessButton, pageNumber, moreButton);
            } else {
                pageChooser.getChildren().addAll(pageNumber, moreButton);
            }
        }
        return pageChooser;
    }

    private Button moreResults() {
        Button moreButton = new Button("More comics");
        moreButton.setOnAction(event -> {
            comicPage += 1;
            comicBooks(selectedHero, primaryStage);
        });
        return moreButton;
    }

    private Button lessResults() {
        Button lessButton = new Button("Less comics");
        lessButton.setOnAction(event -> {
            comicPage -= 1;
            if (comicPage < 1) comicPage = 1;
            comicBooks(selectedHero, primaryStage);
        });
        return lessButton;
    }

    public void comicBooks(Superhero superhero, Stage primary) {
        selectedHero = superhero;
        primaryStage = primary;
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(selectedHero.getId(), comicPage);
        showComics(comicBooks);
    }
}
