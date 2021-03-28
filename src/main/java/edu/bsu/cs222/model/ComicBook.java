package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private final ArrayList<Creator> creators = new ArrayList<>();
    private String title;
    private String description;
    private LocalDateTime onSaleDate;
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
        List<String> comicTitles = comicBookParser.getComicTitles(comicBookData);
        List<String> comicDescriptions = comicBookParser.getComicDescription(comicBookData);
        List<LocalDateTime> comicOnSaleDates = comicBookParser.getComicDates(comicBookData);
        for (int i = 0; i < comicTitles.size(); i++) {
            ComicBook newComic = new ComicBook();
            newComic.title = (comicTitles.get(i));
            newComic.description = (comicDescriptions.get(i));
            newComic.onSaleDate = (comicOnSaleDates.get(i));
            try {
                newComic.thumbnailURL = new URL(comicBookParser.getThumbnail(comicBookData).get(i) + ("/portrait_medium.jpg"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            List<String> comicCreatorNames = comicBookParser.getComicCreatorName(comicBookData, i);
            List<String> comicCreatorRoles = comicBookParser.getComicCreatorRole(comicBookData, i);
            for (int x = 0; x < comicCreatorNames.size(); x++) {
                Creator newCreator = new Creator();
                newCreator.setName(comicCreatorNames.get(x));
                newCreator.setRole(comicCreatorRoles.get(x));
                newComic.creators.add(newCreator);
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

    public String getFormattedSaleDate() {
        if(onSaleDate != null)
        return onSaleDate.getMonth() + " " + onSaleDate.getDayOfMonth() + ", " + onSaleDate.getYear();
        else return null;
    }


}
