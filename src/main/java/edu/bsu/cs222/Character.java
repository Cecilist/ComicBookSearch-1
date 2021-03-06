package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Character {
    private String name;
    private String id;
    private String description;
    private BufferedImage thumbnail;

    public Character() {
    }

    public Character createCharacter(String characterName) throws IOException {
        MarvelCharacterDataStream dataStream = new MarvelCharacterDataStream();
        JSONArray characterData = dataStream.MarvelCharacterConnector(characterName);
        return new Character(characterData);
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BufferedImage getThumbnail() {
        return thumbnail;
    }

    public String getId() {
        return id;
    }

    private BufferedImage setThumbnail(String thumbnailData) throws IOException {
        String thumbnailURLEnd = "/portrait_xlarge.jpg";
        String thumbnailURL = thumbnailData + thumbnailURLEnd;
        URL url = new URL(thumbnailURL);
        return (ImageIO.read(url));
    }

}
