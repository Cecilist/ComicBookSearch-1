package edu.bsu.cs222;

import javafx.scene.control.Alert;
import net.minidev.json.JSONArray;

import java.io.IOException;

public class Character{
    private String name;
    private String id;
    private String description;
    private String thumbnailURL;

    public Character() {
    }

    public Character(JSONArray characterData) throws IOException {
        MarvelCharacterParser characterParser = new MarvelCharacterParser();
        if(characterParser.doesCharExist(characterData)) {
            id = String.valueOf(characterParser.getCharId(characterData).get(0));
            name = String.valueOf(characterParser.getCharName(characterData).get(0));
            description = String.valueOf(characterParser.getCharDescription(characterData).get(0));
            thumbnailURL = characterParser.getCharThumbnail(characterData).get(0) + "/portrait_medium.jpg";

        }
        else{
            Alert charDoesNotExist = new Alert(Alert.AlertType.ERROR);
            charDoesNotExist.setTitle("Character Does Not Exist!");
            charDoesNotExist.setContentText("The Character does not exist, please try again!");
            charDoesNotExist.show();
        }
    }

    public Character createCharacter(String characterName) throws IOException {
        MarvelCharacterDataStream dataStream = new MarvelCharacterDataStream();
        JSONArray characterData = dataStream.MarvelCharacterConnector(characterName);
        return new Character(characterData);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getId() {
        return id;
    }

}
