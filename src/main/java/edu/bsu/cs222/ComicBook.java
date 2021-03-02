package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private String name;
    private String description;
    private String onsaleDate;
    private ArrayList<Creator> creators = new ArrayList<>();

    public ComicBook(){}

    public List<ComicBook> createComicBooks(JSONArray marvelData){
        //Creator newCreator = new Creator();
        List<ComicBook> comicBooks = new ArrayList<>();
        JSONArray comicTitles = getComicTitles(marvelData);
        JSONArray comicDescriptions = getComicDescript(marvelData);
        JSONArray comicOnsaleDates = getComicDates(marvelData);



        for(int i=0; i<comicTitles.size(); i++){
            ComicBook newComic = new ComicBook();
            newComic.name = String.valueOf(comicTitles.get(i));
            newComic.description = String.valueOf(comicDescriptions.get(i));
            newComic.onsaleDate = String.valueOf(comicOnsaleDates.get(i));
            JSONArray comicCreatorNames = getComicCreatorName(marvelData, i);
            JSONArray comicCreatorRoles = getComicCreatorRole(marvelData, i);
            for(int x=0; x<comicCreatorNames.size(); x++){
                newComic.creators.add(
                        new Creator(String.valueOf(comicCreatorNames.get(x)), String.valueOf(comicCreatorRoles.get(x))));
            }
            comicBooks.add(newComic);
        }
        return comicBooks;
    }
    private JSONArray getComicTitles(JSONArray marvelData)
    {
        return JsonPath.read(marvelData, "$..['results'][*]['title']");
    }
    private JSONArray getComicDescript(JSONArray marvelData)
    {
        return JsonPath.read(marvelData, "$..['results'][*]['description']");
    }
    private JSONArray getComicDates(JSONArray marvelData)
    {
        return JsonPath.read
                (marvelData,"$..['results'][*]['dates'][0]['date']");
    }
    private JSONArray getComicCreatorName(JSONArray marvelData, int index)
    {
        return  JsonPath.read(marvelData, "$..['results']["+index+"]['creators']['items'][*]['name']");
    }
    private JSONArray getComicCreatorRole(JSONArray marvelData, int index)
    {
        return JsonPath.read(marvelData, "$..['results']["+index+"]['creators']['items'][*]['role']");
    }




}
