

/*
 *  Program to search for comics and creators that Marvel has available information on.
 *  Copyright (C) 2021  Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see https://www.gnu.org/licenses/.
 */

package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MarvelSearchConnection {
    private SearchType searchType;

    public JSONArray MarvelSearchConnector(String characterName) throws IOException {
        if (searchType == null) {
            throw new IOException("You must first set the search term");
        }
        String FILE_NAME = "apiInformation.txt";
        APIKey key = new APIKey(FILE_NAME);
        String urlString = "https://gateway.marvel.com/v1/public/" + searchType.asLowerCase() + "?nameStartsWith=" + characterName +
                "&ts=2&apikey=" + key.getPUBLIC_KEY() + "&hash=" + key.getHASH_KEY();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "ComicBookSearch/0.1.2 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp21; lnrowe@bsu.edu)");
        return JsonPath.read(connection.getInputStream(), "*");
    }

    public void setSearchType(String searchTerm) {
        searchType = SearchType.valueOf(searchTerm);
    }


}
