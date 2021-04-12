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
import java.net.URL;
import java.net.URLConnection;

public class MarvelComicBookConnection {
    private SearchType searchType;
    public JSONArray MarvelComicBookConnector(String characterId, int comicPage) throws IOException {
        if(searchType == null)
        {
            throw new IOException("You must first set the search term");
        }
        APIKey key = new APIKey();
        String urlString = "https://gateway.marvel.com/v1/public/" + searchType.asLowerCase() + "/" + characterId +
                "/comics?format=comic&formatType=comic&noVariants=true&orderBy=onsaleDate&limit=100&offset=" + (comicPage - 1) * 100 +
                "&ts=2&apikey=" + key.getPublicKey() + "&hash=" + key.getHashKey();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Character Search/0.1.2 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp21; lnrowe@bsu.edu)");
        return JsonPath.read(connection.getInputStream(), "*");
    }
    public void setSearchType(String searchTerm){
        searchType = SearchType.valueOf(searchTerm);
    }

}
