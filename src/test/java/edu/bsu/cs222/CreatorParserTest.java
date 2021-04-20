package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Creator;
import edu.bsu.cs222.model.MarvelSearchParser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CreatorParserTest {
    private Creator stanLee = new Creator();

    @BeforeEach
    public void setup() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("StanLee.json");
        try {
            JSONArray charData = JsonPath.read(inputStream, "*");
            MarvelSearchParser searchParser = new MarvelSearchParser();
            searchParser.setCharacterData(charData);
            stanLee = searchParser.buildCreators().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getName_Stan_Lee_ReturnStan_Lee() {
        Assertions.assertEquals("Stan Lee", stanLee.getName());
    }

    @Test
    public void getComicsTotal_1662_ReturnStan_Lee() {
        Assertions.assertEquals(1662, stanLee.getComicsTotal());
    }

    @Test
    void getId_30_ReturnID() {
        Assertions.assertEquals("30", stanLee.getId());
    }
}
