//  <Program to search for comics and creators that Marvel has available information on.>
//  Copyright (C) <2021>  <Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish>

//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see <https://www.gnu.org/licenses/>.

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

    public List<String> getInformation(JSONArray characterData, String searchTerm) {
        JSONArray information = JsonPath.read(characterData, "$..results[*]." + searchTerm);
        List<String> informationList = new ArrayList<>();
        for (Object o : information) {
            informationList.add(String.valueOf(o));
        }
        return informationList;
    }
}
