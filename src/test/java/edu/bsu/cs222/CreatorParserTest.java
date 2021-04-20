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
import edu.bsu.cs222.model.Creator;
import edu.bsu.cs222.model.MarvelObject;
import edu.bsu.cs222.model.MarvelSearchParser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CreatorParserTest {
    private MarvelObject stanLee = new Creator();

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
    public void getSeriesTotal_518_ReturnStan_Lee() {
        Assertions.assertEquals(518, stanLee.getSeriesTotal());
    }

    @Test
    public void getStoriesTotal_3295_ReturnStan_Lee() {
        Assertions.assertEquals(3295, stanLee.getStoriesTotal());
    }

    @Test
    void getId_30_ReturnID() {
        Assertions.assertEquals("30", stanLee.getId());
    }
}
