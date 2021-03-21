package edu.bsu.cs222.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchStage extends VBox {
    public void createStage(Stage primaryStage) {
        primaryStage primarystage = new primaryStage();
        primarystage.primaryStageEdit(primaryStage,250,400,"Superhero Search");
        VBox searchBox = getSearchBox();
        Label titleLabel = getTitleLabel();
        TextField searchBar = getSearchBar();
        Button searchButton = getSearchButton(searchBar, primaryStage);
        searchButton.setDefaultButton(true);
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

    private Button getSearchButton(TextField articleName, Stage primaryStage) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            HeroStage heroView = new HeroStage();
            heroView.pickSuperhero(articleName.getText(), primaryStage);
        });
        return searchButton;
    }
}
