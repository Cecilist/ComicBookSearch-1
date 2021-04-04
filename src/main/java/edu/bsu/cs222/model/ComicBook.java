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
                Creator newCreator = new Creator();
                newCreator.setName(comicCreatorsNames.get(x));
                newCreator.setRole(comicCreatorsRoles.get(x));
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
