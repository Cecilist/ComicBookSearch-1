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

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MarvelSearchParser {
    private JSONArray characterData;
    private int index;

    public List<Creator> retrieveCreatorData(String creatorName) throws IOException {
        String encodedCreatorName = URLEncoder.encode(creatorName, StandardCharsets.UTF_8.toString());
        MarvelSearchConnection dataStream = new MarvelSearchConnection();
        dataStream.setSearchType("CREATORS");
        characterData = dataStream.MarvelSearchConnector(encodedCreatorName);
        return buildCreators();
    }

    public List<Creator> buildCreators() throws MalformedURLException {
        List<Creator> creators = new ArrayList<>();
        for (int i = 0; i < characterCount(); i++) {
            setIndex(i);
            Creator newCreator = new Creator();
            newCreator.setId(parse("id"));
            newCreator.setName(parse("fullName"));
            URL thumbnailURL = new URL(parse("thumbnail.path") + "/portrait_medium.jpg");
            newCreator.setThumbnailURL(thumbnailURL);
            newCreator.setComicsTotal(Integer.parseInt(parse("comics.available")));
            newCreator.setSeriesTotal(Integer.parseInt(parse("series.available")));
            newCreator.setStoriesTotal(Integer.parseInt(parse("stories.available")));
            creators.add(newCreator);
        }
        return creators;
    }

    public List<Character>  retrieveCharacterData(String characterName) throws IOException {
        String encodedCharacterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8.toString());
        MarvelSearchConnection dataStream = new MarvelSearchConnection();
        dataStream.setSearchType("CHARACTERS");
        characterData = dataStream.MarvelSearchConnector(encodedCharacterName);
        return buildCharacters();
    }

    public List<Character> buildCharacters() throws IOException {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < characterCount(); i++) {
            setIndex(i);
            Character newCharacter = new Character();
            newCharacter.setId(parse("id"));
            newCharacter.setName(parse("name"));
            newCharacter.setDescription(parse("description"));
            newCharacter.setComicsTotal(Integer.parseInt(parse("comics.available")));
            URL thumbnailURL = new URL(parse("thumbnail.path") + "/portrait_medium.jpg");
            newCharacter.setThumbnailURL(thumbnailURL);
            characters.add(newCharacter);
        }
        return characters;
    }


    public int characterCount() {
        JSONArray totalCount = JsonPath.read(characterData, "$..count");
        return ((int) totalCount.get(0));
    }

    public String parse(String searchTerm) {
        JSONArray information = JsonPath.read(characterData, "$..results[" + index + "]." + searchTerm);
        return String.valueOf(information.get(0));
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setCharacterData(JSONArray characterData){
        this.characterData=characterData;
    }
}
