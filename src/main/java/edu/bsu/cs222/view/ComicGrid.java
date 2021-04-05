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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class ComicGrid extends GridPane {
    public Runnable createGrid(List<ComicBook> comicBooks) {
        final int COMICBOOK_WIDTH = 5;
        int numOfComics = comicBooks.size();
        int comicCount = 0;
        for (int i = 0; i < numOfComics; i++)
            for (int x = 0; x < COMICBOOK_WIDTH; x++) {
                if (comicCount < numOfComics) {
                    Button comicButton = new Button();
                    ComicBook comicCharacter = comicBooks.get(comicCount);
                    ImageView comicThumbnail = new ImageView(new Image(comicCharacter.getThumbnailURL().toString()));
                    comicButton.setGraphic(comicThumbnail);
                    add(comicButton, x, i);
                    comicButton.setOnMouseClicked(event -> {
                        ComicDetailStage detailStage = new ComicDetailStage();
                        detailStage.showComicDetail(comicCharacter);
                    });
                    comicCount++;
                }
            }
        return null;
    }
}
