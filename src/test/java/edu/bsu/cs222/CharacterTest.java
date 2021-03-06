package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CharacterTest {
    private InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spiderCharacter.json");
    private JSONArray charData;
    private Character spiderMan = new Character();

    {
        try {
            charData = JsonPath.read(inputStream, "*");
            spiderMan = new Character(charData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals("Spider-Man", spiderMan.getName());
    }

    @Test
    void getDescriptTest() {
        Assertions.assertEquals("Bitten by a radioactive spider, high school student Peter Parker gained the speed, strength and powers of a spider. Adopting the name Spider-Man, Peter hoped to start a career using his new abilities. " +
                "Taught that with great power comes great responsibility, " +
                "Spidey has vowed to use his powers to help people.", spiderMan.getDescription());
    }

    @Test
    void getIdTest() {
        Assertions.assertEquals("1009610", spiderMan.getId());
    }
}
