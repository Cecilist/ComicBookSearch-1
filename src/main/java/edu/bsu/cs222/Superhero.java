package edu.bsu.cs222;

import javafx.scene.control.Alert;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Superhero {
    private String name;
    private String id;
    private String description;
    private String thumbnailURL;

    public Superhero() {
    }

    public List<Superhero> buildSuperheros (JSONArray characterData) throws IOException {
        MarvelSuperheroParser superheroParser = new MarvelSuperheroParser();
        if(superheroParser.doesCharExist(characterData)) {
            List<Superhero> superheros = new ArrayList<>();
            JSONArray ids = superheroParser.getSuperId(characterData);
            JSONArray names = superheroParser.getSuperName(characterData);
            JSONArray descriptions= superheroParser.getSuperDescript(characterData);
            JSONArray thumbnailURLs = superheroParser.getSuperThumbnail(characterData);
            for(int i=0; i<ids.size(); i++) {
                Superhero newSuperhero = new Superhero();
                newSuperhero.id = String.valueOf(ids.get(i));
                newSuperhero.name = String.valueOf(names.get(i));
                newSuperhero.description = String.valueOf(descriptions.get(i));
                newSuperhero.thumbnailURL = thumbnailURLs.get(i) + "/portrait_medium.jpg";
                superheros.add(newSuperhero);
            }
            return superheros;
        }
        else{
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
            Alert charDoesntExist = new Alert(Alert.AlertType.ERROR);
            charDoesntExist.setTitle("Character Does Not Exist!");
            charDoesntExist.setContentText("The Character does not exist, please try again!");
            charDoesntExist.show();
            System.exit(1);
        }

        return superheros;
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
