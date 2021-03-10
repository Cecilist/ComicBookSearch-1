package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private final ArrayList<Creator> creators = new ArrayList<>();
    private String title;
    private String description;
    private String onsaleDate;
    private String thumbnailURL;

    public ComicBook(){}

    public List<ComicBook> getComicBookData(String characterId) throws IOException {
        MarvelComicBookDataStream comicBookStream = new MarvelComicBookDataStream();
        return createComicBooks(comicBookStream.MarvelComicBookConnector(characterId));
    }
    public List<ComicBook> createComicBooks(JSONArray comicBookData){
        MarvelComicBookDataParser comicBookParser = new MarvelComicBookDataParser();
        List<ComicBook> comicBooks = new ArrayList<>();
        JSONArray comicTitles = comicBookParser.getComicTitles(comicBookData);
        JSONArray comicDescriptions = comicBookParser.getComicDescript(comicBookData);
        JSONArray comicOnsaleDates = comicBookParser.getComicDates(comicBookData);
        for (int i = 0; i < comicTitles.size(); i++) {
            ComicBook newComic = new ComicBook();
            newComic.title = String.valueOf(comicTitles.get(i));
            newComic.description = String.valueOf(comicDescriptions.get(i));
            newComic.onsaleDate = String.valueOf(comicOnsaleDates.get(i));
            newComic.thumbnailURL = comicBookParser.getThumbnail(comicBookData).get(i) + "/portrait_medium.jpg";
            JSONArray comicCreatorNames = comicBookParser.getComicCreatorName(comicBookData, i);
            JSONArray comicCreatorRoles = comicBookParser.getComicCreatorRole(comicBookData, i);
            for (int x = 0; x < comicCreatorNames.size(); x++) {
                newComic.creators.add(
                        new Creator(String.valueOf(comicCreatorNames.get(x)), String.valueOf(comicCreatorRoles.get(x))));
            }
            comicBooks.add(newComic);
        }
        return comicBooks;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOnsaleDate() {
        return onsaleDate;
    }

    public ArrayList<Creator> getCreators() {
        return creators;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
