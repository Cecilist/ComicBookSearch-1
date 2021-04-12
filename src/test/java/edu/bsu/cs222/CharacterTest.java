package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Character;
import edu.bsu.cs222.model.MarvelSearchParser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CharacterTest {
    private Character spiderMan;

    @BeforeEach
    public void setup() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spiderCharacter.json");
        spiderMan = new Character();
        try {
            JSONArray charData = JsonPath.read(inputStream, "*");
            MarvelSearchParser searchParser= new MarvelSearchParser();
            searchParser.setCharacterData(charData);
            spiderMan = searchParser.buildCharacters().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getName_SpiderMan_ReturnSpiderMan() {
        Assertions.assertEquals("Spider-Man", spiderMan.getName());
    }

    @Test
    void getDescription_SpiderMan_ReturnDescription() {
        Assertions.assertEquals('B', spiderMan.getDescription().charAt(0));
    }

    @Test
    void getId_SpiderMan_ReturnID() {
        Assertions.assertEquals("1009610", spiderMan.getId());
    }
}
