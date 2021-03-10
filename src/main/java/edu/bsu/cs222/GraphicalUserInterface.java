package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GraphicalUserInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Superhero Search");
        primaryStage.setWidth(400);
        primaryStage.setHeight(250);
        VBox searchBox = getSearchBox();
        Label titleLabel = getTitleLabel();
        TextField searchBar = getSearchBar();
        Button searchButton = getSearchButton(searchBar);

        searchBox.getChildren().addAll(titleLabel, searchBar, searchButton);
        primaryStage.setScene(new Scene(searchBox));
        primaryStage.show();
    }

    private VBox getSearchBox() {
        VBox searchBox = new VBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        searchBox.setSpacing(20);
        return searchBox;
    }

    private Label getTitleLabel() {
        Label titleLabel = new Label("Superhero Search");
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 30));
        titleLabel.setPadding(new Insets(10, 10, 10, 10));
        return titleLabel;
    }

    private TextField getSearchBar() {
        TextField searchBar = new TextField("Enter the name of a Marvel Superhero");
        searchBar.setMaxWidth(300);
        searchBar.setOnMouseClicked(event -> searchBar.clear());
        return searchBar;
    }

    private Button getSearchButton(TextField articleName) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            try {
                GraphicalUserInterface.this.comicBooks(articleName.getText());
            } catch (IOException e) {
                Alert articleNameError = new Alert(Alert.AlertType.ERROR);
                articleNameError.setTitle("SuperHero Name Error");
                articleNameError.show();
            }
        });
        return searchButton;
    }

    private void comicBooks(String superheroName) throws IOException {
        String marvelHeroName = URLEncoder.encode(superheroName, StandardCharsets.UTF_8.toString());
        Character newCharacter = new Character();
        newCharacter = newCharacter.createCharacter(marvelHeroName);
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(newCharacter.getId());
        showComics(newCharacter, comicBooks);

    }

    private void showComics(Character superHero, List<ComicBook> comicBooks) {
        final int COMICBOOK_WIDTH = 5;

        VBox resultsBox = new VBox();
        ScrollPane scrollPane =  new ScrollPane(resultsBox);
        HBox characterBox = new HBox();
        ImageView characterThumbnail = new ImageView(new Image(superHero.getThumbnailURL()));
        VBox superHeroDetails = new VBox();
        Label superHeroName = new Label(superHero.getName());
        TextArea superHeroDescript = new TextArea(superHero.getDescription());
        superHeroDescript.setWrapText(true);
        superHeroDescript.setEditable(false);
        superHeroDetails.getChildren().addAll(superHeroName, superHeroDescript);
        characterBox.getChildren().addAll(characterThumbnail, superHeroDetails);

        GridPane comicPane = new GridPane();

        int comicCount = comicBooks.size();
        for (int i = 0; i < comicCount; i++)
            for (int x = 0; x < COMICBOOK_WIDTH; x++) {
                comicCount--;
                Button comicButton = new Button();
                ComicBook comicCharacter = comicBooks.get(comicCount);
                ImageView comicThumbnail = new ImageView(new Image (comicCharacter.getThumbnailURL()));
                comicButton.setGraphic(comicThumbnail);
                comicPane.add(comicButton, x, i);
                comicButton.setOnMouseClicked(event -> showComicDetail(comicCharacter));
            }
        resultsBox.getChildren().addAll(characterBox, comicPane);
        Stage secondaryStage = new Stage();
        secondaryStage.setHeight(600);
        secondaryStage.setWidth(600);
        secondaryStage.setScene(new Scene(scrollPane));
        secondaryStage.show();
    }
    public void showComicDetail(ComicBook comicSelected){
        HBox comicDetailBox = new HBox();
        ImageView comicThumbnail= new ImageView(new Image (comicSelected.getThumbnailURL()));
        StringBuilder creators= new StringBuilder();
        for(int i=0; i<comicSelected.getCreators().size(); i++)
        {
            creators.append(comicSelected.getCreators().get(i).getCreators());
        }
        TextArea comicDescription = new TextArea("Comic Book Title:\n"+comicSelected.getTitle()+"\n"+ "Description: \n"+comicSelected.getDescription()+"\nCreators: \n"+creators);
        comicDescription.setWrapText(true);
        comicDescription.setEditable(false);
        comicDetailBox.getChildren().addAll(comicThumbnail, comicDescription);
        Stage comicDetailStage = new Stage();
        comicDetailStage.setScene(new Scene(comicDetailBox));
        comicDetailStage.show();
    }

}