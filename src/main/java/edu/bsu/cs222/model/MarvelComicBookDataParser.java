package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;


public class MarvelComicBookDataParser {
    public List<String> getComicTitles(JSONArray marvelData) {
        JSONArray titles = read(marvelData, "$..['results'][*]['title']");
        List<String> titleList = new ArrayList<>();
        for (Object o : titles) {
            titleList.add(String.valueOf(o));
        }
        return titleList;
    }

    public List<String> getComicDescription(JSONArray marvelData) {
        JSONArray descriptions = read(marvelData, "$..['results'][*]['description']");
        List<String> descriptionList = new ArrayList<>();
        for (Object o : descriptions) {
            descriptionList.add(String.valueOf(o));
        }
        return descriptionList;
    }

    public List<LocalDateTime> getComicDates(JSONArray marvelData) {
        JSONArray timestamps = read
                (marvelData, "$..['results'][*]['dates'][0]['date']");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'-'SSSS");
        List<LocalDateTime> dateList = new ArrayList<>();
        for (Object o : timestamps) {
            try {
                dateList.add(LocalDateTime.parse(String.valueOf(o), dateFormat));
            } catch (DateTimeException e) {
                dateList.add(null);
            }
        }
        return dateList;
    }

    public List<String> getComicCreatorName(JSONArray marvelData, int index) {
        JSONArray creatorNames = read(marvelData, "$..['results'][" + index + "]['creators']['items'][*]['name']");
        List<String> creatorNamesList = new ArrayList<>();
        for (Object o : creatorNames) {
            creatorNamesList.add(String.valueOf(o));
        }
        return creatorNamesList;
    }

    public List<String> getComicCreatorRole(JSONArray marvelData, int index) {
        JSONArray creatorRoles = JsonPath.read(marvelData, "$..['results'][" + index + "]['creators']['items'][*]['role']");
        List<String> creatorRolesList = new ArrayList<>();
        for (Object o : creatorRoles) {
            creatorRolesList.add(String.valueOf(o));
        }
        return creatorRolesList;
    }

    public List<String> getThumbnail(JSONArray marvelData) {
        JSONArray thumbnails = read(marvelData, "$..results[*].thumbnail.path");
        List<String> thumbnailList = new ArrayList<>();
        for (Object o : thumbnails) {
            thumbnailList.add(String.valueOf(o));
        }
        return thumbnailList;
    }
}
