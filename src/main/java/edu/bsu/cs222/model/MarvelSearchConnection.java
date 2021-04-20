

package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MarvelSearchConnection {
    private final String FILE_NAME = "apiInformation.txt";
    private SearchType searchType;

    public JSONArray MarvelSearchConnector(String characterName) throws IOException {
        if (searchType == null) {
            throw new IOException("You must first set the search term");
        }
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
