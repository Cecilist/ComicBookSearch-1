//  <Program to search for comics and creators that Marvel has available information on.>
//  Copyright (C) <2021>  <Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish>

//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see <https://www.gnu.org/licenses/>.

package edu.bsu.cs222.view;

import edu.bsu.cs222.model.ComicBook;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ComicDetailStage extends Stage {
    private final GraphicalUserInterface gui = new GraphicalUserInterface();
    public void showComicDetail(ComicBook comicSelected) {
        HBox comicDetailBox = new HBox();
        ImageView comicThumbnail = new ImageView(new Image(comicSelected.getThumbnailURL().toString()));
        StringBuilder creators = new StringBuilder();
        for (int i = 0; i < comicSelected.getCreators().size(); i++) {
            creators.append(comicSelected.getCreators().get(i).getCreators());
        }
        TextArea comicDescription = new TextArea("Comic Book Title:\n" + comicSelected.getTitle() + "\n" +
                "On Sale Date: " + comicSelected.getFormattedSaleDate() + "\nDescription: \n" +
                comicSelected.getDescription() + "\nCreators: \n" + creators);
        comicDescription.appendText("\nSearch for this comic on eBay!");
        Button ebayButton = createEbayButton(comicSelected);
        comicDescription.setWrapText(true);
        comicDescription.setEditable(false);
        comicDetailBox.getChildren().addAll(comicThumbnail, comicDescription, ebayButton);
        if (comicSelected.isHasDigital()) {
            comicDescription.appendText("\nMarvel App Sale Price: " + comicSelected.getPrice());
            Button playStore = createMarvelPlayStoreButton();
            Button appStore = createMarvelAppStoreButton();
            comicDetailBox.getChildren().addAll(playStore, appStore);
        }
        comicDetailBox.setBackground(new Background(
                new BackgroundFill(Color.web("#F0131E"), CornerRadii.EMPTY, Insets.EMPTY)));
        Stage comicDetailStage = new Stage();
        comicDetailStage.setScene(new Scene(comicDetailBox));
        comicDetailStage.show();
    }

    private Button createMarvelPlayStoreButton() {
        Button playStore = new Button();
        Image playImage = new Image(getClass().getResource("/google-play-badge.png").toExternalForm());
        ImageView playImageView = new ImageView(playImage);
        playImageView.setFitHeight(60);
        playImageView.setPreserveRatio(true);
        playStore.setGraphic(playImageView);
        playStore.setStyle("-fx-background-color: transparent;");
        playStore.setOnMouseClicked(e -> gui.getHostServices().showDocument("https://play.google.com/store/apps/details?id=com.marvel.comics&hl=en_US"));
        return playStore;
    }

    private Button createMarvelAppStoreButton() {
        Button appStore = new Button();
        Image appImage = new Image(getClass().getResource("/appStore.png").toExternalForm());
        ImageView appImageView = new ImageView(appImage);
        appImageView.setFitHeight(45);
        appImageView.setPreserveRatio(true);
        appStore.setGraphic(appImageView);
        appStore.setStyle("-fx-background-color: transparent;");
        appStore.setOnMouseClicked(e -> gui.getHostServices().showDocument("https://apps.apple.com/us/app/marvel-comics/id350027738"));
        return appStore;
    }

    private Button createEbayButton(ComicBook comicSelected) {
        Button URLClick = new Button("Find on eBay");
        String encodedTitle = null;
        try {
            encodedTitle = URLEncoder.encode(comicSelected.getTitle(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Alert encodingError = new Alert(Alert.AlertType.ERROR);
            encodingError.setContentText("Error encoding title for eBay button!");
            encodingError.showAndWait();
        }
        String finalEncodedTitle = encodedTitle;
        URLClick.setOnMouseClicked(e -> gui.getHostServices().showDocument("https://www.ebay.com/sch/63/i.html?_from=R40&_nkw=" + finalEncodedTitle));


        return URLClick;

    }
}
