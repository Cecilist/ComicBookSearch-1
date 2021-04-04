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

public class Character extends MarvelObject {
    private String name;
    private String id;
    private String description;
    private URL thumbnailURL;
    private int comicsTotal;


    public List<Character> buildCharacters(JSONArray characterData) throws IOException {
        MarvelSearchParser characterParser = new MarvelSearchParser();
        if (characterParser.doesCharExist(characterData)) {
            List<Character> characters = new ArrayList<>();
            List<String> ids = characterParser.getInformation(characterData, "id");
            List<String> names = characterParser.getInformation(characterData, "name");
            List<String> descriptions = characterParser.getInformation(characterData, "description");
            List<String> thumbnailURLs = characterParser.getInformation(characterData, "thumbnail.path");
            List<String> comicTotals = characterParser.getInformation(characterData, "comics.available");
            for (int i = 0; i < ids.size(); i++) {
                Character newCharacter = new Character();
                newCharacter.id = (ids.get(i));
                newCharacter.name = (names.get(i));
                newCharacter.description = (descriptions.get(i));
                newCharacter.thumbnailURL = new URL(thumbnailURLs.get(i) + "/portrait_medium.jpg");
                newCharacter.comicsTotal = Integer.parseInt(comicTotals.get(i));
                characters.add(newCharacter);
            }
            return characters;
        } else {
            throw new IOException("Bad Character Name");
        }
    }

    public List<Character> createCharacter(String searchterm, String characterName) {
        List<Character> characters = new ArrayList<>();
        try {
            String encodedCharacterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8.toString());
            MarvelSearchConnection dataStream = new MarvelSearchConnection();
            characters = buildCharacters(dataStream.MarvelSearchConnector(searchterm, encodedCharacterName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Alert charDoesNotExist = new Alert(Alert.AlertType.ERROR);
            charDoesNotExist.setTitle("Character Does Not Exist!");
            charDoesNotExist.setContentText("The Character does not exist, please try again!");
            charDoesNotExist.showAndWait();
            return null;


        }

        return characters;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

}
