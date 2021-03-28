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

public class Superhero {
    private String name;
    private String id;
    private String description;
    private URL thumbnailURL;
    private int comicsTotal;

    public Superhero() {
    }

    public List<Superhero> buildSuperheros(JSONArray characterData) throws IOException {
        MarvelSuperheroParser superheroParser = new MarvelSuperheroParser();
        if (superheroParser.doesCharExist(characterData)) {
            List<Superhero> superheros = new ArrayList<>();
            List<String> ids = superheroParser.getSuperInformation(characterData, "id");
            List<String> names = superheroParser.getSuperInformation(characterData, "name");
            List<String> descriptions = superheroParser.getSuperInformation(characterData, "description");
            List<String> thumbnailURLs = superheroParser.getSuperInformation(characterData, "thumbnail.path");
            List<String> comicTotals = superheroParser.getSuperInformation(characterData, "comics.available");
            for (int i = 0; i < ids.size(); i++) {
                Superhero newSuperhero = new Superhero();
                newSuperhero.id = (ids.get(i));
                newSuperhero.name = (names.get(i));
                newSuperhero.description = (descriptions.get(i));
                newSuperhero.thumbnailURL = new URL(thumbnailURLs.get(i) + "/portrait_medium.jpg");
                newSuperhero.comicsTotal = Integer.parseInt(comicTotals.get(i));
                superheros.add(newSuperhero);
            }
            return superheros;
        } else {
            throw new IOException("Bad Superhero Name");
        }
    }

    public List<Superhero> createSuperhero(String superheroName) {
        List<Superhero> superheros = new ArrayList<>();
        try {
            String encodedSuperheroName = URLEncoder.encode(superheroName, StandardCharsets.UTF_8.toString());
            MarvelSuperheroDataStream dataStream = new MarvelSuperheroDataStream();
            superheros = buildSuperheros(dataStream.MarvelCharacterConnector(encodedSuperheroName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Alert charDoesNotExist = new Alert(Alert.AlertType.ERROR);
            charDoesNotExist.setTitle("Character Does Not Exist!");
            charDoesNotExist.setContentText("The Character does not exist, please try again!");
            charDoesNotExist.showAndWait();
            return null;


        }

        return superheros;
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
