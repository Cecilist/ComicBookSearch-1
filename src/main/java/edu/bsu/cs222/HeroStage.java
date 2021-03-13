package edu.bsu.cs222;

import javafx.application.Platform;
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

import java.util.List;

public class HeroStage extends Stage {
    public void pickSuperhero(String superheroName) {
        ComicStage comicStage = new ComicStage();
        Superhero newSuperhero = new Superhero();
        List<Superhero> superheroList = newSuperhero.createSuperhero(superheroName);
        if (superheroList != null) {
            Label instruction = new Label("Please select a Superhero: ");
            instruction.setTextFill(Color.web("#ffffffff"));
            instruction.setFont(Font.font("Fantasy", FontWeight.BOLD, 15));
            VBox superheroButtons = new VBox(instruction);
            ScrollPane buttonScroll = new ScrollPane(superheroButtons);
            superheroButtons.setSpacing(5);
            superheroButtons.setAlignment(Pos.CENTER);
            int noComicsFlag = 0;
            for (int i = 0; i < superheroList.size(); i++) {
                if(superheroList.get(i).hasComics()) {
                    Button superHeroButton = new Button(superheroList.get(i).getName());
                    int finalI = i;
                    superHeroButton.setOnMouseClicked(event -> comicStage.comicBooks(superheroList.get(finalI)));
                    superheroButtons.getChildren().add(superHeroButton);
                }
                else noComicsFlag++;

            }
            if (noComicsFlag != superheroList.size()) {
                superheroButtons.setBackground(new Background(
                        new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
                setScene(new Scene(buttonScroll));
                showAndWait();
            }
            else {
                Alert charHasNoComics = new Alert(Alert.AlertType.ERROR);
                charHasNoComics.setTitle("Character has no comics!");
                charHasNoComics.setContentText("The Marvel Character, "+ superheroList.get(0).getName()+
                        ", exists but is not featured in any comics, please try again!");
                charHasNoComics.showAndWait();
            }
        }
    }
}
