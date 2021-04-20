/*
 *  Program to search for comics and creators that Marvel has available information on.
 *  Copyright (C) 2021  Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see https://www.gnu.org/licenses/.
 */

package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Character;
import edu.bsu.cs222.model.MarvelObject;
import edu.bsu.cs222.model.MarvelSearchParser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CharacterParserTest {
    private MarvelObject spiderMan;

    @BeforeEach
    public void setup() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spiderCharacter.json");
        spiderMan = new Character();
        try {
            JSONArray charData = JsonPath.read(inputStream, "*");
            MarvelSearchParser searchParser = new MarvelSearchParser();
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
        Character charSpiderMan = (Character)spiderMan;
        Assertions.assertEquals('B', charSpiderMan.getDescription().charAt(0));
    }

    @Test
    void getId_1009610_ReturnID() {
        Assertions.assertEquals("1009610", spiderMan.getId());
    }

    @Test
    void hasComics_True_ReturnTrue() {
        Assertions.assertTrue(spiderMan.hasComics());
    }

    @Test
    void getComicsTotal_3979_ReturnTotal() {
        Assertions.assertEquals(3979, spiderMan.getComicsTotal());
    }


}
