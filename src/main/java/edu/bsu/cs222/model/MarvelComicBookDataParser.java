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
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;


public class MarvelComicBookDataParser {
    private JSONArray marvelData;
    private int index;

    public List<ComicBook> retrieveComicBookData(String characterId, int comicResultPage, String searchTerm) throws MalformedURLException {
        MarvelComicBookConnection comicBookStream = new MarvelComicBookConnection();
        try {
            comicBookStream.setSearchType(searchTerm);
            marvelData = comicBookStream.MarvelComicBookConnector(characterId, comicResultPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createComicBooks();
    }
    public void setMarvelData(JSONArray marvelData){
        this.marvelData = marvelData;
    }

    public List<ComicBook> createComicBooks() throws MalformedURLException {
        List<ComicBook> comicBooks = new ArrayList<>();
        for(int i=0; i<numOfComics(); i++){
            setIndex(i);
            ComicBook newComic = new ComicBook();
            newComic.setTitle(getComicTitle());
            newComic.setDescription(getComicDescription());
            newComic.setOnSaleDate(getOnSaleDate());
            newComic.setThumbnailURL(getThumbnailURL());
            for(int x=0; x<numOfCreators(); x++) {
                Creator newCreator = new Creator();
                newCreator.setName((getComicCreatorName(x)));
                newCreator.setRole(getComicCreatorRole(x));
                newComic.addCreator(newCreator);
            }
            comicBooks.add(newComic);
        }
        return comicBooks;
    }

    public int numOfComics(){
        JSONArray total = read(marvelData, "$..['count']");
        return (int) total.get(0);
    }
    public int numOfCreators() {
        JSONArray creators = read(marvelData, "$..['results']["+index+"]['creators']['available']");
        return (int) creators.get(0);

    }
    public String getComicTitle() {
        JSONArray titles = read(marvelData, "$..['results'][" + index + "]['title']");
        return String.valueOf(titles.get(0));
    }

    public String getComicDescription() {
        JSONArray description = read(marvelData, "$..['results'][" + index + "]['description']");

        return String.valueOf(description.get(0));
    }

    public LocalDateTime getOnSaleDate() {
        JSONArray timestamp = read
                (marvelData, "$..['results'][" + index + "]['dates'][0]['date']");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'-'SSSS");
        LocalDateTime onSaleDate;
        try{
            onSaleDate =LocalDateTime.parse(String.valueOf(timestamp.get(0)), dateFormat);
        }
        catch(DateTimeException e)
        {
            return null;
        }
        return onSaleDate;

    }

    public String getComicCreatorName(int creatorIndex) {
        JSONArray creatorName = read(marvelData, "$..['results'][" + index + "]['creators']['items']["+creatorIndex+"]['name']");

        return String.valueOf(creatorName.get(0));
    }

    public String getComicCreatorRole(int creatorIndex) {
        JSONArray creatorRole = JsonPath.read(marvelData, "$..['results'][" + index + "]['creators']['items']["+creatorIndex+"]['role']");
        return String.valueOf(creatorRole.get(0));
    }

    public URL getThumbnailURL() throws MalformedURLException {
        JSONArray thumbnail = read(marvelData, "$..['results'][" + index + "].thumbnail.path");

        return new URL(thumbnail.get(0) + "/portrait_medium.jpg");
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
