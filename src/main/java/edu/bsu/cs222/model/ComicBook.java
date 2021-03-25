package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private final ArrayList<Creator> creators = new ArrayList<>();
    private String title;
    private String description;
    private String onSaleDate;
    private URL thumbnailURL;

    public ComicBook() {
    }

    public List<ComicBook> getComicBookData(String characterId, int comicResultPage) {
        MarvelComicBookDataStream comicBookStream = new MarvelComicBookDataStream();
        List<ComicBook> comicBookList = null;
        try {
            comicBookList = createComicBooks(comicBookStream.MarvelComicBookConnector(characterId, comicResultPage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comicBookList;
    }

    public List<ComicBook> createComicBooks(JSONArray comicBookData) {
        MarvelComicBookDataParser comicBookParser = new MarvelComicBookDataParser();
        List<ComicBook> comicBooks = new ArrayList<>();
        JSONArray comicTitles = comicBookParser.getComicTitles(comicBookData);
        JSONArray comicDescriptions = comicBookParser.getComicDescription(comicBookData);
        JSONArray comicOnSaleDates = comicBookParser.getComicDates(comicBookData);
        for (int i = 0; i < comicTitles.size(); i++) {
            ComicBook newComic = new ComicBook();
            newComic.title = String.valueOf(comicTitles.get(i));
            newComic.description = String.valueOf(comicDescriptions.get(i));
            newComic.onSaleDate = String.valueOf(comicOnSaleDates.get(i));
            try {
                newComic.thumbnailURL = new URL(comicBookParser.getThumbnail(comicBookData).get(i) + ("/portrait_medium.jpg"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

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


    public ArrayList<Creator> getCreators() {
        return creators;
    }

    public URL getThumbnailURL() {
        return thumbnailURL;
    }

    public String getOnSaleDate() {
        return onSaleDate;
    }


}
