package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class MarvelSuperheroParser {
    public boolean doesCharExist(JSONArray characterData){
        JSONArray totalCount = JsonPath.read(characterData, "$..total");
        return((int)totalCount.get(0)>0);
    }
    public JSONArray getSuperId(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[*].id");
    }
    public JSONArray getSuperName(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[*].name");
    }
    public JSONArray getSuperDescript(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[*].description");
    }
    public JSONArray getSuperThumbnail(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[*].thumbnail.path");
    }
    public JSONArray getComicsTotal(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[*].comics.available");
    }
}
