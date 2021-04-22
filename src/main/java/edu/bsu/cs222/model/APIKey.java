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

package edu.bsu.cs222.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class APIKey {
    private final String PUBLIC_KEY;
    private final String HASH_KEY;

    public APIKey(String fileName) throws FileNotFoundException {
        File apiFile = new File
                (Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        Scanner fileReader = new Scanner(apiFile);
        PUBLIC_KEY = fileReader.nextLine();
        HASH_KEY = fileReader.nextLine();
    }

    public String getHASH_KEY() {
        return HASH_KEY;
    }

    public String getPUBLIC_KEY() {
        return PUBLIC_KEY;
    }

}
