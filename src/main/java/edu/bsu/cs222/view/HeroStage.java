package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Superhero;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class HeroStage extends VBox {
    public void pickSuperhero(String superheroName, Stage primaryStage) {
        ComicStage comicStage = new ComicStage();
        Superhero newSuperhero = new Superhero();
        int noComicsFlag = 0;
        List<Superhero> superheroList = newSuperhero.createSuperhero(superheroName);
        if (superheroList != null) {
            Label instruction = new Label("Please select a Superhero: ");
            instruction.setTextFill(Color.web("#ffffffff"));
            instruction.setFont(Font.font("Fantasy", FontWeight.BOLD, 15));
            VBox superheroButtons = new VBox(instruction);
            ScrollPane buttonScroll = new ScrollPane(superheroButtons);
            superheroButtons.setSpacing(5);
            superheroButtons.setAlignment(Pos.CENTER);
            primaryStage.setWidth(205);
            primaryStage.setHeight(50);
            primaryStage.centerOnScreen();
            for (int i = 0; i < superheroList.size(); i++) {
                primaryStage.setHeight(primaryStage.getHeight()+ 16.5);
                if (superheroList.get(i).hasComics()) {
                    Button superHeroButton = new Button(superheroList.get(i).getName());
                    int finalI = i;
                    superHeroButton.setOnMouseClicked(event -> comicStage.comicBooks(superheroList.get(finalI), primaryStage));
                    superheroButtons.getChildren().add(superHeroButton);
                }
                else noComicsFlag++;

            }
            if (noComicsFlag != superheroList.size()) {
                superheroButtons.setBackground(new Background(
                        new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
                primaryStage.setScene(new Scene(buttonScroll));
                primaryStage.showAndWait();
            }
            else {
                Alert charHasNoComics = new Alert(Alert.AlertType.ERROR);
                charHasNoComics.setTitle("Character has no comics!");
                charHasNoComics.setContentText("The Marvel Character, "+ superheroList.get(0).getName()+
                        ", exists but is not featured in any comics, please try again!");
                charHasNoComics.showAndWait();
            }
            superheroButtons.setBackground(new Background(
                    new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
            primaryStage.setScene(new Scene(buttonScroll));
            primaryStage.show();
            SearchStage createStage = new SearchStage();
            createStage.createStage( primaryStage);
        }
    }
}
