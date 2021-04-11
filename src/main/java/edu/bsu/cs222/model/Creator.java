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

package edu.bsu.cs222.model;

import javafx.scene.control.Alert;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Creator implements MarvelObject {
    private String name;
    private String id;
    private String role;
    private URL thumbnailURL;
    private int comicsTotal;
    private String seriesTotal;
    private String storiesTotal;

    public List<Creator> buildCreator(JSONArray characterData) throws IOException {
        MarvelSearchParser CreatorParser = new MarvelSearchParser();
        if (CreatorParser.doesCharExist(characterData)) {
            List<Creator> Creators = new ArrayList<>();
            List<String> ids = CreatorParser.getInformation(characterData, "id");
            List<String> names = CreatorParser.getInformation(characterData, "fullName");
            List<String> thumbnailURLs = CreatorParser.getInformation(characterData, "thumbnail.path");
            List<String> comicTotals = CreatorParser.getInformation(characterData, "comics.available");
            List<String> seriesTotals = CreatorParser.getInformation(characterData, "series.available");
            List<String> storiesTotals = CreatorParser.getInformation(characterData, "stories.available");
            for (int i = 0; i < ids.size(); i++) {
                Creator newCreator = new Creator();
                newCreator.id = (ids.get(i));
                newCreator.name = (names.get(i));
                newCreator.seriesTotal = (seriesTotals.get(i));
                newCreator.storiesTotal = (storiesTotals.get(i));
                newCreator.thumbnailURL = new URL(thumbnailURLs.get(i) + "/portrait_medium.jpg");
                newCreator.comicsTotal = Integer.parseInt(comicTotals.get(i));
                Creators.add(newCreator);
            }
            return Creators;
        } else {
            throw new IOException("Bad Character Name");
        }
    }

    public List<Creator> createCreator(String searchTerm, String CreatorName) {
        List<Creator> Creators = new ArrayList<>();
        try {
            String encodedCreatorName = URLEncoder.encode(CreatorName, StandardCharsets.UTF_8.toString());
            MarvelSearchConnection dataStream = new MarvelSearchConnection();
            Creators = buildCreator(dataStream.MarvelSearchConnector(searchTerm, encodedCreatorName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Alert charDoesNotExist = new Alert(Alert.AlertType.ERROR);
            charDoesNotExist.setTitle(searchTerm + " Does Not Exist!");
            charDoesNotExist.setContentText("The " + searchTerm + "does not exist, please try again!");
            charDoesNotExist.showAndWait();
            return null;


        }
        return Creators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getThumbnailURL() {
        return thumbnailURL;
    }

    public String getId() {
        return id;
    }

    public int getComicsTotal() {
        return comicsTotal;
    }

    public boolean hasComics() {
        return comicsTotal > 0;
    }

    public String getSeriesTotal() {
        return seriesTotal;
    }

    public String getStoriesTotal() {
        return storiesTotal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreators() {
        return getRole() + ": " + getName() + "\n";
    }
}
