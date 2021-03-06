package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MarvelComicBookDataStream {
    public JSONArray MarvelComicBookConnector(String characterId) throws IOException {
        String urlString = "https://gateway.marvel.com/v1/public/characters/" + characterId +
                "/comics?format=comic&formatType=comic&orderBy=onsaleDate" +
                "&ts=2&apikey=f68be3ef212bbce1cfefed726396718d&hash=997d5981dfde767fa0fca645e967d9ee";
        URL url =new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp21; lnrowe@bsu.edu)");
        return JsonPath.read(connection.getInputStream(), "*");
    }

}
