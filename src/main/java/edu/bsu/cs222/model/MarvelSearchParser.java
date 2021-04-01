package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MarvelSearchParser {
    public boolean doesCharExist(JSONArray characterData) {
        JSONArray totalCount = JsonPath.read(characterData, "$..total");
        return ((int) totalCount.get(0) > 0);
    }

    public List<String> getSuperInformation(JSONArray characterData, String searchTerm) {
        JSONArray information = JsonPath.read(characterData, "$..results[*]." + searchTerm);
        List<String> informationList = new ArrayList<>();
        for (Object o : information) {
            informationList.add(String.valueOf(o));
        }
        return informationList;
    }
}
