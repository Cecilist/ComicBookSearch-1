package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Superhero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HeroBox extends VBox {
    private List<Superhero> superheroList;
    private List<Superhero> superheroNoComics;
    private Stage primaryStage;

    public void pickSuperhero(String searchTerm, String superheroName, Stage primaryStage) {
        this.primaryStage = primaryStage;
        Superhero newSuperhero = new Superhero();
        superheroNoComics = new ArrayList<>();
        superheroList = newSuperhero.createSuperhero(searchTerm,superheroName);
        if (superheroList != null) {
            getChildren().add(createInstructionLabel());
            setSpacing(5);
            setAlignment(Pos.CENTER);
            createButtons(primaryStage, searchTerm);
            if (superheroNoComics.size() != 0) {
                alertNoComic(superheroNoComics);
            }
            setBackground(new Background(
                    new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
            ScrollPane buttonScroll = new ScrollPane(HeroBox.this);
            buttonScroll.setFitToWidth(true);
            primaryStage.setScene(new Scene(buttonScroll));
            primaryStage.show();
        }
    }

    private Label createInstructionLabel() {
        Label instruction = new Label("Please select a Superhero: ");
        instruction.setTextFill(Color.web("#ffffffff"));
        instruction.setFont(Font.font("Fantasy", FontWeight.BOLD, 15));
        return instruction;
    }

    private void alertNoComic(List<Superhero> superheroNoComics) {
        Alert charHasNoComics = new Alert(Alert.AlertType.INFORMATION);
        charHasNoComics.setTitle("Some Characters have no comics");
        StringBuilder noComicsAlertText = new StringBuilder();
        noComicsAlertText.append("The following Marvel characters exist but have no comics: ");
        for (Superhero superheroNoComic : superheroNoComics) {
            noComicsAlertText.append("\n");
            noComicsAlertText.append(superheroNoComic.getName());
        }
        charHasNoComics.setContentText(noComicsAlertText.toString());
        charHasNoComics.showAndWait();
    }

    private void createButtons(Stage primaryStage, String SearchTerm) {
        ComicBox comicBox = new ComicBox();
        for (int i = 0; i < superheroList.size(); i++) {
            if (superheroList.get(i).hasComics()) {
                Button superHeroButton = new Button(superheroList.get(i).getName());
                int finalI = i;
                superHeroButton.setOnMouseClicked(event -> comicBox.comicBooks(superheroList.get(finalI), primaryStage, SearchTerm));
                getChildren().add(superHeroButton);
            } else {
                superheroNoComics.add(superheroList.get(i));
            }
        }
    }
}