package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private final ArrayList<Creators> creators = new ArrayList<>();
    private String title;
    private String description;
    private LocalDateTime onSaleDate;
    private URL thumbnailURL;

    public ComicBook() {
    }

    public List<ComicBook> getComicBookData(String characterId, int comicResultPage, String SearchTerm) {
        MarvelComicBookConnection comicBookStream = new MarvelComicBookConnection();
        List<ComicBook> comicBookList = null;
        try {
            comicBookList = createComicBooks(comicBookStream.MarvelComicBookConnector(SearchTerm,characterId, comicResultPage));
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

            List<String> comicCreatorsNames = comicBookParser.getComicCreatorName(comicBookData, i);
            List<String> comicCreatorsRoles = comicBookParser.getComicCreatorRole(comicBookData, i);
            if (comicCreatorsNames.size() > 0)
            for (int x = 0; x < comicCreatorsNames.size(); x++) {
                Creators newCreators = new Creators();
                newCreators.setName(comicCreatorsNames.get(x));
                newCreators.setRole(comicCreatorsRoles.get(x));
                newComic.creators.add(newCreators);

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


    public ArrayList<Creators> getCreators() {
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
