package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private final ArrayList<Creator> creators = new ArrayList<>();
    private String title;
    private String description;
    private String onsaleDate;
    private BufferedImage thumbnail;

    public List<ComicBook> createComicBooks(String characterId) throws IOException {
        MarvelComicBookDataStream comicBookStream = new MarvelComicBookDataStream();
        JSONArray comicBookData = comicBookStream.MarvelComicBookConnector(characterId);
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
            newComic.thumbnail = setThumbnail(comicBookParser.getThumbnail(comicBookData), i);
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

    private BufferedImage setThumbnail(JSONArray thumbnailData, int index) throws IOException {
        String thumbnailURLEnd = "/portrait_xlarge.jpg";
        String thumbnailURL = thumbnailData.get(index).toString() + thumbnailURLEnd;
        URL url = new URL(thumbnailURL);
        return (ImageIO.read(url));
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

    public BufferedImage getThumbnail() {
        return thumbnail;
    }
}
