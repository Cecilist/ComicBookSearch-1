package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Character;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CharacterTest {
    private final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spiderCharacter.json");
    private Character spiderMan = new Character();

    {
        try {
            JSONArray charData = JsonPath.read(inputStream, "*");
            spiderMan = spiderMan.buildCharacters(charData).get(0);
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
        Assertions.assertEquals("Bitten by a radioactive spider, high school student Peter Parker gained the speed, strength and powers of a spider. Adopting the name Spider-Man, Peter hoped to start a career using his new abilities. " +
                "Taught that with great power comes great responsibility, " +
                "Spidey has vowed to use his powers to help people.", spiderMan.getDescription());
    }

    @Test
    void getId_SpiderMan_ReturnID() {
        Assertions.assertEquals("1009610", spiderMan.getId());
    }
}
