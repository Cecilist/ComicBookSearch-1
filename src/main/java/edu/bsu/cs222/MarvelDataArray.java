package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class MarvelDataArray {
    public JSONArray cacheDataStream(InputStream inputStream) throws IOException {
        return (JsonPath.read(inputStream, "*"));
    }
}
