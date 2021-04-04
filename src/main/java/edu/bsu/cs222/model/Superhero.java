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

public class Superhero extends MarvelObject {
    private String name;
    private String id;
    private String description;
    private URL thumbnailURL;
    private int comicsTotal;


    public List<Superhero> buildSuperheros(JSONArray characterData) throws IOException {
        MarvelSearchParser superheroParser = new MarvelSearchParser();
        if (superheroParser.doesCharExist(characterData)) {
            List<Superhero> superheros = new ArrayList<>();
            List<String> ids = superheroParser.getInformation(characterData, "id");
            List<String> names = superheroParser.getInformation(characterData, "name");
            List<String> descriptions = superheroParser.getInformation(characterData, "description");
            List<String> thumbnailURLs = superheroParser.getInformation(characterData, "thumbnail.path");
            List<String> comicTotals = superheroParser.getInformation(characterData, "comics.available");
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

    public List<Superhero> createSuperhero(String searchterm,String superheroName) {
        List<Superhero> superheros = new ArrayList<>();
        try {
            String encodedSuperheroName = URLEncoder.encode(superheroName, StandardCharsets.UTF_8.toString());
            MarvelSearchConnection dataStream = new MarvelSearchConnection();
            superheros = buildSuperheros(dataStream.MarvelSearchConnector(searchterm,encodedSuperheroName));
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
