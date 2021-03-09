package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class MarvelComicBookDataParser {
    public JSONArray getComicTitles(JSONArray marvelData) {
        return JsonPath.read(marvelData, "$..['results'][*]['title']");
    }

    public JSONArray getComicDescription(JSONArray marvelData) {
        return JsonPath.read(marvelData, "$..['results'][*]['description']");
    }

    public JSONArray getComicDates(JSONArray marvelData) {
        return JsonPath.read
                (marvelData, "$..['results'][*]['dates'][0]['date']");
    }

    public JSONArray getComicCreatorName(JSONArray marvelData, int index) {
        return JsonPath.read(marvelData, "$..['results'][" + index + "]['creators']['items'][*]['name']");
    }

    public JSONArray getComicCreatorRole(JSONArray marvelData, int index) {
        return JsonPath.read(marvelData, "$..['results'][" + index + "]['creators']['items'][*]['role']");
    }

    public JSONArray getThumbnail(JSONArray marvelData) {
        return JsonPath.read(marvelData, "$..results[*].thumbnail.path");
    }
}
