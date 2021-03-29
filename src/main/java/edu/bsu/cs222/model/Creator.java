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

public class Creator {
    private String name;
    private String id;
    private String description;
    private URL thumbnailURL;
    private int comicsTotal;

    public Creator() {
    }

    public List<Creator> buildCreator(JSONArray characterData) throws IOException {
        MarvelSearchParser superheroParser = new MarvelSearchParser();
        if (superheroParser.doesCharExist(characterData)) {
            List<Creator> Creators = new ArrayList<>();
            List<String> ids = superheroParser.getSuperInformation(characterData, "id");
            List<String> names = superheroParser.getSuperInformation(characterData, "fullName");
            List<String> thumbnailURLs = superheroParser.getSuperInformation(characterData, "thumbnail.path");
            List<String> comicTotals = superheroParser.getSuperInformation(characterData, "comics.available");
            for (int i = 0; i < ids.size(); i++) {
                Creator newCreator = new Creator();
                newCreator.id = (ids.get(i));
                newCreator.name = (names.get(i));
                newCreator.thumbnailURL = new URL(thumbnailURLs.get(i) + "/portrait_medium.jpg");
                newCreator.comicsTotal = Integer.parseInt(comicTotals.get(i));
                Creators.add(newCreator);
            }
            return Creators;
        } else {
            throw new IOException("Bad Superhero Name");
        }
    }

    public List<Creator> createCreator(String searchTerm, String CreatorName) {
        List<Creator> Creators = new ArrayList<>();
        try {
            String encodedCreatorName = URLEncoder.encode(CreatorName, StandardCharsets.UTF_8.toString());
            MarvelSearchConnection dataStream = new MarvelSearchConnection();
            Creators = buildCreator(dataStream.MarvelSearchConnector(searchTerm,encodedCreatorName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Alert charDoesNotExist = new Alert(Alert.AlertType.ERROR);
            charDoesNotExist.setTitle(searchTerm +" Does Not Exist!");
            charDoesNotExist.setContentText("The "+searchTerm +"does not exist, please try again!");
            charDoesNotExist.showAndWait();
            return null;


        }

        return Creators;
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
