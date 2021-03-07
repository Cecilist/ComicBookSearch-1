package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import javafx.scene.image.Image;
import net.minidev.json.JSONArray;

import java.io.IOException;

public class Character {
    private String name;
    private String id;
    private String description;
    private Image thumbnail;

    public Character() {
    }

    public Character(JSONArray characterData) throws IOException {
        JSONArray idData = JsonPath.read(characterData, "$..results[0].id");
        id = String.valueOf(idData.get(0));
        JSONArray nameData = JsonPath.read(characterData, "$..results[0].name");
        name = String.valueOf(nameData.get(0));
        JSONArray descriptData = JsonPath.read(characterData, "$..results[0].description");
        description = String.valueOf(descriptData.get(0));
        JSONArray thumbnailData = JsonPath.read(characterData, "$..results[0].thumbnail.path");
        thumbnail = setThumbnail(thumbnailData.get(0).toString());
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

    public Image getThumbnail() {
        return thumbnail;
    }

    public String getId() {
        return id;
    }

    private Image setThumbnail(String thumbnailData) throws IOException {
        String thumbnailURLEnd = "/portrait_medium.jpg";
        String thumbnailURL = thumbnailData + thumbnailURLEnd;
        //URL url = new URL(thumbnailURL);
        return (new Image(thumbnailURL));
    }

}
