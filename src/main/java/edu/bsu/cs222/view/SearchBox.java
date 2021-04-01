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

public class SearchBox extends VBox {
    public void createStage(Stage primaryStage, String SearchTerm, boolean isSuperhero) {
        primaryStage primarystage = new primaryStage();
        primarystage.primaryStageEdit(primaryStage, 300, 400, " Search");
        VBox searchBox = createSearchBox();
        Label titleLabel = createTitleLabel(SearchTerm);
        Label SearchLabel = createSearchLabel(SearchTerm);
        TextField searchBar = createSearchBar();
        Button searchButton = createSearchButton(SearchTerm,isSuperhero,searchBar, primaryStage);
        searchButton.setDefaultButton(true);
        searchBox.getChildren().addAll(titleLabel,SearchLabel, searchBar, searchButton);
        primaryStage.setScene(new Scene(searchBox));
        primaryStage.show();
    }

    private VBox createSearchBox() {
        VBox searchBox = new VBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        searchBox.setSpacing(20);
        return searchBox;
    }

    private Label createTitleLabel(String SearchTerm) {
        Label titleLabel = new Label(SearchTerm +" Search");
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 30));
        titleLabel.setPadding(new Insets(10, 10, 10, 10));
        return titleLabel;
    }
    private Label createSearchLabel(String SearchTerm) {
        Label titleLabel = new Label("Enter the name of a Marvel "+ SearchTerm);
        titleLabel.setTextFill(Color.web("#ffffffff"));
        titleLabel.setFont(new Font("Fantasy", 12));
        titleLabel.setPadding(new Insets(10, 10, -10, 10));
        return titleLabel;
    }


    private TextField createSearchBar() {
        TextField searchBar = new TextField("");
        searchBar.setMaxWidth(300);
        searchBar.setOnMouseClicked(event -> searchBar.clear());
        return searchBar;
    }

    private Button createSearchButton(String searchTerm, boolean isSuperhero, TextField articleName, Stage primaryStage) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            if (isSuperhero) {
                HeroBox heroView = new HeroBox();
                heroView.pickSuperhero(searchTerm,articleName.getText(), primaryStage);
            }
            else {
                CreatorBox CreatorBox = new CreatorBox();
                CreatorBox.pickCreator(searchTerm,articleName.getText(), primaryStage);
            }
        });
        return searchButton;
    }
}
