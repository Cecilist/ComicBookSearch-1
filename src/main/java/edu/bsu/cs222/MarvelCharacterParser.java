package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class MarvelCharacterParser {
    public boolean doesCharExist(JSONArray characterData){
        JSONArray totalCount = JsonPath.read(characterData, "$..total");
        Integer resultCount = (Integer) totalCount.get(0);
        return resultCount>0;
    }
    public JSONArray getCharId(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[0].id");
    }
    public JSONArray getCharName(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[0].name");
    }
    public JSONArray getCharDescription(JSONArray characterData){
        return JsonPath.read(characterData, "$..results[0].description");
    }
    public JSONArray getCharThumbnail (JSONArray characterData){
        return JsonPath.read(characterData, "$..results[0].thumbnail.path");
    }
}
