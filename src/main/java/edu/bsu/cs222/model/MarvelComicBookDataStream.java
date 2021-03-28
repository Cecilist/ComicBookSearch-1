package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MarvelComicBookDataStream {
    public JSONArray MarvelComicBookConnector(String characterId, int comicPage) throws IOException {
        APIKey key = new APIKey();
        String urlString = "https://gateway.marvel.com/v1/public/characters/" + characterId +
                "/comics?format=comic&formatType=comic&noVariants=true&orderBy=onsaleDate&limit=100&offset=" + (comicPage - 1) * 100 +
                "&ts=2&apikey=" + key.getPublicKey() + "&hash=" + key.getHashKey();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Superhero Search/0.1.2 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp21; lnrowe@bsu.edu)");
        return JsonPath.read(connection.getInputStream(), "*");
    }

}
