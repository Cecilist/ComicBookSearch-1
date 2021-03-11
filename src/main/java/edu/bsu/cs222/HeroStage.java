package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class HeroStage extends Stage {
    public void pickSuperhero(String superheroName){
        ComicStage comicStage = new ComicStage();
        Superhero newSuperhero = new Superhero();
        List<Superhero> superheroList = newSuperhero.createSuperhero(superheroName);
        VBox superheroButtons = new VBox(new Label("Please select a Superhero: "));
        ScrollPane buttonScroll = new ScrollPane(superheroButtons);
        superheroButtons.setSpacing(5);
        superheroButtons.setAlignment(Pos.CENTER);
        for(int i=0; i<superheroList.size(); i++)
        {
            Button superHeroButton = new Button(superheroList.get(i).getName());
            int finalI = i;
            superHeroButton.setOnMouseClicked(event -> comicStage.comicBooks(superheroList.get(finalI)));
            superheroButtons.getChildren().add(superHeroButton);
        }

        setScene(new Scene(buttonScroll));
        showAndWait();
    }
}
